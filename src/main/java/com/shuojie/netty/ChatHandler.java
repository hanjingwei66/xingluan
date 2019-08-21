package com.shuojie.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    //保存所有客户端连接
    private static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:MM");
    // 当Channel中有新的事件消息会自动调用
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        //接收到数据自动调用
        String text = msg.text();
        System.out.println("接收到的消息数据为：" +text);
        for (Channel client : clients) {
            //将消息发送到所有客户端
            client.writeAndFlush(new TextWebSocketFrame(sdf.format(new Date())+"：" + text));
        }
    }
    //当有新的客户端连接服务器之后，自动调用这个方法
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception{
        clients.add(ctx.channel());
    }
}
