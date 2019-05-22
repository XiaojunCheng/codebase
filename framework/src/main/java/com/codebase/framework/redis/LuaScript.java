package com.codebase.framework.redis;

/**
 * @author Xiaojun.Cheng
 * @date 2017/3/31
 */
public class LuaScript {

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer();
        //参数:锁字符串,锁过期时间
        sb.append("local key=KEYS[1]").append(NEW_LINE);
        sb.append("local expireTime=ARGV[1]").append(NEW_LINE);
        //不存在则set
        sb.append("local ret=redis.call('setnx',key, 1)").append(NEW_LINE);
        //是否设置超时
        sb.append("if (ret == 1) then").append(NEW_LINE);
        sb.append("\t").append("redis.call('expire', key, expireTime)").append(NEW_LINE);
        sb.append("end").append(NEW_LINE);
        //返回结果; 失败:0,成功:1
        sb.append("\t").append("return ret");
        System.out.println(sb.toString());
    }

}
