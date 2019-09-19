package com.fingard.xuesl.im.server.handler;

import cn.hutool.core.util.RandomUtil;
import com.fingard.xuesl.im.protocol.request.CreateGroupRequest;
import com.fingard.xuesl.im.protocol.response.CreateGroupResponse;
import com.fingard.xuesl.im.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuesl
 * @date 2018/12/11
 */
@Slf4j
public class ServerCreateGroupHandler extends SimpleChannelInboundHandler<CreateGroupRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequest msg) throws Exception {

        //创建一个Channel组
        ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());

        List<String> userNameList = new ArrayList<>();

        for (String userId : msg.getUserIdList()) {
            Channel channel = SessionUtil.getChannel(userId);
            if (channel != null) {
                if (!channelGroup.contains(channel)) {
                    channelGroup.add(channel);
                    userNameList.add(SessionUtil.getSession(channel).getUserName());
                }
            }
        }

        //将发起方加入聊天群
        if (!channelGroup.contains(ctx.channel())){
            channelGroup.add(ctx.channel());
            userNameList.add(SessionUtil.getSession(ctx.channel()).getUserName());
        }

        //构建response
        String groupId = RandomUtil.simpleUUID();
        CreateGroupResponse response = new CreateGroupResponse();
        response.setSuccess(true);
        response.setUserNameList(userNameList);
        response.setGroupId(groupId);

        SessionUtil.bindChannelGroup(groupId, channelGroup);

        channelGroup.writeAndFlush(response);

        log.info("聊天群创建成功，群ID为[{}],成员为{}", groupId, userNameList);
    }
}
