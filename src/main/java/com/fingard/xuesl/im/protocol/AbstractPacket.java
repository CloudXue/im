package com.fingard.xuesl.im.protocol;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xuesl
 * @date 2018/10/15
 */
@Data
public abstract class AbstractPacket implements Serializable {

    private static final int MAGIC_NUMBER = 0x34547621;
    private static final long serialVersionUID = -8649049591657378902L;


    /**
     * 版本号
     */
    public int version;

    public abstract byte getCommand();
}
