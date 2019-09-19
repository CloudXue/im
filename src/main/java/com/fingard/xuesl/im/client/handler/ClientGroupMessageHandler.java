package com.fingard.xuesl.im.client.handler;

import com.fingard.xuesl.im.protocol.response.GroupMessageResponse;
import com.fingard.xuesl.im.session.Session;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author xuesl
 * @date 2018/12/11
 */
public class ClientGroupMessageHandler extends SimpleChannelInboundHandler<GroupMessageResponse> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageResponse responsePacket) {
        String fromGroupId = responsePacket.getFromGroupId();
        Session fromUser = responsePacket.getFromUser();
        System.out.println("收到群[" + fromGroupId + "]中[" + fromUser + "]发来的消息：" + responsePacket.getMessage());
    }
}
