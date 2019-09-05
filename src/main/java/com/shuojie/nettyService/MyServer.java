package com.shuojie.nettyService;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.InetSocketAddress;

//websocket长连接示例
@Component
public class MyServer {
    @Autowired
    private WebSocketChannelInitializer webSocketChannelInitializer;
    @PostConstruct
    public void start() throws Exception{

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup wokerGroup = new NioEventLoopGroup();

        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,wokerGroup)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .option(ChannelOption.TCP_NODELAY, Boolean.valueOf(true))
                    .option(ChannelOption.SO_KEEPALIVE,true)
                    .handler(new LoggingHandler(LogLevel.TRACE))//TRACE
                    .channel(NioServerSocketChannel.class)
                    .childHandler(webSocketChannelInitializer);
            ChannelFuture channelFuture = serverBootstrap
                    .bind(new InetSocketAddress(8091)).sync();
            channelFuture.channel().closeFuture().sync();
            System.out.println("启动成功");
        }finally {
            bossGroup.shutdownGracefully();
            wokerGroup.shutdownGracefully();
        }

    }

}
