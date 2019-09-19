package com.fingard.xuesl.im.protocol.response;

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
public class CreateGroupResponse extends AbstractPacket {

    private boolean success;
    private String groupId;
    private List<String> userNameList;

    @Override
    public byte getCommand() {
        return Command.CREATE_GROUP_RESPONSE;
    }
}
