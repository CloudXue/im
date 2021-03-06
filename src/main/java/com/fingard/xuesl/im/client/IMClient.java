package com.fingard.xuesl.im.client;

import com.fingard.xuesl.im.client.console.ConsoleCommand;
import com.fingard.xuesl.im.client.console.ConsoleCommandManager;
import com.fingard.xuesl.im.client.console.LoginConsoleCommand;
import com.fingard.xuesl.im.client.handler.*;
import com.fingard.xuesl.im.codec.PacketDecoder;
import com.fingard.xuesl.im.codec.PacketEncoder;
import com.fingard.xuesl.im.codec.PacketFilter;
import com.fingard.xuesl.im.handler.IMIdleStateHandler;
import com.fingard.xuesl.im.protocol.Attributes;
import com.fingard.xuesl.im.protocol.request.MessageRequest;
import com.fingard.xuesl.im.util.LoginUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;
import java.util.concurrent.Executors;
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
     * 连接状态{0:连接中|-1:连接失败|1-连接成功|2-断开连接}
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
                        //出站
                        socketChannel.pipeline().addLast(new PacketEncoder());

                        //入站
                        socketChannel.pipeline().addLast(new IMIdleStateHandler());
                        socketChannel.pipeline().addLast(new PacketFilter());
                        socketChannel.pipeline().addLast(new PacketDecoder());
                        socketChannel.pipeline().addLast(new ClientHeartBeatTimerHandler());
                        socketChannel.pipeline().addLast(new ClientLoginHandler());
                        socketChannel.pipeline().addLast(new ClientMessageHandler());
                        socketChannel.pipeline().addLast(new ClientCreateGroupHandler());
                        socketChannel.pipeline().addLast(new ClientGroupMessageHandler());
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
                Channel channel = ((ChannelFuture)channelFuture).channel();
                startConsoleThread(channel);
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

    private void startConsoleThread(Channel channel) {
        ConsoleCommand consoleCommandManager = new ConsoleCommandManager();
        ConsoleCommand loginConsoleCommand = new LoginConsoleCommand();
        Scanner scanner = new Scanner(System.in);
        Executors.newSingleThreadExecutor().execute(() -> {
            while (successFlag == 1) {
                if (!LoginUtil.hasLogin(channel)) {
                    loginConsoleCommand.execute(scanner, channel);
                } else {
                    consoleCommandManager.execute(scanner, channel);
                }
            }

            //TODO 拆包沾包测试
//            for (int i = 0;i<100;i++) {
//                MessageRequest messageRequest = new MessageRequest();
//                messageRequest.setMessage("这是一条测试拆包粘包的测试数据，我得让消息再长点才好产生现象。");
//                channel.writeAndFlush(messageRequest);
//            }
        });
    }
}
