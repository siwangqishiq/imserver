package com.xinlan.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

public class HttpServer {
    public static final int PORT = 9999;

    public static void main(String[] args) {
        new HttpServer().starServer(PORT);
    }

    public void starServer(int port) {
        System.out.println("start http server");
        final Router router = new Router();
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap strap = new ServerBootstrap();
            strap.group(group).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            System.out.println("initChannel ch:" + socketChannel);
                            ChannelPipeline pipline = socketChannel.pipeline();
                            pipline.addLast("decoder", new HttpRequestDecoder());
                            pipline.addLast("encoder", new HttpResponseEncoder());
                            pipline.addLast("aggregator", new HttpObjectAggregator(512 * 1024));
                            pipline.addLast("handler", new HttpHandler(router));
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128) // determining the number of connections queued
                    .childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE);

            ChannelFuture future = strap.bind(port).sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                group.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
