package com.fingard.xuesl.im.protocol.request;

import com.fingard.xuesl.im.protocol.AbstractPacket;
import com.fingard.xuesl.im.protocol.Command;

/**
 * @author xuesl
 * @date 2018/12/11
 */
public class HeartBeatRequest extends AbstractPacket {
    @Override
    public byte getCommand() {
        return Command.HEARTBEAT_REQUEST;
    }
}
