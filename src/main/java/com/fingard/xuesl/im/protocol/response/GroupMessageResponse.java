package com.fingard.xuesl.im.protocol.response;

import com.fingard.xuesl.im.protocol.AbstractPacket;
import com.fingard.xuesl.im.protocol.Command;
import com.fingard.xuesl.im.session.Session;
import lombok.Getter;
import lombok.Setter;

/**
 * @author xuesl
 * @date 2018/12/11
 */
@Setter
@Getter
public class GroupMessageResponse extends AbstractPacket {

    private String fromGroupId;

    private Session fromUser;

    private String message;

    @Override
    public byte getCommand() {
        return Command.GROUP_MESSAGE_RESPONSE;
    }
}
