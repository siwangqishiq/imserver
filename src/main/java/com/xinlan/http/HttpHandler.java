package com.xinlan.http;

import com.xinlan.http.action.IAction;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.AsciiString;

public class HttpHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    private AsciiString contentType = HttpHeaderValues.TEXT_PLAIN;

    private Router mRouter;
    public HttpHandler(Router router){
        mRouter = router;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx,
                                FullHttpRequest request) throws Exception {
        System.out.println("class:" + request.getClass().getName());

        DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK); //

        handleHttpRequest(request , response);

        HttpHeaders heads = response.headers();
        heads.add(HttpHeaderNames.CONTENT_TYPE, contentType + "; charset=UTF-8");
        heads.add(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes()); // 3
        heads.add(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);

        ctx.write(response);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelReadComplete");
        super.channelReadComplete(ctx);
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("exceptionCaught");
        if (cause != null) {
            cause.printStackTrace();
        }

        if (ctx != null) {
            ctx.close();
        }
    }

    public void handleHttpRequest(FullHttpRequest request , DefaultFullHttpResponse response){
        IAction action = mRouter.findAction(request);
        if(action != null){
            action.service(request , response);
        }else{
            response.content().writeBytes(Resp.error(StatusCode.CODE_ERROR , StatusCode.NO_ACTION));
        }
    }

} //end class
