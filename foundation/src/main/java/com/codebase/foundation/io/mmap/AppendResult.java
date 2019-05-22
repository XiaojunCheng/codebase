package com.codebase.foundation.io.mmap;

/**
 * 文件追加结果
 */
public class AppendResult {

    // 是否存储成功
    private boolean succeed = true;

    private long appendOffset;

    private long length;

    public AppendResult() {
    }

    public boolean isSucceed() {
        return succeed;
    }

    public void setSucceed(boolean succeed) {
        this.succeed = succeed;
    }

    public long getAppendOffset() {
        return appendOffset;
    }

    public void setAppendOffset(long appendOffset) {
        this.appendOffset = appendOffset;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

}
