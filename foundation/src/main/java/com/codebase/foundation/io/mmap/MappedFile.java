package com.codebase.foundation.io.mmap;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MappedFile extends AbstractReferenceCounted {

    private static final Logger LOGGER = LoggerFactory.getLogger(MappedFile.class);

    public static final int OS_PAGE_SIZE = 1024 * 4;
    // 当前JVM中映射的内存总大小
    private static final AtomicLong totalMappedMemoryInBytes = new AtomicLong(0);
    // 当前JVM中mmap文件句柄数量
    private static final AtomicInteger totalMappedFileCount = new AtomicInteger(0);

    //  文件大小，定长
    private final int fileSize;
    // 文件
    private final File file;
    // 文件绝对路径
    private final String filePath;
    // 映射的内存对象，position永远不变
    private final MappedByteBuffer mappedByteBuffer;
    // 映射的FileChannel对象
    private FileChannel fileChannel;
    // 文件起点对应的全局偏移
    private final long startPositionInGlobal;
    // 追加写到什么位置
    private final AtomicInteger appendSizeInBytes = new AtomicInteger(0);
    // flush到什么位置
    private final AtomicInteger flushedSizeInBytes = new AtomicInteger(0);

    public MappedFile(final File file, int fileSize, long startOffsetInGlobal) throws IOException {

        this.file = file;
        this.fileSize = fileSize;
        this.filePath = file.getAbsolutePath();
        this.startPositionInGlobal = startOffsetInGlobal;

        ensureDirOK(this.file.getParent());

        try {
            this.fileChannel = new RandomAccessFile(this.file, "rw").getChannel();
            this.mappedByteBuffer = this.fileChannel.map(MapMode.READ_WRITE, 0, fileSize);
            totalMappedMemoryInBytes.addAndGet(fileSize);
            totalMappedFileCount.incrementAndGet();
        } catch (IOException e) {
            LOGGER.warn("map file " + filePath + " failed", e);
            if (this.fileChannel != null) {
                this.fileChannel.close();
            }
            throw e;
        }
    }

    /**
     * 追加消息
     *
     * @param buffer
     */
    public AppendResult append(final ByteBuffer buffer) {

        AppendResult result = new AppendResult();

        // 记录位置及buffer长度信息
        int appendPosition = appendSizeInBytes.get();
        int length = buffer.remaining();
        if (!this.canAppend(length)) {
            result.setSucceed(false);
            return result;
        }

        // 写入数据
        ByteBuffer mappedBuffer = mappedByteBuffer.slice();
        mappedBuffer.position(appendPosition);
        mappedBuffer.put(buffer);
        appendSizeInBytes.addAndGet(length);
        // 
        result.setAppendOffset(startPositionInGlobal + appendPosition);
        result.setLength(length);
        return result;
    }

    /**
     * 读取数据
     *
     * @param position 起始偏移
     * @param length   读取长度
     */
    public ReadResult read(int position, int length) {

        if ((position + length) > this.appendSizeInBytes.get()) { // 请求参数非法
            LOGGER.warn("[read] invalid request, startOffsetInGlobal [{}], position [{}], length [{}]", startPositionInGlobal, position, length);
            return new ReadResult(false);
        }

        if (this.retain()) {

            ByteBuffer tmpBuffer = this.mappedByteBuffer.slice();
            tmpBuffer.position(position);
            ByteBuffer validBuffer = tmpBuffer.slice();
            validBuffer.limit(length);

            return new ReadResult(validBuffer, length, this);
        } else { // 请求参数非法或者mmap资源已经被释放
            LOGGER.warn("[read] try lock failed, startOffsetInGlobal [{}], position [{}], length [{}]", startPositionInGlobal, position, length);
            return new ReadResult(false);
        }
    }

    /**
     * 强制刷盘
     */
    public int flush(int pageCount) {

        if (needFlush(pageCount)) {
            if (retain()) {
                doFlush();
                release();
            } else { // 有其他线程正在进行刷盘
                LOGGER.warn("[flush] try lock failed, appendSizeInBytes [{}], flushedSizeInBytes [{}]", appendSizeInBytes.get(), flushedSizeInBytes);
                this.flushedSizeInBytes.set(this.appendSizeInBytes.get());
            }
        }

        return getFlushedSizeInBytes();
    }

    private void doFlush() {
        int flushSize = this.appendSizeInBytes.get();
        this.mappedByteBuffer.force();
        this.flushedSizeInBytes.set(flushSize);
    }

    private boolean needFlush(int pageCount) {

        int flushSize = flushedSizeInBytes.get();
        int appendSize = appendSizeInBytes.get();

        boolean needFlush = appendSize > flushSize;
        if (pageCount <= 0) {
            return needFlush;
        } else {
            return ((appendSize / OS_PAGE_SIZE) - (flushSize / OS_PAGE_SIZE)) >= pageCount;
        }
    }

    @Override
    public boolean cleanup() {

        // 如果已经cleanup，再次操作会引起crash
        if (this.isCleanupOver()) {
            LOGGER.warn("file[REF:{}] {} have cleanup, do not do it again.", getRefCount(), filePath);
            // 必须返回true
            return true;
        }

        flush(0);
        clean(this.mappedByteBuffer);
        close(this.fileChannel);

        totalMappedMemoryInBytes.addAndGet(this.fileSize * (-1));
        totalMappedFileCount.decrementAndGet();
        LOGGER.info("unmap file[REF:{}] {} OK", getRefCount(), filePath);
        return true;
    }

    private void close(FileChannel fileChannel) {
        try {
            fileChannel.close();
        } catch (Exception e) {
            LOGGER.warn("", e);
        }
    }

    public boolean canAppend(int length) {
        return length <= (fileSize - appendSizeInBytes.get());
    }

    public void setFull() {
        flush(0);
        setAppendSizeInBytes(fileSize);
    }

    public boolean isFull() {
        return fileSize == appendSizeInBytes.get();
    }

    private int getFlushedSizeInBytes() {
        return flushedSizeInBytes.get();
    }

    public int getAppendSizeInBytes() {
        return appendSizeInBytes.get();
    }

    public void setAppendSizeInBytes(int size) {
        appendSizeInBytes.set(size);
        flushedSizeInBytes.set(size);
    }

    public MappedByteBuffer getMappedByteBuffer() {
        return mappedByteBuffer;
    }

    public long lastModified() {
        return file.lastModified();
    }

    public File getFile() {
        return file;
    }

    public String getFilePath() {
        return filePath;
    }

    public int getFileSize() {
        return fileSize;
    }

    /**
     * 文件起点对应的全局偏移
     */
    public long getStartPositionInGlobal() {
        return this.startPositionInGlobal;
    }

    public static void ensureDirOK(final String dirName) {
        if (dirName != null) {
            File f = new File(dirName);
            if (!f.exists()) {
                boolean result = f.mkdirs();
                LOGGER.info(dirName + " mkdir " + (result ? "OK" : "Failed"));
            }
        }
    }

    public static void clean(final ByteBuffer buffer) {
        if (buffer == null || !buffer.isDirect() || buffer.capacity() == 0)
            return;
        invoke(invoke(viewed(buffer), "cleaner"), "clean");
    }

    private static Object invoke(final Object target, final String methodName, final Class<?>... args) {
        return AccessController.doPrivileged(new PrivilegedAction<Object>() {
            public Object run() {
                try {
                    Method method = method(target, methodName, args);
                    method.setAccessible(true);
                    return method.invoke(target);
                } catch (Exception e) {
                    throw new IllegalStateException(e);
                }
            }
        });
    }

    private static Method method(Object target, String methodName, Class<?>[] args) throws NoSuchMethodException {
        try {
            return target.getClass().getMethod(methodName, args);
        } catch (NoSuchMethodException e) {
            return target.getClass().getDeclaredMethod(methodName, args);
        }
    }

    private static ByteBuffer viewed(ByteBuffer buffer) {

        String methodName = "viewedBuffer";

        // JDK7中将DirectByteBuffer类中的viewedBuffer方法换成了attachment方法
        Method[] methods = buffer.getClass().getMethods();
        for (int i = 0; i < methods.length; i++) {
            if (methods[i].getName().equals("attachment")) {
                methodName = "attachment";
                break;
            }
        }

        ByteBuffer viewedBuffer = (ByteBuffer) invoke(buffer, methodName);
        if (viewedBuffer == null)
            return buffer;
        else
            return viewed(viewedBuffer);
    }

    public ByteBuffer slice() {
        return mappedByteBuffer.slice();
    }

    public static int getTotalMmapedFileCount() {
        return totalMappedFileCount.get();
    }

    public static long getTotalMmapedMemoryInBytes() {
        return totalMappedMemoryInBytes.get();
    }
}
