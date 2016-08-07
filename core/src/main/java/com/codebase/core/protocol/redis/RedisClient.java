package com.codebase.core.protocol.redis;

import com.codebase.core.protocol.MemoryStream;
import com.codebase.core.protocol.SocketConnection;
import com.codebase.core.protocol.codec.RedisCodec;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.List;

public class RedisClient {

    private int bufferSize = 4 * 1024;

    private SocketConnection socket;
    private MemoryStream stream;

    public RedisClient(String host, int port) throws Exception {
        this(host, port, 0);
    }

    public RedisClient(String host, int port, int timeoutInMills) throws Exception {
        socket = new SocketConnection(host, port, timeoutInMills);
        stream = new MemoryStream();
    }

    public void close() {
        socket.close();
    }

    public void set(byte[] key, byte[] value) throws Exception {
        sendRequest(Command.SET, key, value);
        Response resp = receiveResponse();
        if (ResponseType.SIMPLE_STRING.equals(resp.getResponseType())) {
            return;
        }
        resp.exception();
    }

    public void set(String key, byte[] value) throws Exception {
        set(key.getBytes(), value);
    }

    public void set(String key, String value) throws Exception {
        set(key, value.getBytes());
    }

    public byte[] get(byte[] key) throws Exception {
        sendRequest(Command.GET, key);
        Response resp = receiveResponse();
        if (ResponseType.BULK_STRING.equals(resp.getResponseType())) {
            List<byte[]> blocks = resp.getRespBlocks();
            if (blocks.isEmpty()) {
                return null;
            } else {
                return blocks.get(0);
            }
        }
        resp.exception();
        return null;
    }

    public byte[] get(String key) throws Exception {
        return get(key.getBytes());
    }

    private void sendRequest(String cmd, byte[]... params) throws Exception {
        Request req = new Request(cmd, params);
        ByteBuffer reqBuf = RedisCodec.encodeRequest(req);
        OutputStream os = socket.getOutputStream();
        os.write(reqBuf.array(), reqBuf.position(), reqBuf.remaining());
        os.flush();
    }

    private Response receiveResponse() throws Exception {

        InputStream is = socket.getInputStream();
        stream.clear();

        boolean canBreak = false;
        boolean needReadType = true;
        ResponseType type = null;
        while (!canBreak) {
            stream.realloc(bufferSize);
            int len = is.read(stream.array(), stream.writePosition(), stream.writeAvailable());
            if (len <= 0) {
                break;
            }
            stream.writePosition += len;

            if (needReadType) {
                type = readResponseType();
                needReadType = false;
            }

            int countOfCRLF = stream.countCRLF();
            switch (type) {
                case SIMPLE_STRING:
                case ERROR:
                case INTEGER:
                    if (countOfCRLF == 1) {
                        canBreak = true;
                    }
                    break;
                case BULK_STRING:
                    if (countOfCRLF == 2) {
                        canBreak = true;
                    } else if (countOfCRLF == 1) {
                        int valueLength = readHeaderValue();
                        if (valueLength == -1) {
                            canBreak = true;
                        }
                    }
                    break;
                case ARRAY:
                    if (countOfCRLF <= 0) {
                        break;
                    }
                    int paramNum = readHeaderValue();
                    if (paramNum == -1) {
                        canBreak = true;
                    } else if (countOfCRLF == (1 + paramNum * 2)) {
                        canBreak = true;
                    }
                    break;
                default:
                    return null;
            }
        }

        switch (type) {
            case SIMPLE_STRING:
                return RedisParser.readSimpleString(stream);
            case ERROR:
                return RedisParser.readError(stream);
            case INTEGER:
                return RedisParser.readInteger(stream);
            case BULK_STRING:
                return RedisParser.readBulkString(stream);
            case ARRAY:
                return RedisParser.readArray(stream);
            default:
                return null;
        }
    }

    private int readHeaderValue() {
        int idx = stream.findCRLF(stream.readPosition);
        byte[] lengthBytes = stream.slice(stream.readPosition, idx);
        return Integer.parseInt(new String(lengthBytes));
    }

    private ResponseType readResponseType() throws Exception {
        if (stream.readAvailable() <= 0) {
            throw new Exception("null response");
        }

        byte typeByte = stream.read();
        ResponseType type = getResponseType(typeByte);
        if (type == null) {
            throw new Exception("invalid type " + new String(new byte[]{typeByte}));
        }
        return type;
    }

    private ResponseType getResponseType(byte typeByte) {
        ResponseType matchedType = null;
        for (ResponseType type : ResponseType.values()) {
            if (type.desc == typeByte) {
                matchedType = type;
            }
        }
        return matchedType;
    }

    private void receive() throws Exception {

    }

}
