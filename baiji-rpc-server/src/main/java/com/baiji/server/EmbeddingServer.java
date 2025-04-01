package com.baiji.server;

import com.baiji.server.bo.MethodWrapper;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.util.Map;

public class EmbeddingServer {
    private Map<String, MethodWrapper> methodMap;
    private final int port;

    public EmbeddingServer(Map<String, MethodWrapper> methodMap, int port) {
        this.methodMap = methodMap;
        this.port = port;
    }

    public void run() throws Exception {
        // 配置服务器
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ChannelPipeline p = ch.pipeline();

                            // 添加HTTP编解码器
                            p.addLast(new HttpServerCodec());

                            // 聚合HTTP请求内容
                            p.addLast(new HttpObjectAggregator(65536));

                            // 添加自定义HTTP处理器
                            p.addLast(new HttpServerHandler(methodMap));
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            // 启动服务器
            ChannelFuture f = b.bind(port).sync();
            System.out.println("HTTP服务器启动，监听端口: " + port);

            // 等待服务器socket关闭
            f.channel().closeFuture().sync();
        } finally {
            // 优雅关闭
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
