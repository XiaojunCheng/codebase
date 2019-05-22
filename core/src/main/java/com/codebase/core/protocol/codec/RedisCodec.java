package com.codebase.core.protocol.codec;


import com.codebase.common.util.ByteUtil;
import com.codebase.core.protocol.redis.Request;

import java.nio.ByteBuffer;

public class RedisCodec {

    private static final int MAX_HEADER_EXTENDED_BYTES = 7;
    private static final int MAX_PARAM_EXTENDED_BYTES = 9;
    private static final byte HEADER_FLAG = '*';
    private static final byte PARAM_HEADER_FLAG = '$';

    public static ByteBuffer encodeRequest(Request request) {

        byte[] cmdBytes = request.cmd.getBytes();
        int totalLength = calculateMaxBufferLength(request, cmdBytes);
        ByteBuffer buf = CodecBufferAllocator.allocate(totalLength);
        // header
        buf.put(HEADER_FLAG);
        buf.put(Integer.toString(1 + request.params.size()).getBytes());
        buf.put(ByteUtil.CR);
        buf.put(ByteUtil.LF);
        // cmd & params
        writeParam(buf, cmdBytes);
        for(byte[] param : request.params) {
            writeParam(buf, param);
        }

        buf.flip();
        return buf;
    }

    private static void writeParam(ByteBuffer buf, byte[] prams) {
        buf.put(PARAM_HEADER_FLAG);
        buf.put(Integer.toString(prams.length).getBytes());
        buf.put(ByteUtil.CR);
        buf.put(ByteUtil.LF);
        buf.put(prams);
        buf.put(ByteUtil.CR);
        buf.put(ByteUtil.LF);
    }

    private static int calculateMaxBufferLength(Request request, byte[] cmdBytes) {
        int totalLength = MAX_HEADER_EXTENDED_BYTES;
        totalLength += MAX_PARAM_EXTENDED_BYTES + cmdBytes.length;
        for (byte[] param : request.params) {
            totalLength += MAX_PARAM_EXTENDED_BYTES + param.length;
        }
        return totalLength;
    }

}
