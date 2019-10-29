package com.shuojie.nettyService.Handler;

import com.alibaba.fastjson.JSONObject;
import com.shuojie.dao.sensorMappers.DistanceSensorMapper;
import com.shuojie.domain.sensorModle.BaseSensor;
import com.shuojie.mqttClient.PubMsg;
import com.shuojie.mqttClient.SubMsg;
import com.shuojie.utils.nettyUtil.LoginCheckUtil;
import com.shuojie.utils.snowFlake.SnowFlake;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*传感器handler*/
@Component
@ChannelHandler.Sharable
public class SensorHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    //    private static RedisService redisService;
//    static {
//        redisService = SpringUtil.getBean(RedisService.class);
//    }
    private boolean flag;
    //    @Autowired
//    private RedisService redisService;
    @Resource
    private DistanceSensorMapper distanceSensorMapper;
    //订阅
    @Autowired
    private SubMsg subMsg;

    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;
    //过期时间60秒
    @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {

//        InetSocketAddress inetSocketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
//        String ip = inetSocketAddress.getHostName();
//        System.out.println("收到id为"+ctx.channel().id().asLongText()+"ip为"+ip+"发来的消息1："+msg.text());
        JSONObject json = JSONObject.parseObject(msg.text().toString());//json字符串转json对象
        String command = json.getString("command");
        switch (command) {
            case "sensor_check"://检测数量
                if(LoginCheckUtil.hasLogin(ctx.channel())){
                List<BaseSensor> list = new ArrayList();
                for (int i = 0; i < 6; i++) {
                    Integer id = (int) (Math.random() * 100);
                    Integer sesorType=0;
                    if(i<3){
                        sesorType=0;
                    }else {
                        sesorType=1;
                    }
                    //Integer sesorType = ((int) (Math.random() * 4));
                    Integer power = 80 + ((int) (Math.random() * 20));
                    Integer status = 0;
                    Integer signal = 2 + (int) (Math.random() * 3);
                    String angle = (int) (Math.random() * 15) + "";
                    Double distance = Math.random() * 100;
                    BaseSensor s = new BaseSensor();
                    s.setSensorId(SnowFlake.nextId());
                    s.setPower(power);
                    s.setSensorName("Sesor" + id);
                    s.setSensorType(sesorType);
                    s.setStatus(status);
                    s.setSignal(signal);
//                    s.setAngle(angle);
//                    s.setDistance(distance);
                    list.add(s);
                }
                Map map = new HashMap();
                map.put("list", list);
                map.put("command", "sensor_check");
                String sesorList = JSONObject.toJSONString(map);
                ctx.writeAndFlush(new TextWebSocketFrame(sesorList));
//                System.out.println("buf.refCnt()" + buf.refCnt());
                }
                return;
            case "sensor_allow"://检测
                if (flag) {

                }
//                String redisauthcode= redisService.get("portal:authCode:"+"demo/topics");//REDIS_KEY_PREFIX_AUTH_CODE
//                ctx.channel().writeAndFlush(new TextWebSocketFrame(redisauthcode));
//                System.out.println("value"+redisauthcode);
                break;
            case "sensor_start":
//                REDIS_KEY_PREFIX_AUTH_CODE
//                redisService.set( "portal:authCode:"+"123", ctx);

//                new Timer("testTimer").schedule(new TimerTask() {
//                    @Override
//                    public void run() {
//                        String rediscode= redisService.get("portal:authCode:"+"demo/topics");
//                        ctx.channel().writeAndFlush(new TextWebSocketFrame(rediscode));
                System.out.println("123");
//                    }
//                }, 100,100);

                break;
            case "sensor_init":
                //网关ID+设备ID+数据长度+要发送的数据
              //  2595fcd0是网关ID，00015b6c是设备ID，07为数据长度，ff fe为识别码，没实际意义，后面五个数据分别代表
            //激光器是否使用（01开00关）+超声波测距是否使用（01开00关）+十轴是否使用（01开00关）+
                // 上传间隔时间（计算方式为=0a*100ms=1s）+放置方式（00设备水平放置，01设备垂直放置）。
                subMsg.runsub("client-id-0","demo/test");
                byte []bytes4={0x25,(byte) 0x95,(byte) 0xfc,(byte) 0xd0,
                        0x00,0x01,0x5b,0x15
                        ,0x07, (byte)0xff, (byte) 0xfe,
                        0x01,0x01,0x01,0x0a,0x00};
                byte []bytes6={0x25,(byte) 0x95,(byte) 0xfc,(byte) 0xd0,
                        0x00,0x01,0x5a,0x6a
                        ,0x07, (byte)0xff, (byte) 0xfe,
                        0x01,0x00,0x01,0x0a,0x00};
                PubMsg.publish(bytes4,"client-id-0","demo/test");
//                PubMsg.publish(bytes6,"client-id-0","demo/test");


                break ;
            case "sensor_test":
                System.out.println("112");

                byte [] bytes5={0x01,0x03,0x25,(byte)0x95,(byte)0xFC,(byte)0xD0,0x00,0x01,0x5B
                        ,0x6C,0x00,0x04,0x02,0x0D,0x15,0x00,
                        0x00,0x00,0x5D,(byte)0x9E,(byte)0xD3,
                        (byte)0x85,0x00,0x00,0x0A,0x00,0x2A,0x30,0x30,0x35,0x30,0x31,0x33,0x30,
                        0x30,0x35,0x30,0x31,0x32
                    ,0x33,0x31,0x39,0x32,0x36,0x31,0x00,0x00,(byte)0xD0,(byte)0xFF,(byte)0xF9,
                        0x07,(byte)0xD3,(byte)0xFF,0x00,0x00,0x00,0x00,0x00,0x00,0x02,0x00,
                        (byte)0xF6,(byte)0xFF,0x0B
                    ,(byte)0xFE,0x54,0x00,0x00,0x00};
                PubMsg.publish(bytes5,"client-id-0","demo/test");
                System.out.println("1");
        }

    }


    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        super.channelWritabilityChanged(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
