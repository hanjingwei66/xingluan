package com.shuojie.nettyService.Handler;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class BinaryWebSocketFrameHandler  extends SimpleChannelInboundHandler<BinaryWebSocketFrame> {
    private static final Logger log = LoggerFactory.getLogger(BinaryWebSocketFrameHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, BinaryWebSocketFrame msg) throws InterruptedException {
//        ByteBuf buf = ctx.alloc().directBuffer();
//        buf.retain();
        if (msg instanceof BinaryWebSocketFrame) {
            ByteBuf content = msg.content();
            log.info("服务器接收到二进制消息,消息长度:[{}]", msg.content().capacity());

            byte[] array = new byte[content.readableBytes()];//转为字节数组
            String path = "F:/test";
            File dest = new File(path + "/" + "fileName");
            if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
                dest.getParentFile().mkdir();
            }
            try {
                OutputStream outputStream = new FileOutputStream(dest);
                outputStream.write(array);
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ByteBuf byteBuf = Unpooled.directBuffer(msg.content().capacity());
            byteBuf.writeBytes(msg.content());
            ctx.writeAndFlush(new BinaryWebSocketFrame(byteBuf));
        }

    }
    private void writeChunk(ChannelHandlerContext ctx) throws IOException {
//        while (httpDecoder.hasNext()) {
//            InterfaceHttpData data = httpDecoder.next();
//            if (data != null && HttpDataType.FileUpload.equals(data.getHttpDataType())) {
//                final FileUpload fileUpload = (FileUpload) data;
//                final File file = new File(FILE_UPLOAD + fileUpload.getFilename());
//                log.info("upload file: {}", file);
//                try (FileChannel inputChannel = new FileInputStream(fileUpload.getFile()).getChannel();
//                     FileChannel outputChannel = new FileOutputStream(file).getChannel()) {
//                    outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
//                    ResponseUtil.response(ctx, request, new GeneralResponse(HttpResponseStatus.OK, "SUCCESS", null));
//                }
//            }
//        }
    }
}
