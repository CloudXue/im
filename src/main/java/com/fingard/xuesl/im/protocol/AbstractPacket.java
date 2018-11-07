package com.fingard.xuesl.im.protocol;

import lombok.Data;

import java.io.Serializable;

/**
 * +---------------------------------------------------------------+
 * | magic number| version | serializer| command | length | content|
 * +---------------------------------------------------------------+
 * |      4      |    1    |      1    |    1    |    4   | length |
 */
@Data
public abstract class AbstractPacket implements Serializable {

    private final int magicNumber = 0x34547621;
    private static final long serialVersionUID = -8649049591657378902L;


    /**
     * 版本号
     */
    private int version;

    public abstract byte getCommand();
}
