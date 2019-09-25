package com.shuojie.mqttClient;


import com.shuojie.dao.sensorMappers.SensorMapper;
import com.shuojie.domain.sensorModle.DistanceSensor;
import com.shuojie.domain.sensorModle.SensorTitle;
import com.shuojie.nettyService.Handler.TextWebSocketFrameHandler;
import com.shuojie.service.sensorService.AsyncService;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.ByteBuffer;
import java.text.MessageFormat;

@Slf4j
@Component
public class Mqttclien  {
    @Autowired
    private SensorMapper sensorMapper;
//    @Autowired
//    private RedisService redisService;
    @Autowired
    private TextWebSocketFrameHandler textWebSocketFrameHandler;
    @Autowired
    private AsyncService asyncService;
//    @Resource
//    private DistanceSensorMapper distanceSensorMapper;

//    @Autowired
//    private TextWebSocketFrameHandler text;
    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;
    //过期时间60秒
    @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;

//    @PostConstruct
    public void start() throws Exception {
        String broker = "tcp://47.98.193.195:1883";
        String clientId = "JavaSample12";
        //Use the memory persistence
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setUserName("suojie");
            connOpts.setPassword("123456".toCharArray());
            connOpts.setCleanSession(true);
            System.out.println("Connecting to broker:" + broker);
            sampleClient.connect(connOpts);
            System.out.println("Connected");
            String topic = "demo/test";
            System.out.println("Subscribe to topic:" + topic);
            sampleClient.subscribe(topic);
            sampleClient.setCallback(new MqttCallback() {
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    String theMsg = MessageFormat.format("{0} is arrived for topic {1}.", new String(message.getPayload()), topic);
//                    redisService.set(REDIS_KEY_PREFIX_AUTH_CODE + topic,new String(message.getPayload()));
//                    redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE + );
                    System.out.println(theMsg);
                    System.out.println("getload"+new String(message.getPayload()));
                    byte[] payload = message.getPayload();
//                    String s = EncodUtil.BinaryToHexString(payload);
//                    byte[] bytes = s.getBytes();
                    ByteBuffer wrap = ByteBuffer.wrap(payload);

                    if(payload!=null&&payload.length>=27){
                        SensorTitle tt=new SensorTitle();
                        tt.setVersion(wrap.get(0));
                        tt.setComand(wrap.get(1));
                        tt.setJizhongqid(wrap.getInt(2));
                        tt.setJiedianid(wrap.getInt(6));
                        tt.setDuanid(wrap.getShort(10));
                        tt.setTongdao(wrap.get(12));
                        tt.setSnr(wrap.get(13));
                        tt.setRssi0(wrap.get(14));
                        tt.setRssi1(wrap.get(15));
                        tt.setNc(wrap.get(16));
                        tt.setNc1(wrap.get(17));
                        tt.setTime(wrap.getInt(18));
                        tt.setZdzxqk(wrap.get(22));
                        tt.setNum(wrap.getShort(23));
                        tt.setSensorDataLength(wrap.getShort(25));
//                        byte [] contant=new byte[tt.getDataLength()];
//                        if(contant!=null&&contant[0]!=0&&payload.length>27){
//                            for (int i = 0; i <payload.length -27 ; i++) {
//                                contant[i]=payload[i+27];
//                            }
////                            tt.setData(new String(contant));
//                        }

//                        asyncService.executeAsync(tt);
                        sensorMapper.insert(tt);
                        System.out.println(tt.toString());
                    }

//                    try {
//                        byte [] contant=new byte[tt.getDataLength()];
//                        for (int i = 0; i <payload.length -27 ; i++) {
//                            contant[i]=payload[i+27];
//                        }
//                        tt.setData(new String(contant));
//                    }catch (Exception e){
//                        e.printStackTrace();
//                    }


//                    System.out.println(tt.toString());
//                    for (int i = 0; i < payload.length; i++) {
//                       System.out.println(i+"我要死了"+bytes[i]);
//
//                    }



                    log.info("getload"+new String(message.getPayload())+"id"+message.getId()+message.getQos());
//            ConcurrentMap<Object, Channel> serverChannels = textWebSocketFrameHandler.getServerChannels();
//            Channel s = serverChannels.get("s");
//            s.writeAndFlush(new TextWebSocketFrame(theMsg));

                   ChannelGroup channels= textWebSocketFrameHandler.channels;
//                    Channel incoming = ctx.channel();
//                    channels.add(ctx.channel());
//                    textWebSocketFrameHandler.
//                    if(){
//
//                    }
                    DistanceSensor sensor =new DistanceSensor();
                    sensor.setStartTime();
//                    try {
                        for (Channel channel : channels) {
                            ChannelId id = channel.id();
                            Channel channel1 = channels.find(id);
                            channel1.writeAndFlush(new TextWebSocketFrame( "123"));
                        }
//                    }catch (Exception e){
//                        e.printStackTrace();
//                    }

//                    sensor.setSensorId( SnowFlake.nextId());
//                    sensor.setDistance(45.4545);
//                    sensor.setPower(95);
//                    sensor.setSensorType(1);
//                    sensor.setSignal(1);
//                    sensor.setEndTime();
//                    distanceSensorMapper.insert(sensor);

                }

                public void deliveryComplete(IMqttDeliveryToken token) {
                }

                public void connectionLost(Throwable throwable) {
                }
            });


            String content = "123456";
//            int jizhongqid = tt.getJizhongqid();
//            int jiedianid = tt.getJiedianid();
            int jizhongqid = 88879;
            int jiedianid = 630586576;


            byte[] bytes = EncodUtil.intToByteArray(jizhongqid);//集中器id
            byte[] bytes1 = EncodUtil.intToByteArray(jiedianid);//终端id

            byte bytes2= (byte) content.getBytes().length;//有效数据字节长度
            System.out.println(bytes2);
//            //合并数组
            byte[] bt3 = new byte[bytes.length+bytes1.length+1];
            for (int i = 0; i < bytes.length; i++) {
                bt3[i]=bytes[i];
            }
            for (int i = 0; i < bytes1.length; i++) {
                bt3[i+bytes.length]=bytes1[i];
            }
            bt3[bt3.length-1]=bytes2;
            byte[] bytes3 = EncodUtil.byteMerger(bt3, content.getBytes());
//            System.arraycopy(bytes, 0, bt3, 0, bytes.length);
//            System.arraycopy(bytes1, 0, bt3, bytes.length, bytes1.length);
//            bt3[bt3.length]=bytes2;
//            byte[] bytes3 = EncodUtil.byteMerger(bt3, content.getBytes());
            int qos = 0;
//            System.out.println("Publishing message:" + content);
            MqttMessage message = new MqttMessage(bytes3);
            message.setQos(qos);
            sampleClient.publish(topic, message);
//            System.out.println("Message published");

        } catch (MqttException me) {
            System.out.println("reason" + me.getReasonCode());
            System.out.println("msg" + me.getMessage());
            System.out.println("loc" + me.getLocalizedMessage());
            System.out.println("cause" + me.getCause());
            System.out.println("excep" + me);
            me.printStackTrace();
        }
    }

}
