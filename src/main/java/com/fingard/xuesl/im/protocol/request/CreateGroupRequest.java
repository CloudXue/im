package com.fingard.xuesl.im.protocol.request;

import com.fingard.xuesl.im.protocol.AbstractPacket;
import com.fingard.xuesl.im.protocol.Command;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author xuesl
 * @date 2018/12/11
 */
@Setter
@Getter
public class CreateGroupRequest extends AbstractPacket {

    private List<String> userIdList;

    @Override
    public byte getCommand() {
        return Command.CREATE_GROUP_REQUEST;
    }
}
