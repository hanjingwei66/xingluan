package com.shuojie.mqttClient;

import com.shuojie.dao.sensorMappers.SensorMapper;
import com.shuojie.domain.sensorModle.ZullProperty;
import com.shuojie.nettyService.Handler.TextWebSocketFrameHandler;
import com.shuojie.service.sensorService.SensorAsyncService;
import com.shuojie.service.sensorService.SensorProperty;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SubMsg {

//	 private static String topic = "$share/testgroup/wyptest1";
//	 private static String topic = "$queue/wyptest1";
//	 private static String topic = "wyptest1";
     private static int qos = 2;
     private static String broker = "tcp://47.98.193.195:1883";
     private static String userName = "suojie";
     private static String passWord = "123456";

    @Autowired
    private static SensorProperty sensorProperty;
    @Autowired
    private SensorMapper sensorMapper;
    //    @Autowired
//    private RedisService redisService;
    @Autowired
    private static TextWebSocketFrameHandler textWebSocketFrameHandler;
    @Autowired
    private SensorAsyncService asyncService;
//    @Resource
//    private DistanceSensorMapper distanceSensorMapper;

    //    @Autowired
//    private TextWebSocketFrameHandler text;
    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;
    //过期时间60秒
    @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;

    private static MqttClient connect(String clientId) throws MqttException{

    	 MemoryPersistence persistence = new MemoryPersistence();
    	 MqttConnectOptions connOpts = new MqttConnectOptions();
//    	 String[] uris = {"tcp://10.100.124.206:1883","tcp://10.100.124.206:1883"};
    	 connOpts.setCleanSession(false);
         connOpts.setUserName(userName);
         connOpts.setPassword(passWord.toCharArray());
         connOpts.setConnectionTimeout(10);
         connOpts.setKeepAliveInterval(20);
//         connOpts.setServerURIs(uris);
//         connOpts.setWill(topic, "close".getBytes(), 2, true);
         MqttClient mqttClient = new MqttClient(broker, clientId, persistence);
         mqttClient.connect(connOpts);
    	 return mqttClient;
     }

     public static void sub(MqttClient mqttClient,String topic) throws MqttException{
         int[] Qos  = {qos};
         String[] topics = {topic};
         mqttClient.subscribe(topics, Qos);
     }


    public static void runsub(String clientId, String topic) throws MqttException{
    	MqttClient mqttClient = connect(clientId);
        MemoryPersistence persistence = new MemoryPersistence();
        ZullProperty zullProperty=new ZullProperty();
    	if(mqttClient != null){
			sub(mqttClient,topic);
    	}
        mqttClient.subscribe(topic,2, new IMqttMessageListener() {

            @Override
            public  void messageArrived(String topic, MqttMessage message) throws Exception {
                // TODO Auto-generated method stub
                System.out.println(new String(message.getPayload()));


//                String theMsg = MessageFormat.format("{0} is arrived for topic {1}.", new String(message.getPayload()), topic);
////                    redisService.set(REDIS_KEY_PREFIX_AUTH_CODE + topic,new String(message.getPayload()));
////                    redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE + );
//                System.out.println(theMsg);
//                System.out.println("getload"+new String(message.getPayload()));
//                byte[] payload = message.getPayload();
////                    String s = EncodUtil.BinaryToHexString(payload);
////                    byte[] bytes = s.getBytes();
//                ByteBuffer wrap = ByteBuffer.wrap(payload);
//
//                if(payload!=null&&payload.length>=27){
//                    SensorTitle tt=new SensorTitle(); byte version;//版本
//                    tt.setVersion(wrap.get(0)); //版本
//                    tt.setComand(wrap.get(1));//2 命令字
//                    tt.setJizhongqid(wrap.getInt(2));//6 集中器 ID
//                    tt.setJiedianid(wrap.getInt(6));//节点 ID
//                    tt.setDuanid(wrap.getShort(10));//短 ID
//                    tt.setTongdao(wrap.get(12));//通道0x01-0x04
//                    tt.setSnr(wrap.get(13));//SNR
//                    tt.setRssi0(wrap.get(14));//RSSI[0]
//                    tt.setRssi1(wrap.get(15));//12 RSSI[1]0x01:RSSI 为正数，0x00:RSSI 为负数
//                    tt.setNc(wrap.get(16));//13 NC
//                    tt.setNc1(wrap.get(17));//14 NC
//                    tt.setTime(wrap.getInt(18));//15 时间戳
//                    tt.setZdzxqk(wrap.get(22));//16 终端在线情况 0x01：掉线，0x00：在线
//                    tt.setNum(wrap.getShort(23));//17 终端入网总数
//                    tt.setSensorDataLength(wrap.getShort(25));// 18 数据长度
////                        byte[] dataBytes = new byte[wrap.getShort(25)];
//                    if(payload.length>=69) {
//                        byte[] dataBytes = Arrays.copyOfRange(payload, 27, payload.length);
//                        //电压
//                        Integer VD00 = sensorProperty.computeVoltage(dataBytes[0], dataBytes[1], dataBytes[2]);
//                        Integer VD01 = sensorProperty.computeVoltage(dataBytes[3], dataBytes[4], dataBytes[5]);
//                        Integer VD10 = sensorProperty.computeVoltage(dataBytes[6], dataBytes[7], dataBytes[8]);
//                        Integer VD11 = sensorProperty.computeVoltage(dataBytes[9], dataBytes[10], dataBytes[11]);
////                           //激光点的xy轴坐标
//                        Double Px = sensorProperty.computeSupersonic(dataBytes[12], dataBytes[13], dataBytes[14]);
//                        Double Py = sensorProperty.computeSupersonic(dataBytes[15], dataBytes[16], dataBytes[17]);
////                          //超声波测距
//                        Double Distance = sensorProperty.computeDistance(dataBytes[18], dataBytes[19]);
//
////                        Integer DataL = sensorProperty.computeTENAXIS(dataBytes[20], dataBytes[21]);
//                        //加速度xyz
//                        List AccelerationList = sensorProperty.computeAcceleration(dataBytes[20], dataBytes[21], dataBytes[22], dataBytes[23], dataBytes[24], dataBytes[25]);
//////                       //角速度xyz
//                        List AngularVelocityList = sensorProperty.computeAngularVelocity(dataBytes[26], dataBytes[27], dataBytes[28], dataBytes[29], dataBytes[30], dataBytes[31]);
////                        //角度
//                        List AngularList = sensorProperty.computeAngular(dataBytes[32], dataBytes[33], dataBytes[34], dataBytes[35], dataBytes[36], dataBytes[37]);
////                        //高度
//                        double hight= sensorProperty.computeHight(dataBytes[38], dataBytes[39], dataBytes[40], dataBytes[41]);
//////                        tt.setSensorData(dataBytes.toString());
//                        zullProperty.setJiedianid(tt.getJiedianid());
//                        zullProperty.setTime(tt.getTime());
//                        zullProperty.setVoltage1(VD00);
//                        zullProperty.setVoltage2(VD01);
//                        zullProperty.setVoltage3(VD10);
//                        zullProperty.setVoltage4(VD11);
//                        zullProperty.setXSupersonic(Px);
//                        zullProperty.setYSupersonic(Py);
//                        zullProperty.setDistance(Distance);
//                        zullProperty.setAcceleration(AccelerationList);
//                        zullProperty.setAngularVelocity(AngularVelocityList);
//                        zullProperty.setAngular(AngularList);
//                        zullProperty.setHight(hight);
//
//                    }
////                        Date date = new Date();
//                    Long timeStamp = System.currentTimeMillis();
////                        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////                        String sd = df1.format(new Date((long) tt.getTime()));   // 时间戳转换成时间
////                        System.out.println("传感器时间"+df1.format(new Date((tt.getTime()*1000L)));
////                        System.out.println("系统时间"+df1.format(new Date()));
//                    System.out.println("传感器时间"+tt.getTime());
//                    System.out.println("  系统时间"+timeStamp);
//////                        asyncService.executeAsync(tt);
////
////                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////                        System.out.println(df.format(date));
//////                        sensorMapper.insert(tt);
////                        System.out.println(tt.toString());
//                }
//
//
//
//
//                log.info("getload"+new String(message.getPayload())+"id"+message.getId()+message.getQos());
////            ConcurrentMap<Object, Channel> serverChannels = textWebSocketFrameHandler.getServerChannels();
////            Channel s = serverChannels.get("s");
////            s.writeAndFlush(new TextWebSocketFrame(theMsg));
//
//                ChannelGroup channels= textWebSocketFrameHandler.channels;
////                    DistanceSensor sensor =new DistanceSensor();
////                    sensor.setStartTime();
//                Result result=new Result(200,"success","sensor_init",zullProperty);
//                String jsonzullProperty = JSONObject.toJSONString(result);
//
//                for (Channel channel : channels) {
//                    ChannelId id = channel.id();
//                    Channel channel1 = channels.find(id);
//                    channel1.writeAndFlush(new TextWebSocketFrame(jsonzullProperty ));
//                }
            }
        });

    }
    public static void main(String[] args) throws MqttException{

        runsub("client-id-1", "demo/test");//"$share/testgroupa/edge/server/private/+"
    }
}