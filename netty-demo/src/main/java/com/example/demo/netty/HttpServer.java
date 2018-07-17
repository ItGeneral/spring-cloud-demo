package com.example.demo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpRequestEncoder;


/**
 * @author SongJiuHua.
 * @Date Created on 2018/1/24.
 * @description
 */
public class HttpServer {

    private final int port;

    public HttpServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        new HttpServer(65535).start();
    }


    /**
     * 启动类
     * @throws Exception
     */
    public void start() throws Exception {
        ServerBootstrap bootstrap = new ServerBootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();
        bootstrap
                // 绑定线程池
                .group(group)
                // 指定使用的channel
                .channel(NioServerSocketChannel.class)
                // 绑定监听端口
                .localAddress(port)
                // 绑定客户端连接时候触发操作
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel socketChannel) throws Exception {
                        System.out.println("initChannel ch:" + socketChannel);
                        socketChannel.pipeline()
                                //用于解码request
                                .addLast("decoder", new HttpRequestDecoder())
                                //用于编码response
                                .addLast("encoder", new HttpRequestEncoder())
                                //消息聚合器 消息内容长度不超过512kb
                                .addLast("aggregator", new HttpObjectAggregator(512 * 1024))
                                //添加自己的处理接口
                                .addLast("handler", new HttpHandler());

                    }
                 })
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE);
        // 服务器异步创建绑定
        ChannelFuture cf = bootstrap.bind().sync();
        // 关闭服务器通道
        cf.channel().closeFuture().sync();
        // 释放线程池资源
        group.shutdown();

    }

}
