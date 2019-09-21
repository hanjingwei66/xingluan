package com.shuojie.nettyService.Handler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shuojie.domain.Contact;
import com.shuojie.domain.User;
import com.shuojie.domain.system.SysContact;
import com.shuojie.service.ContactService;
import com.shuojie.service.IUserService;
import com.shuojie.service.UserMerberService;
import com.shuojie.service.sysService.SysContactService;
import com.shuojie.utils.vo.Result;
import com.shuojie.utils.vo.ReturnUser;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import io.netty.util.internal.PlatformDependent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

//处理文本协议数据，处理TextWebSocketFrame类型的数据，websocket专门处理文本的frame就是TextWebSocketFrame
@Slf4j
@Component
@ChannelHandler.Sharable
public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    public static TextWebSocketFrameHandler textWebSocketFrameHandler;

    @Autowired
    private UserMerberService usermerberservice;
    @Autowired
    private IUserService userServer;
    @Autowired
    private  ContactService contactServer;
    @Autowired
    private SysContactService sysContactService;
//    private static UserMerberService usermerberservice;
//    private static IUserService userServer;
//    private static ContactService contactServer;
   public static ChannelGroup  channels= new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

//    static {
////        usermerberservice = SpringUtil.getBean(UserMerberService.class);
////        userServer = SpringUtil.getBean(IUserService.class);
////        contactServer = SpringUtil.getBean(ContactService.class);
//
//   }
//    public TextWebSocketFrameHandler() { }
//    @PostConstruct
//    public void init() {
//        textWebSocketFrameHandler = this;
//        textWebSocketFrameHandler.usermerberservice = this.usermerberservice;
//        textWebSocketFrameHandler.userServer = this.userServer;
//
//    }


    public ConcurrentMap<Object, Channel> getServerChannels() {
        return serverChannels;
    }

    private ConcurrentMap<Object, Channel> serverChannels = PlatformDependent.newConcurrentHashMap();
    //保存所有客户端连接
//private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    //读到客户端的内容并且向客户端去写内容
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        channels.add(ctx.channel());
        ByteBuf buf = ctx.alloc().directBuffer();//从 channel 获取 ByteBufAllocator 然后分配一个 ByteBuf
        System.out.println("收到"+ctx.channel().id().asLongText()+"发来的消息："+msg.text());
//        if(msg instanceof WebSocketFrame){
//
//        }else{
////            buf.retain();//检查引用计数器是否是 1
////            ctx.fireChannelRe(msg);
//        }
        JSONObject json = JSONObject.parseObject(msg.text().toString());//json字符串转json对象
        String command = json.getString("command");
        User user =new User();
        Contact contact = new Contact();

        if(!command.substring(0,4).equals("api_")){
            buf.retain();//检查引用计数器是否是 1
            msg.retain();
            ctx.fireChannelRead(msg);
        }
        switch (command){
            case "api_login":
                System.out.println("loging");
                user.setMobile(json.getString("mobile"));
                user.setPassword(json.getString("password"));
                System.out.println(user.toString());
                ReturnUser result = userServer.toLogin(user);
                result.setCommand("api_login");
                String loginRespone = JSONObject.toJSONString(result);//json对象解析为json字符串
                ctx.channel().writeAndFlush(new TextWebSocketFrame(loginRespone));
                if(result.getCode()==200){
                  //登陆成功加入管道
//                    ctx.channel().writeAndFlush(new TextWebSocketFrame());
                }else{
//                    ctx.channel().writeAndFlush(new TextWebSocketFrame());
                }

                break;
            //注册
            case "api_register" :
                user.setMobile(json.getString("mobile"));
                user.setPassword(json.getString("password"));
                user.setYzm(json.getString("yzm"));
                user.setUsername(json.getString("username"));
                user.setIdNumber(json.getString("idNumber"));
//                user.setPosition();//职位
//                user.setAreaname(login.getAreaname());//所属地区
                Result results =userServer.register(user);
                results.setCommand("api_register");

                String registerReponse = JSONObject.toJSONString(results);
                ctx.channel().writeAndFlush(new TextWebSocketFrame(registerReponse));
                break;
            case "api_sendMsg" :
                String telephone =json.getString("mobile").toString();
                try {
                    Result result1 = usermerberservice.sendMsg(telephone);
                    result1.setCommand("api_sendMsg");
                    String res = JSONObject.toJSONString(result1);
                    ctx.channel().writeAndFlush(new TextWebSocketFrame(res));
                }catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            //忘记密码
            case "api_updatePassword" :
                user.setMobile(json.getString("mobile"));
                user.setPassword(json.getString("password"));
                user.setYzm(json.getString("yzm"));
                Result res = userServer.updateUserPassworld(user);
                String updatePasswordReponse = JSONObject.toJSONString(res);
                ctx.channel().writeAndFlush(new TextWebSocketFrame(updatePasswordReponse));
                System.out.println("updatePassword");
                break;
            //修改密码
            case "api_xiugaiPassword" :
                user.setId(json.getInteger("id"));
                user.setMobile(json.getString("mobile"));
                user.setOldPassword(json.getString("oldpassword"));
                user.setPassword(json.getString("password"));
                user.setYzm(json.getString("yzm"));
                Result response = userServer.xiugaiUserPassworld(user);
                String xiugaiPasswordReponse = JSONObject.toJSONString(response);
                ctx.channel().writeAndFlush(new TextWebSocketFrame(xiugaiPasswordReponse));
                System.out.println("updatePassword");
                break;
            //添加留言
            case "api_insertContact" :
                contact.setId(Integer.valueOf(json.getString("id")));
                contact.setContactText(new String(json.getString("contactText")));
                Result con = contactServer.insertContact(contact);
                String insertContactResponse = JSONObject.toJSONString(con);
                ctx.channel().writeAndFlush(new TextWebSocketFrame(insertContactResponse));
                break;
            case "api_selectSysMsg" :
                Integer id = json.getInteger("id");
                List<SysContact> list = sysContactService.selectById(id);
                Map map=new HashMap();
                map.put("data",list);
                map.put("command","api_selectSysMsg");
                String contectlist = JSONObject.toJSONString(map);
                System.out.println(contectlist);
                ctx.channel().writeAndFlush(new TextWebSocketFrame(contectlist));
                break;
            case "api_deleteSysMsg" :
                //JSONArray array = json.getJSONArray("sysContactId");
                JSONArray array = json.getJSONArray("sysContactId");

                try {
                    for(int i=0;i<array.size();i++){
                        sysContactService.deleteById(array.getInteger(i));
                    }

                    Result result1=new Result(200,"SUCCESS","api_deleteSysMsg");
                    String respon = JSONObject.toJSONString(result1);

                    ctx.channel().writeAndFlush(new TextWebSocketFrame(respon));
                }catch (Exception e) {
                    e.printStackTrace();

                }
                break;
            case "api_updateStatus" :
                Integer array1= json.getInteger("sysContactId");
                try {
                    sysContactService.updateStatus(array1);
                    Result result1 = new Result(200, "SUCCESS", "api_updateStatus");
                    String respon = JSONObject.toJSONString(result1);
                    ctx.channel().writeAndFlush(new TextWebSocketFrame(respon));
                }catch (Exception e) {
                    e.printStackTrace();

                }
                break;
//                sysContactService.deleteById(sysContactId);
//                System.out.println(contectlist);
//                ctx.channel().writeAndFlush(new TextWebSocketFrame(contectlist));

            //事件经纬度存储
        }

//        if(command.equals("login")){
//
//            ReturnUser result = userServer.toLogin(user);
//        }
        for (Channel channel  : channels) {
            //将消息发送到所有客户端
//            channel.writeAndFlush(new TextWebSocketFrame(msg.text()));

    }
        channels.writeAndFlush("发送所有建立连接设备");

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
        channels.add(ctx.channel());
        for (Channel channel : channels) {
            channel.writeAndFlush(new TextWebSocketFrame("[SERVER] - " + incoming.remoteAddress() + " 加入\n"));
            System.out.println("123");
        }

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

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    /**
     * 活跃的通道  也可以当作用户连接上客户端进行使用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("【channelActive】=====>"+ctx.channel());
    }
//
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        System.out.println("异常发生");
//        ctx.close();
//        ctx.channel().close();
//    }
}
