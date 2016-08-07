package com.codebase.core.protocol;

import com.codebase.core.protocol.redis.RedisClient;
import com.codebase.core.protocol.redis.Request;

public class RedisTest {

    public static void main(String[] args) throws Exception {
        Request req = new Request("set", "hello".getBytes(), "world".getBytes());
        {
            //ByteBuffer reqBuffer = RedisCodec.encodeRequest(req);
            //System.out.println(new String(reqBuffer.array(), reqBuffer.position(), reqBuffer.remaining()));
        }
        System.out.println("=========");
        {
            //ByteBuffer reqBuffer = SSDBCodec.encodeRequest(req);
            //System.out.println(new String(reqBuffer.array(), reqBuffer.position(), reqBuffer.remaining()));
        }

        RedisClient redis = new RedisClient("127.0.0.1", 6379);
        byte[] data = redis.get("a");
        if(data != null) {
            System.out.println(new String(data));
        }
        System.out.println("=========");

        redis.set("a", "ddd");
        data = redis.get("a");
        if(data != null) {
            System.out.println(new String(data));
        }
        System.out.println("=========");
        redis.close();
    }
}
