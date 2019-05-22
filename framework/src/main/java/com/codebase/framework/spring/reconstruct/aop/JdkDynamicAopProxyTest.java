package com.codebase.framework.spring.reconstruct.aop;

import com.codebase.framework.spring.reconstruct.SayHelloService;
import com.codebase.framework.spring.reconstruct.context.ApplicationContext;
import com.codebase.framework.spring.reconstruct.context.ClassPathXmlApplicationContext;

public class JdkDynamicAopProxyTest {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("aop/reconstruct/spring.xml");
		SayHelloService sayHelloService = (SayHelloService) applicationContext.getBean("sayHelloService");
		sayHelloService.sayHello();

		//============== with AOP
		//1. 设置被代理对象(Joinpoint)
		AdvisedSupport advisedSupport = new AdvisedSupport();
		TargetSource targetSource = new TargetSource(sayHelloService, SayHelloService.class);
		advisedSupport.setTargetSource(targetSource);

		//2. 设置拦截器(Advice)
		TimerMethodInterceptor timerInterceptor = new TimerMethodInterceptor();
		advisedSupport.setMethodInterceptor(timerInterceptor);

		//3. 创建代理(Proxy)
		JdkDynamicAopProxy jdkDynamicAopProxy = new JdkDynamicAopProxy(advisedSupport);
		SayHelloService sayHelloServiceProxy = (SayHelloService) jdkDynamicAopProxy.getProxy();

		// 4. 基于AOP的调用
		sayHelloServiceProxy.sayHello();
	}
}
