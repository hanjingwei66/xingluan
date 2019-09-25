package com.shuojie.nettyService;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.InetSocketAddress;

//websocket长连接示例
@Component
public class MyServer {
    @Autowired
    private WebSocketChannelInitializer webSocketChannelInitializer;
    private Logger logger = LoggerFactory.getLogger(MyServer.class);
    //配置服务端NIO线程组
    private final EventLoopGroup parentGroup = new NioEventLoopGroup(); //NioEventLoopGroup extends MultithreadEventLoopGroup Math.max(1, SystemPropertyUtil.getInt("io.netty.eventLoopThreads", NettyRuntime.availableProcessors() * 2));
    private final EventLoopGroup childGroup = new NioEventLoopGroup();
    private Channel channel;

    public ChannelFuture bing(InetSocketAddress address) {
        ChannelFuture channelFuture = null;
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(parentGroup, childGroup)
                    .channel(NioServerSocketChannel.class)    //非阻塞模式
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childHandler(webSocketChannelInitializer);

            channelFuture = b.bind(address).syncUninterruptibly();
            channel = channelFuture.channel();
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            if (null != channelFuture && channelFuture.isSuccess()) {
                logger.info("Netty正在启动。。。 ");
            } else {
                logger.error("itstack-demo-netty server start error. ");
            }
        }
        return channelFuture;
    }

    public void destroy() {
        if (null == channel) return;
        channel.close();
        parentGroup.shutdownGracefully();
        childGroup.shutdownGracefully();
    }

    public Channel getChannel() {
        return channel;
    }

//
//    public void start() throws Exception {
//
//        EventLoopGroup bossGroup = new NioEventLoopGroup();
//        EventLoopGroup wokerGroup = new NioEventLoopGroup();
//
//        try {
//            ServerBootstrap serverBootstrap = new ServerBootstrap();
//            serverBootstrap.group(bossGroup, wokerGroup)
//                    .channel(NioServerSocketChannel.class)
//                    .option(ChannelOption.SO_BACKLOG, 1024)
////                    .option(ChannelOption.TCP_NODELAY, Boolean.valueOf(true))
////                    .option(ChannelOption.SO_KEEPALIVE,true)
//                    .handler(new LoggingHandler(LogLevel.TRACE))//TRACE
//                    .childHandler(webSocketChannelInitializer);
//            ChannelFuture channelFuture = serverBootstrap
//                    .bind(new InetSocketAddress("",80)).sync();
//            System.out.println("启动成功");
////            channelFuture.channel().closeFuture().sync();
//
//        } finally {
//            System.out.println("关闭");
////            bossGroup.shutdownGracefully();
////            wokerGroup.shutdownGracefully();
//
//        }
//
//    }

}
