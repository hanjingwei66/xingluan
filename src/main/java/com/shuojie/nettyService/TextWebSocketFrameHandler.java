package com.shuojie.nettyService;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;

//处理文本协议数据，处理TextWebSocketFrame类型的数据，websocket专门处理文本的frame就是TextWebSocketFrame
@Slf4j
public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    //保存所有客户端连接
    private static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    //读到客户端的内容并且向客户端去写内容
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        System.out.println("收到"+ctx.channel().id().asLongText()+"发来的消息："+msg.text());
        for (Channel client : clients) {
            //将消息发送到所有客户端
            client.writeAndFlush(new TextWebSocketFrame(msg.text()));
        }
        /**
         * writeAndFlush接收的参数类型是Object类型，但是一般我们都是要传入管道中传输数据的类型，比如我们当前的demo
         * 传输的就是TextWebSocketFrame类型的数据
         */
//        ctx.channel().writeAndFlush(new TextWebSocketFrame("服务时间："+LocalDateTime.now()));
    }

    //每个channel都有一个唯一的id值
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //打印出channel唯一值，asLongText方法是channel的id的全名
        System.out.println("handlerAdded："+ctx.channel().id().asLongText()+"你好世界");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerRemoved：" + ctx.channel().id().asLongText());
    }
    /**
     * 活跃的通道  也可以当作用户连接上客户端进行使用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        log.info("【channelActive】=====>"+ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("异常发生");
        ctx.close();
        ctx.channel().close();
    }
}
