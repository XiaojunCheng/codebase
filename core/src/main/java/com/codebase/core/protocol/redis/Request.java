package com.codebase.core.protocol.redis;

import java.util.ArrayList;
import java.util.List;

public class Request {

    public final String cmd;

    public final List<byte[]> params = new ArrayList<>();

    public Request(String cmd, byte[]... reqParams) {
        this.cmd = cmd;
        for(byte[] param : reqParams) {
            this.params.add(param);
        }
    }

}
