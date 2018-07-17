package com.example.demo.netty;

import io.netty.buffer.ChannelBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author SongJiuHua.
 * @Date Created on 2018/1/25.
 * @description
 */
public class HttpHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void inboundBufferUpdated(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    @Override
    public ChannelBuf newInboundBuffer(ChannelHandlerContext channelHandlerContext) throws Exception {
        return null;
    }

    @Override
    public void freeInboundBuffer(ChannelHandlerContext channelHandlerContext, ChannelBuf channelBuf) throws Exception {

    }
}
