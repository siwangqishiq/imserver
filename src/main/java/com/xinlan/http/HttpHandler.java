package com.xinlan.http;

import com.alibaba.fastjson.JSON;
import com.xinlan.exception.CommonException;
import com.xinlan.http.action.IAction;
import com.xinlan.security.SecurityHelper;
import com.xinlan.security.TokenVertifyResult;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.AsciiString;
import io.netty.util.internal.StringUtil;

public class HttpHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    private AsciiString contentType = HttpHeaderValues.TEXT_PLAIN;

    public static final String HEAD_AUTH_TOKEN = "auth_token";

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
            if(action.needVertified()){
                try {
                    if(doVertified(request)){
                        action.service(request , response);
                    }
                } catch (CommonException e) {
                    Resp resp = Resp.error(StatusCode.CODE_ERROR_LOGIC , e.getErrorMsg());
                    response.content().writeBytes(JSON.toJSONBytes(resp));
                }
            }else{
                action.service(request , response);
            }
        }else{
            Resp resp = Resp.error(StatusCode.CODE_ERROR , StatusCode.NO_ACTION);
            response.content().writeBytes(JSON.toJSONBytes(resp));
        }
    }

    protected boolean doVertified(FullHttpRequest request) throws CommonException {
        String token = request.headers().get(HEAD_AUTH_TOKEN);
        if(StringUtil.isNullOrEmpty(token)){
            throw new CommonException(StatusCode.NO_LOGIN);
        }

        TokenVertifyResult result = SecurityHelper.vertifyToken(token, new SecurityHelper.ICheck() {
            @Override
            public boolean validateAccount(String token, String account, String pwd) {

                return false;
            }
        });
        if(result == TokenVertifyResult.success){


            return true;
        }else if(result == TokenVertifyResult.error_expire){
            throw new CommonException(StatusCode.TOKEN_EXPIRE);
        }else if(result == TokenVertifyResult.error_invalide){
            throw new CommonException(StatusCode.TOKEN_ERROR);
        } else{
            throw new CommonException(StatusCode.UNKNOW);
        }
    }

} //end class
