package com.fingard.xuesl.im.protocol.response;

import com.fingard.xuesl.im.protocol.AbstractPacket;
import com.fingard.xuesl.im.protocol.Command;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xuesl
 * @date 2018/10/16
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LoginResponse extends AbstractPacket {
    private static final long serialVersionUID = -3607349036610435900L;
    private String userId;
    private String userName;
    private boolean success;
    private String info;

    @Override
    public byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
