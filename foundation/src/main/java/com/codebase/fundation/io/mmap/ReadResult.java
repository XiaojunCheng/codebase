package com.codebase.fundation.io.mmap;

import java.nio.ByteBuffer;

/**
 * mapped文件读取结果
 * NOTE: 用完之后一定要记得release
 */
public class ReadResult {

    // 是否存储成功
    private boolean succeed = true;

    private ByteBuffer buffer;

    private int size = 0;

    private MappedFile storeFile;

    public ReadResult() {
    }

    public ReadResult(boolean succeed) {
        this.succeed = succeed;
    }

    public ReadResult(final ByteBuffer buffer, int size, final MappedFile storeFile) {
        this.buffer = buffer;
        this.size = size;
        this.storeFile = storeFile;
    }

    public boolean isSucceed() {
        return succeed;
    }

    public ByteBuffer getBuffer() {
        return buffer;
    }

    public int getSize() {
        return size;
    }

    public synchronized void release() {
        if (storeFile != null) {
            storeFile.release();
            storeFile = null;
        }
    }
}
