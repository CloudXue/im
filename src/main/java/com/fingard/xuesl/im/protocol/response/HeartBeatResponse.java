package com.fingard.xuesl.im.protocol.response;

import com.fingard.xuesl.im.protocol.AbstractPacket;
import com.fingard.xuesl.im.protocol.Command;

/**
 * @author xuesl
 * @date 2018/12/11
 */
public class HeartBeatResponse extends AbstractPacket {
    @Override
    public byte getCommand() {
        return Command.HEARTBEAT_RESPONSE;
    }
}
