package com.codebase.framework.spring.reconstruct;

import lombok.Data;

/**
 * @author Xiaojun.Cheng
 * @date 2017/6/10
 */
@Data
public class SayHelloServiceImpl implements SayHelloService {

    private String name;

    @Override
    public void sayHello() {
        System.out.println("hello! by " + name);
    }
}
