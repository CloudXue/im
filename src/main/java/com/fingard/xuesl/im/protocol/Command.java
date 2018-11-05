package com.fingard.xuesl.im.protocol;

/**
 * @author xuesl
 * @date 2018/10/15
 */
public interface Command {
    byte LOGIN_REQUEST = 1;

    byte LOGIN_RESPONSE = 2;

    byte MESSAGE_REQUEST = 3;

    byte MESSAGE_RESPONSE = 4;

}
