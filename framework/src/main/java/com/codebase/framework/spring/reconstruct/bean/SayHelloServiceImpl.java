package com.codebase.framework.spring.reconstruct.bean;

import lombok.Data;

/**
 * @author Xiaojun.Cheng
 * @date 2017/6/10
 */
@Data
public class SayHelloServiceImpl implements SayHelloService {

    private String name;
    private OutputService outputService;

    @Override
    public void sayHello() {
        System.out.println("=========== before sayHello");
        System.out.println("hello! by " + name + ", class: " + outputService.getClass().getName());
        outputService.output();
        System.out.println("=========== after sayHello");
    }
}
