package com.fingard.xuesl.im.protocol;

import lombok.Data;

/**
 * @author xuesl
 * @date 2018/10/15
 */
@Data
public abstract class Packet {

    private static final int MAGIC_NUMBER = 0x34547621;


    /**
     * 版本号
     */
    public int version;

    public abstract String getCommand();
}
