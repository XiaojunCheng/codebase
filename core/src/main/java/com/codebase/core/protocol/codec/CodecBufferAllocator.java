package com.codebase.core.protocol.codec;

import java.nio.ByteBuffer;

public class CodecBufferAllocator {

    private static final int INIT_MESSAGE_LENGTH = 64 * 1024;
    private static final int SHRINK_BUFFER_INTERVAL = 60 * 1000;

    private static ThreadLocal<ByteBuffer> bufferCache = new ThreadLocal<>();
    private static ThreadLocal<Long> updateTimeCache = new ThreadLocal<>();

    public static ByteBuffer allocate(int length) {

        if (bufferCache.get() == null) {
            allocate0(Math.max(roundUpToPowerOf2(length), INIT_MESSAGE_LENGTH));
            return bufferCache.get();
        }

        ByteBuffer buffer = bufferCache.get();
        buffer.clear();
        if (needExpand(buffer.capacity(), length)) {
            allocate0(roundUpToPowerOf2(length));
        } else if (needShrink(buffer.capacity(), length)) {
            allocate0(INIT_MESSAGE_LENGTH);
        }

        return bufferCache.get();
    }

    private static boolean needExpand(int capacity, int length) {
        return capacity < length;
    }

    private static boolean needShrink(int capacity, int length) {
        return length > INIT_MESSAGE_LENGTH && // 大于初始长度
                capacity > length && //
                (System.currentTimeMillis() - updateTimeCache.get()) >= SHRINK_BUFFER_INTERVAL //
                ;
    }

    private static void allocate0(int capacity) {
        ByteBuffer buffer = ByteBuffer.allocate(capacity);
        updateTimeCache.set(System.currentTimeMillis());
        bufferCache.set(buffer);
    }

    private static int roundUpToPowerOf2(int number) {
        return (number > 1) ? Integer.highestOneBit((number - 1) << 1) : 1;
    }
}
