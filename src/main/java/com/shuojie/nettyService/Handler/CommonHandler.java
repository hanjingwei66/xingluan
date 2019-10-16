package com.shuojie.nettyService.Handler;

import com.alibaba.fastjson.JSONObject;
import com.shuojie.domain.system.PictureList;
import com.shuojie.service.sysService.PictureListService;
import com.shuojie.utils.vo.Result;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@ChannelHandler.Sharable
public class CommonHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    @Autowired
    private PictureListService pictureListService;
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        JSONObject json = JSONObject.parseObject(msg.text());//json字符串转json对象
        String command = json.getString("command");
        ByteBuf buf = ctx.alloc().directBuffer();
        try {
            if (!command.substring(0, 6).equals("common")) {
                buf.retain();//检查引用计数器是否是 1
                msg.retain();
                ctx.fireChannelRead(msg);
            }
        } finally {
            buf.release();
        }
        switch (command) {
            case "common_pictureList"://图例集
                Integer type = json.getInteger("type");
                PictureList pictureList = JSONObject.parseObject(msg.text(),PictureList.class);
                List<PictureList> pictureLists = pictureListService.selectList(pictureList);
                Result result=new Result (200,"SUCCESS","common_pictureList",pictureLists);
                result.setType(type);
                String jsonPictureLists = JSONObject.toJSONString(result);
                ctx.channel().writeAndFlush(new TextWebSocketFrame(jsonPictureLists));
                break;
        }
    }
}
