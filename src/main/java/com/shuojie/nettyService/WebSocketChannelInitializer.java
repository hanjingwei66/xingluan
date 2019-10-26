package com.shuojie.nettyService;

import com.shuojie.nettyService.Handler.*;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketFrameAggregator;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.codec.mqtt.MqttDecoder;
import io.netty.handler.codec.mqtt.MqttEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Autowired
    private TextWebSocketFrameHandler textWebSocketFrameHandler;
    @Autowired
    private SelectHandler selectHandler;
    @Autowired
    private AuthHandler authHandler;
//    @Autowired
//    private MapsWebSocketFrameHandle mapsWebSocketFrameHandle;
    @Autowired
    private BinaryWebSocketFrameHandler binaryWebSocketFrameHandler;
//    @Autowired
//    private CommonHandler commonHandler;

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        //websocket协议本身是基于http协议的，所以这边也要使用http解编码器
        pipeline.addLast(new HttpServerCodec());
        //mqtt 协议的编码解码器
        pipeline.addLast(new MqttDecoder());
        pipeline.addLast(MqttEncoder.INSTANCE);
        //以块的方式来写的处理器
        pipeline.addLast(new ChunkedWriteHandler());
        //netty是基于分段请求的，HttpObjectAggregator的作用是将请求分段再聚合,参数是聚合字节的最大长度
        pipeline.addLast(new HttpObjectAggregator(8192));
        // webSocket 数据压缩扩展，当添加这个的时候WebSocketServerProtocolHandler
        pipeline.addLast(new WebSocketServerCompressionHandler());
        pipeline.addLast(new WebSocketFrameAggregator(10 * 1024 * 1024));
        //ws://server:port/context_path
        //ws://localhost:9999/ws
        //参数指的是contex_path
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws", null, true, 10485760));

        //websocket定义了传递数据的6中frame类型
//        pipeline.addLast(new com.shuojie.nettyService.Handler.SensorHandler());
        pipeline.addLast("TextWebSocketFrameHandler", textWebSocketFrameHandler);
        pipeline.addLast("AuthHandler", authHandler);
        pipeline.addLast("selectHandler",selectHandler);
        pipeline.addLast("BinaryWebSocketFrameHandler", binaryWebSocketFrameHandler);
//        pipeline.addLast("CommonHandler", commonHandler);
//        new StringDecoder();
        //测试git0281470051500F24474E524D432C3032353934382E30302C412C333431302E33333136332C4E2C31303834382E35383833312C452C302E3035392C2C3135313031392C2C2C442A36380D0A1303020102004857A203

    }

}
