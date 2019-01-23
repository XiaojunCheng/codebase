package com.codebase.framework.rpc.client;

import com.codebase.framework.rpc.registry.ServiceDiscovery;
import com.codebase.framework.rpc.server.RpcRequest;
import com.codebase.framework.rpc.server.RpcResponse;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.UUID;

/**
 * @author Xiaojun.Cheng
 * @date 2019/1/19
 */
public class RpcProxy {

    private String serverAddress;
    private ServiceDiscovery serviceDiscovery;

    /**
     * 直连方式
     *
     * @param serverAddress
     */
    public RpcProxy(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    /**
     * 注册中心方式
     *
     * @param serviceDiscovery
     */
    public RpcProxy(ServiceDiscovery serviceDiscovery) {
        this.serviceDiscovery = serviceDiscovery;
    }

    public <T> T create(Class<?> interfaceClass) {
        return (T) Proxy.newProxyInstance(
                interfaceClass.getClassLoader(),
                new Class<?>[]{interfaceClass},
                new RpcInvocationHandler());
    }

    class RpcInvocationHandler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            /**
             * 创建并初始化 RPC 请求
             */
            RpcRequest request = new RpcRequest();
            request.setRequestId(UUID.randomUUID().toString());
            request.setClassName(method.getDeclaringClass().getName());
            request.setMethodName(method.getName());
            request.setParameterTypes(method.getParameterTypes());
            request.setParameters(args);

            /**
             * 发现服务
             */
            if (serviceDiscovery != null) {
                serverAddress = serviceDiscovery.discover();
            }

            String[] array = serverAddress.split(":");
            String host = array[0];
            int port = Integer.parseInt(array[1]);
            /**
             * 初始化 RPC 客户端
             */
            RpcClient client = new RpcClient(host, port);
            /**
             * 通过 RPC 客户端发送 RPC 请求并获取 RPC 响应
             */
            RpcResponse response = client.send(request);
            if (response.getError() != null) {
                throw response.getError();
            } else {
                return response.getResult();
            }
        }
    }
}
