package com.codebase.core.protocol.redis;

import com.codebase.core.protocol.MemoryStream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RedisParser {

    private static void readPureData(MemoryStream stream, Response response) {
        List<byte[]> respBlocks = new ArrayList<>();
        respBlocks.add(stream.readUntilCRLF());
        response.setRespBlocks(respBlocks);
    }

    public static Response readSimpleString(MemoryStream stream) {
        Response response = new Response(ResponseType.SIMPLE_STRING);
        readPureData(stream, response);
        return response;
    }

    public static Response readError(MemoryStream stream) {
        Response response = new Response(ResponseType.ERROR);
        readPureData(stream, response);
        return response;
    }

    public static Response readInteger(MemoryStream stream) {
        Response response = new Response(ResponseType.INTEGER);
        readPureData(stream, response);
        return response;
    }

    private static void readData(MemoryStream stream, Response response) {
        byte[] lengthBytes = stream.readUntilCRLF();
        int length = Integer.parseInt(new String(lengthBytes));
        if (length <= 0) {
            response.setRespBlocks(Collections.<byte[]>emptyList());
        } else {
            List<byte[]> respBlocks = new ArrayList<>();
            respBlocks.add(stream.read(length));
            response.setRespBlocks(respBlocks);
        }
    }

    public static Response readBulkString(MemoryStream stream) {
        Response response = new Response(ResponseType.BULK_STRING);
        readData(stream, response);
        return response;
    }

    public static Response readArray(MemoryStream stream) {
        Response response = new Response(ResponseType.ARRAY);
        return response;
    }
}
