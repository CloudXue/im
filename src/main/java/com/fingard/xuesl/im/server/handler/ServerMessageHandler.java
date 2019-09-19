package com.fingard.xuesl.im.server.handler;

import com.fingard.xuesl.im.protocol.request.MessageRequest;
import com.fingard.xuesl.im.protocol.response.MessageResponse;
import com.fingard.xuesl.im.session.Session;
import com.fingard.xuesl.im.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author xuesl
 * @date 2018/11/6
 */
public class ServerMessageHandler extends SimpleChannelInboundHandler<MessageRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequest messageRequest) {

        //获取发送方session
        Session session = SessionUtil.getSession(ctx.channel());

        //获取接收方channel
        Channel toUserChannel = SessionUtil.getChannel(messageRequest.getToUserId());

        //组装返回消息
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setFromUserId(session.getUserId());
        messageResponse.setFromUserName(session.getUserName());
        messageResponse.setMessage(messageRequest.getMessage());

        if (toUserChannel != null) {
            toUserChannel.writeAndFlush(messageResponse);
        }
    }
}
