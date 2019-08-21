package com.shuojie.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.stereotype.Component;

@Component
public class WebSocketServer {

    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private ServerBootstrap server;
    private ChannelFuture funture;

    public void start(){
        funture = server.bind(9001);
        System.out.println("netty server -  启 动 成 功");
    }
    public WebSocketServer(){
       bossGroup = new NioEventLoopGroup();
       workerGroup = new NioEventLoopGroup();

       server = new ServerBootstrap();
       server.group(bossGroup,workerGroup)
               .channel(NioServerSocketChannel.class)
               .childHandler(new WebSocketInitializer());
    }



















   /*
        public static void main(String[] args) {
       //创建两个线程池
        NioEventLoopGroup mainGrp = new NioEventLoopGroup();//主线程
        NioEventLoopGroup subGrp = new NioEventLoopGroup();//从线程

        try {
            //创建Netty服务器启动对象
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //初始化服务器启动对象
            serverBootstrap
                    //指定使用上面创建的两个线程池
                    .group(mainGrp,subGrp)
                    //指定Netty通道类型
                    .channel(NioServerSocketChannel.class)
                    //指定通道初始化器用来加载Channel收到事件消息后
                    //如何进行业务处理
                    .childHandler(new WebSocketInitializer());
            //绑定服务器端口，以同步的方式启动服务器
            ChannelFuture future = serverBootstrap.bind(9090).sync();
            //等待服务器关闭
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //手动关闭服务器
            mainGrp.shutdownGracefully();System.out.println(mainGrp +"已断开连接");
            subGrp.shutdownGracefully(); System.out.println(subGrp +"已断开连接");
        }
    }*/
}
