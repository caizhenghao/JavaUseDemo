package com.czh.study.netty.objectse;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Author: cai.zhenghao
 * @Description:
 * @Date: Created in 2018/12/17  5:49 PM
 * @Modified By:
 */
public class ObjectChatServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive");
        super.channelActive(ctx);
        ctx.writeAndFlush("Hello ,I am server");
        String id = ctx.channel().id().asLongText();
        ObjectChatServer.chMap.put(id,ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        System.out.println("channelRead");
        super.channelRead(ctx, msg);
        System.out.println("Receive msg from client:" + msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
        ObjectChatServer.chMap.remove(ctx.channel().id().asLongText());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                System.out.println("channelInactive");
        super.channelInactive(ctx);
        ObjectChatServer.chMap.remove(ctx.channel().id().asLongText());
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("channelRegistered");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("channelUnregistered");
        super.channelUnregistered(ctx);
    }
}
