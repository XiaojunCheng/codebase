package com.codebase.framework.spring.reconstruct.bean;

/**
 * @author Xiaojun.Cheng
 * @date 2017/6/10
 */
public class OutputServiceImpl implements OutputService {

    private SayHelloService sayHelloService;

    @Override
    public void output() {
        System.out.println("output, class: " + sayHelloService.getClass().getName());
    }
}
