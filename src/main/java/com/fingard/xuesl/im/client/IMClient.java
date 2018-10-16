package com.fingard.xuesl.im.client;

import com.fingard.xuesl.im.client.handler.ClientLoginHandler;
import com.fingard.xuesl.im.codec.PacketEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author xuesl
 * @date 2018/10/15
 */
@Slf4j
public class IMClient {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8080;
    private static final int MAX_RETRY_TIMES = 5;
    /**
     * 连接状态{0:连接中|-1:连接失败|1-连接成功}
     */
    private volatile int successFlag = 0;

    public static void main(String[] args) {
        IMClient client = new IMClient();
        client.connect();
    }


    private void connect() {
        Bootstrap bootstrap = new Bootstrap();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) {
                        socketChannel.pipeline().addLast(new PacketEncoder());
                        socketChannel.pipeline().addLast(new ClientLoginHandler());
                    }
                });

        connect(bootstrap, HOST, PORT, MAX_RETRY_TIMES);

        //连接失败并在重试次数耗尽后关闭客户端
        while (successFlag == 0 || successFlag == -1) {
            if (successFlag == -1) {
                workerGroup.shutdownGracefully();
                break;
            }
        }
    }

    private void connect(final Bootstrap bootstrap, final String host, final int port, final int retryTimes) {
        bootstrap.connect(host, port).addListener(channelFuture -> {
            if (channelFuture.isSuccess()) {
                log.info("IM客户端连接成功！");
                successFlag = 1;
            } else {
                log.info("连接失败，原因：" + channelFuture.cause().getMessage());
                if (retryTimes == 0) {
                    successFlag = -1;
                    log.info("重试次数耗尽，放弃连接！");
                    return;
                }
                //重连次数
                int times = (MAX_RETRY_TIMES - retryTimes) + 1;
                //重连延迟
                int delay = 1;
                log.info("第" + times + "次重试将于" + delay + "秒后进行！");
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retryTimes - 1), delay, TimeUnit.SECONDS);
            }
        });
    }
}
