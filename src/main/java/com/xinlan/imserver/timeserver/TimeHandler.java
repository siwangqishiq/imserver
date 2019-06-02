package com.xinlan.imserver.timeserver;

import com.xinlan.imserver.Policy;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeHandler extends ChannelInboundHandlerAdapter {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() +" has connected!");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof ByteBuf){
            String askStr = ((ByteBuf) msg).toString(CharsetUtil.UTF_8);
            System.out.println("from: " + ctx.channel().remoteAddress() + " : " + askStr);
            if(Policy.TIME_SERVER_ASK.equals(askStr)){
                answerTime(ctx);
                return;
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(ctx.channel().remoteAddress() +" fire a ERROR! ");
        cause.printStackTrace();
        ctx.close();
    }

    protected void answerTime(final ChannelHandlerContext ctx){
        final String timeStr = DATE_FORMAT.format(new Date());
        ByteBuf timeData = Unpooled.copiedBuffer(timeStr , CharsetUtil.UTF_8);
        ctx.writeAndFlush(timeData);
        System.out.println("send " + timeStr +" to " +ctx.channel().remoteAddress());
    }

} //end class
