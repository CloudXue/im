package com.fingard.xuesl.im.client.handler;

import com.fingard.xuesl.im.protocol.response.CreateGroupResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xuesl
 * @date 2018/12/11
 */
@Slf4j
public class ClientCreateGroupHandler extends SimpleChannelInboundHandler<CreateGroupResponse> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponse msg) throws Exception {
        if (msg.isSuccess()) {
            log.info("被拉入聊天群，群ID为[{}],成员为{}", msg.getGroupId(), msg.getUserNameList());
        }
    }
}
