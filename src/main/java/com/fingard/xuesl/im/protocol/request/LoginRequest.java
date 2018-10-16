package com.fingard.xuesl.im.protocol.request;

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
public class LoginRequest extends AbstractPacket {

    private static final long serialVersionUID = 8200594262234171147L;
    private String userId;
    private String userName;
    private String password;

    @Override
    public byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
