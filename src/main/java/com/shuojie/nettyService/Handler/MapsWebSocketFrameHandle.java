package com.shuojie.nettyService.Handler;

import com.alibaba.fastjson.JSONObject;
import com.shuojie.domain.Model;
import com.shuojie.domain.maps.CurrentInfo;
import com.shuojie.domain.maps.CurrentLine;
import com.shuojie.domain.maps.MapPoints;
import com.shuojie.service.ModelService;
import com.shuojie.service.mapsService.CurrentLineService;
import com.shuojie.service.mapsService.CurrentInfoService;
import com.shuojie.service.mapsService.MapPointsService;
import com.shuojie.utils.vo.Result;
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

import java.math.BigDecimal;
import java.util.concurrent.ConcurrentMap;

@Slf4j
@Component
@ChannelHandler.Sharable
public class MapsWebSocketFrameHandle extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    public static MapsWebSocketFrameHandle mapsWebSocketFrameHandle;

    @Autowired
    private CurrentInfoService currentService;

    @Autowired
    private CurrentLineService currentLineService;

    @Autowired
    private MapPointsService mapPointsService;

    @Autowired
    private ModelService modelService;

    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private ConcurrentMap<Object, Channel> serverChannels = PlatformDependent.newConcurrentHashMap();

    public ConcurrentMap<Object,Channel> getServerChannels() {return serverChannels;}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        channels.add(ctx.channel());
        ByteBuf buf = ctx.alloc().directBuffer();//从 channel 获取 ByteBufAllocator 然后分配一个 ByteBuf
        JSONObject json = JSONObject.parseObject(msg.text().toString());//json字符串转json对象
        String command = json.getString("command");

        CurrentInfo current = new CurrentInfo();
        CurrentLine currentLine = new CurrentLine();
        MapPoints mapPoints = new MapPoints();
        Model model = new Model();

        if(!command.substring(0,4).equals("maps")){
            buf.retain();//检查引用计数器是否是 1
            msg.retain();
            ctx.fireChannelRead(msg);
        }

        switch (command){
            //添加当前线路line_name,clid,current_date
            case "maps_insertCurrentInfo":
               // current.setCurrentDate(json.getString("currentDate"));
                current.setLineName(json.getString("lineName"));
                Result cur = currentService.insertCurrentInfo(current);
                String insertCurrentInfoResponse = JSONObject.toJSONString(cur);
                ctx.channel().writeAndFlush(new TextWebSocketFrame(insertCurrentInfoResponse));
                break;
            //添加当前线路经纬度
            case "maps_insertCurrentLine":
                currentLine.setCuLiLongitude(json.getDouble("cuLiLongitude"));
                currentLine.setCuLiLatitude(json.getDouble("cuLiLatitude"));
                currentLine.setCuid(Integer.valueOf(json.getString("cuid")));
                Result curLin = currentLineService.insertCurrentLine(currentLine);
                String  insertCurrentLineResponse = JSONObject.toJSONString(curLin);
                ctx.channel().writeAndFlush(new TextWebSocketFrame(insertCurrentLineResponse));
                break;
            //添加数据空间地图初始点
            case "maps_insertMapPoints":
                mapPoints.setMaLongitude(json.getDouble("maLongitude"));
                mapPoints.setMaLatitude(json.getDouble("maLatitude"));
                mapPoints.setPointsName(json.getString("pointsName"));
                Result map = mapPointsService.insertMapPoints(mapPoints);
                String insertMapPointsResponse = JSONObject.toJSONString(map);
                ctx.channel().writeAndFlush(new TextWebSocketFrame(insertMapPointsResponse));
                break;
            //添加模型信息
            case "maps_insertModel":
                try {
                    model.setModelName(json.getString("modelName"));
                    model.setMoLatitude(json.getDouble("moLatitude"));
                    model.setMoLongitude(json.getDouble("moLongitude"));
                    model.setDirection(json.getString("direction"));
                    Result mod = modelService.insertModel(model);
                    String insertModelResponse = JSONObject.toJSONString(mod);
                    ctx.channel().writeAndFlush(new TextWebSocketFrame(insertModelResponse));
                }catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
}
}
