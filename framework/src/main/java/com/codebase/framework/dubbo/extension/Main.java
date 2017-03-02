package com.codebase.framework.dubbo.extension;

import com.alibaba.dubbo.common.extension.ExtensionFactory;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.rpc.Protocol;

public class Main {

    public static void main(String[] args) {
        ExtensionLoader<ExtensionFactory> factoryLoader = ExtensionLoader.getExtensionLoader(ExtensionFactory.class);
        ExtensionFactory factory = factoryLoader.getAdaptiveExtension();
        System.out.println(factory.getClass());

        ExtensionLoader<Protocol> protocolLoader = ExtensionLoader.getExtensionLoader(Protocol.class);
        Protocol protocol = protocolLoader.getAdaptiveExtension();
        System.out.println(protocol.getClass());
        protocol = protocolLoader.getExtension("http");
        System.out.println(protocol.getClass());
    }

}
