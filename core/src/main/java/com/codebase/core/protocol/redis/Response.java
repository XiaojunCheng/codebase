package com.codebase.core.protocol.redis;

import java.util.List;

public class Response {

    private final ResponseType type;
    private List<byte[]> respBlocks;

    public Response(ResponseType type) {
        this.type = type;
    }

    public ResponseType getResponseType() {
        return type;
    }

    public void setRespBlocks(List<byte[]> respBlocks) {
        this.respBlocks = respBlocks;
    }

    public List<byte[]> getRespBlocks() {
        return respBlocks;
    }

    public void exception() throws Exception {
        if (respBlocks.size() >= 1) {
            throw new Exception(new String(respBlocks.get(0)));
        } else {
            throw new Exception("");
        }
    }
}
