package com.fingard.xuesl.im.protocol.request;

import com.fingard.xuesl.im.protocol.AbstractPacket;
import com.fingard.xuesl.im.protocol.Command;

/**
 * @author xuesl
 * @date 2018/12/11
 */
public class LogoutRequest extends AbstractPacket {
    @Override
    public byte getCommand() {
        return Command.LOGOUT_REQUEST;
    }
}
