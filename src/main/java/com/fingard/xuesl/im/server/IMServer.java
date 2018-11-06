package com.fingard.xuesl.im.server;

import com.fingard.xuesl.im.codec.PacketDecoder;
import com.fingard.xuesl.im.codec.PacketEncoder;
import com.fingard.xuesl.im.server.handler.ServerLoginHandler;
import com.fingard.xuesl.im.server.handler.ServerMessageHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xuesl
 * @date 2018/10/15
 */
@Slf4j
public class IMServer {
    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) {
                        socketChannel.pipeline().addLast(new PacketEncoder());
                        socketChannel.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 7, 4));
                        socketChannel.pipeline().addLast(new PacketDecoder());
                        socketChannel.pipeline().addLast(new ServerLoginHandler());
                        socketChannel.pipeline().addLast(new ServerMessageHandler());
                    }
                });
        bind(serverBootstrap, 8080);
    }

    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(channelFuture -> {
            if (channelFuture.isSuccess()) {
                log.info("IM服务器在端口[" + port + "]绑定成功!");
            } else {
                bind(serverBootstrap, port + 1);
            }
        });
    }
}
