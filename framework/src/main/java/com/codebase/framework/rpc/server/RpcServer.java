package com.codebase.framework.rpc.server;

import com.codebase.framework.rpc.registry.ServiceRegistry;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Xiaojun.Cheng
 * @date 2019/1/19
 */
public class RpcServer implements ApplicationContextAware, InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(RpcServer.class);

    private final String serverAddress;

    private final ServiceRegistry serviceRegistry;

    /**
     * 存放接口名与服务对象之间的映射关系
     */
    private Map<String, Object> handlerMap = new HashMap<>();

    public RpcServer(String serverAddress, ServiceRegistry serviceRegistry) {
        this.serverAddress = serverAddress;
        this.serviceRegistry = serviceRegistry;
    }

    @Override
    public void afterPropertiesSet() {
        Thread thread = new Thread(() -> {
            EventLoopGroup bossGroup = new NioEventLoopGroup();
            EventLoopGroup workerGroup = new NioEventLoopGroup();
            try {
                ServerBootstrap bootstrap = new ServerBootstrap();
                bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                        .childHandler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            public void initChannel(SocketChannel channel) {
                                channel.pipeline()
                                        /**
                                         * 将 RPC 请求进行解码（为了处理请求）
                                         */
                                        .addLast(new RpcDecoder(RpcRequest.class))
                                        /**
                                         * 将 RPC 响应进行编码（为了返回响应）
                                         */
                                        .addLast(new RpcEncoder(RpcResponse.class))
                                        /**
                                         * 处理 RPC 请求
                                         */
                                        .addLast(new RpcHandler(handlerMap));
                            }
                        })
                        .option(ChannelOption.SO_BACKLOG, 128)
                        .childOption(ChannelOption.SO_KEEPALIVE, true);

                String[] array = serverAddress.split(":");
                String host = array[0];
                int port = Integer.parseInt(array[1]);

                ChannelFuture future = bootstrap.bind(host, port).sync();
                LOGGER.debug("server started on port {}", port);

                if (serviceRegistry != null) {
                    //注册服务地址
                    serviceRegistry.register(serverAddress);
                }

                future.channel().closeFuture().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                workerGroup.shutdownGracefully();
                bossGroup.shutdownGracefully();
            }
        });
        thread.start();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        /**
         * 获取所有带有 RpcService 注解的 Spring Bean
         */
        Map<String, Object> serviceBeanMap = applicationContext.getBeansWithAnnotation(RpcService.class);
        if (!serviceBeanMap.isEmpty()) {
            for (Object serviceBean : serviceBeanMap.values()) {
                RpcService service = serviceBean.getClass().getAnnotation(RpcService.class);
                String interfaceName = service.value().getName();
                handlerMap.put(interfaceName, serviceBean);
            }
        }
    }
}
