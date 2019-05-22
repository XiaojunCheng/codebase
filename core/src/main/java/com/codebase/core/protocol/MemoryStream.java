package com.codebase.core.protocol;

import com.codebase.common.util.ByteUtil;

import java.util.Arrays;

public class MemoryStream {

    public int readPosition;
    public int writePosition;
    public int capacity;
    private byte[] buf;

    public MemoryStream() {
        this(4096);
    }

    public MemoryStream(int capacity) {
        this.readPosition = 0;
        this.writePosition = 0;
        this.capacity = capacity;
        this.buf = new byte[capacity];
    }

    public void clear() {
        this.readPosition = 0;
        this.writePosition = 0;
    }

    public byte[] array() {
        return buf;
    }

    public int writePosition() {
        return writePosition;
    }

    public int writeAvailable() {
        return capacity - writePosition;
    }

    public int readAvailable() {
        return writePosition - readPosition;
    }

    public byte read() {
        byte b = buf[readPosition];
        readPosition += 1;
        return b;
    }

    public byte[] read(int len) {
        byte[] newBuf = Arrays.copyOfRange(buf, readPosition, readPosition + len);
        readPosition += len;
        return newBuf;
    }

    public byte[] slice(int from, int to) {
        return Arrays.copyOfRange(buf, from, to);
    }

    public void realloc(int length) {
        if (writeAvailable() < length) {
            capacity *= 2;
            buf = Arrays.copyOf(buf, capacity);
        }
    }

    public int countCRLF() {
        int count = 0;
        int offset = readPosition;
        int idx = findCRLF(offset);
        while(idx >= 0) {
            count++;
            offset = idx + 2;
            idx = findCRLF(offset);
        }
        return count;
    }

    public int findCRLF(int offset) {
        for (int i = offset; i < writePosition && i + 2 <= writePosition; i += 1) {
            if (ByteUtil.CR == buf[i] && ByteUtil.LF == buf[i + 1]) {
                return i;
            }
        }
        return -1;
    }

    public byte[] readUntilCRLF() {
        int idx = findCRLF(readPosition);
        if(idx == -1) {
            return ByteUtil.EMPTY;
        }

        byte[] newBuf = Arrays.copyOfRange(buf, readPosition, idx);
        readPosition = idx+2;
        return newBuf;
    }

    public static String repr(byte[] bs) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bs.length; i++) {
            char b = (char) bs[i];
            //byte b = buf[i];
            if (b == '\r') {
                sb.append("\\r");
            } else if (b == '\n') {
                sb.append("\\n");
            } else if (b == '\t') {
                sb.append("\\t");
            } else if (b == '_') {
                sb.append(b);
            } else if (Character.isLetter(b) || Character.isDigit(b)) {
                sb.append(b);
            } else {
                sb.append(String.format("\\%02X", (int) b));
            }
        }
        return sb.toString();
    }

    public String repr() {
        return repr(slice());
    }

    public byte[] slice() {
        return Arrays.copyOfRange(buf, 0, writePosition);
    }
}
