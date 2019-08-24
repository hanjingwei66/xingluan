package com.shuojie.nettyService;

import com.alibaba.fastjson.JSONObject;
import com.shuojie.domain.User;
import com.shuojie.service.IUserServer;
import com.shuojie.service.UserMerberService;
import com.shuojie.utils.vo.ReturnUser;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

//处理文本协议数据，处理TextWebSocketFrame类型的数据，websocket专门处理文本的frame就是TextWebSocketFrame
@Slf4j
public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    @Resource(name = "userServiceImpl")
    private IUserServer userServer;
    @Autowired
    private UserMerberService usermerberservice;
    //保存所有客户端连接
    private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    //读到客户端的内容并且向客户端去写内容
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        System.out.println("收到"+ctx.channel().id().asLongText()+"发来的消息："+msg.text());
        JSONObject json = JSONObject.parseObject(msg.text().toString());
        String command = json.getString("command");
        switch (command){
            case "login":
                System.out.println("loging");
                User user =new User();
                user.setMobile(json.getString("mobile"));
                user.setPassword(json.getString("password"));
                System.out.println(user.toString());
                ReturnUser result = userServer.toLogin(user);
                if(result!=null){
                    channels.add(ctx.channel());//登陆成功加入管道
                }else{
                    ctx.channel().writeAndFlush("{msg:}");
                }

                break;
            case "register" :
                System.out.println("register");
                break;
            case "sendMsg" :
                System.out.println("sendMsg");
                break;
            case "updatePassword" :
                System.out.println("updatePassword");
                break;
        }

//        if(command.equals("login")){
//
//            ReturnUser result = userServer.toLogin(user);
//        }
        for (Channel channel  : channels) {
            //将消息发送到所有客户端
//            channel.writeAndFlush(new TextWebSocketFrame(msg.text()));
            channel.writeAndFlush("发送所有建立连接设备");
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
        Channel incoming = ctx.channel();
        for (Channel channel : channels) {
            channel.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " 加入\n");
        }
        channels.add(ctx.channel());
        System.out.println("handlerAdded："+ctx.channel().id().asLongText()+"你好世界");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        for (Channel channel : channels) {
            channel.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " 离开\n");
        }
        channels.remove(ctx.channel());
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
//        ctx.close();
//        ctx.channel().close();
    }
}
