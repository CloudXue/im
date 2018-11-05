package com.fingard.xuesl.im.protocol.request;

import com.fingard.xuesl.im.protocol.AbstractPacket;
import com.fingard.xuesl.im.protocol.Command;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xuesl
 * @date 2018/11/5
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MessageResponse extends AbstractPacket {
    private static final long serialVersionUID = -5734565802575990424L;

    private String message;

    @Override
    public byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }
}
