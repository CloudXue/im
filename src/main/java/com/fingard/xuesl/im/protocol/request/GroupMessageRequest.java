package com.fingard.xuesl.im.protocol.request;

import com.fingard.xuesl.im.protocol.AbstractPacket;
import com.fingard.xuesl.im.protocol.Command;
import lombok.Getter;
import lombok.Setter;

/**
 * @author xuesl
 * @date 2018/12/11
 */
@Setter
@Getter
public class GroupMessageRequest extends AbstractPacket {
    private String toGroupId;
    private String message;

    public GroupMessageRequest(String toGroupId, String message) {
        this.toGroupId = toGroupId;
        this.message = message;
    }

    @Override
    public byte getCommand() {
        return Command.GROUP_MESSAGE_REQUEST;
    }
}
