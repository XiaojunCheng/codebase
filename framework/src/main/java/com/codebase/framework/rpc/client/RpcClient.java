package com.codebase.framework.rpc.client;

import com.codebase.framework.rpc.server.RpcDecoder;
import com.codebase.framework.rpc.server.RpcEncoder;
import com.codebase.framework.rpc.server.RpcRequest;
import com.codebase.framework.rpc.server.RpcResponse;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Xiaojun.Cheng
 * @date 2019/1/19
 */
public class RpcClient extends SimpleChannelInboundHandler<RpcResponse> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RpcClient.class);

    private final String host;
    private final int port;

    private RpcResponse response;

    private final Object obj = new Object();

    public RpcClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, RpcResponse response) throws Exception {
        this.response = response;
        synchronized (obj) {
            /**
             * 收到响应，唤醒线程
             */
            obj.notifyAll();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        LOGGER.error("client caught exception", cause);
        ctx.close();
    }

    public RpcResponse send(RpcRequest request) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel channel) throws Exception {
                            channel.pipeline()
                                    /**
                                     * 将 RPC 请求进行编码（为了发送请求）
                                     */
                                    .addLast(new RpcEncoder(RpcRequest.class))
                                    /**
                                     * 将 RPC 响应进行解码（为了处理响应）
                                     */
                                    .addLast(new RpcDecoder(RpcResponse.class))
                                    /**
                                     * 使用 RpcClient 发送 RPC 请求
                                     */
                                    .addLast(RpcClient.this);
                        }
                    })
                    .option(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture future = bootstrap.connect(host, port).sync();
            future.channel().writeAndFlush(request).sync();

            synchronized (obj) {
                /**
                 * 未收到响应，使线程等待
                 */
                obj.wait();
            }

            if (response != null) {
                future.channel().closeFuture().sync();
            }
            return response;
        } finally {
            group.shutdownGracefully();
        }
    }
}
