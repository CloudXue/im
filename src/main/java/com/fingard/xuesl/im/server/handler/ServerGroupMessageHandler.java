package com.fingard.xuesl.im.server.handler;

import com.fingard.xuesl.im.protocol.request.GroupMessageRequest;
import com.fingard.xuesl.im.protocol.response.GroupMessageResponse;
import com.fingard.xuesl.im.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @author xuesl
 * @date 2018/12/11
 */
public class ServerGroupMessageHandler extends SimpleChannelInboundHandler<GroupMessageRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequest requestPacket) {
        // 拿到 groupId 构造群聊消息的响应
        String groupId = requestPacket.getToGroupId();
        GroupMessageResponse responsePacket = new GroupMessageResponse();
        responsePacket.setFromGroupId(groupId);
        responsePacket.setMessage(requestPacket.getMessage());
        responsePacket.setFromUser(SessionUtil.getSession(ctx.channel()));


        // 拿到群聊对应的 channelGroup，写到每个客户端
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        channelGroup.writeAndFlush(responsePacket);
    }
}
