package com.codebase.core.protocol.codec;

import com.codebase.common.util.ByteUtil;
import com.codebase.core.protocol.redis.Request;

import java.nio.ByteBuffer;

public class SSDBCodec {

    private static final int MAX_PARAM_EXTENTION_BYTES = 6;

    public static ByteBuffer encodeRequest(Request request) {

        byte[] cmdBytes = request.cmd.getBytes();
        int totalLength = calculateMaxBufferLength(request, cmdBytes);
        ByteBuffer buf = CodecBufferAllocator.allocate(totalLength);
        // cmd & params
        writeParam(buf, cmdBytes);
        for(byte[] param : request.params) {
            writeParam(buf, param);
        }

        buf.flip();
        return buf;
    }

    private static void writeParam(ByteBuffer buf, byte[] prams) {
        buf.put(Integer.toString(prams.length).getBytes());
        buf.put(ByteUtil.LF);
        buf.put(prams);
        buf.put(ByteUtil.LF);
    }

    private static int calculateMaxBufferLength(Request request, byte[] cmdBytes) {
        int totalLength = 1; //END
        totalLength += MAX_PARAM_EXTENTION_BYTES + cmdBytes.length;
        for (byte[] param : request.params) {
            totalLength += MAX_PARAM_EXTENTION_BYTES + param.length;
        }
        return totalLength;
    }

}
