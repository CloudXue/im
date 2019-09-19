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

    byte LOGOUT_REQUEST = 5;

    byte LOGOUT_RESPONSE = 6;

    byte CREATE_GROUP_REQUEST = 7;

    byte CREATE_GROUP_RESPONSE = 8;

    byte HEARTBEAT_REQUEST = 9;

    byte HEARTBEAT_RESPONSE = 10;

    byte GROUP_MESSAGE_REQUEST = 11;

    byte GROUP_MESSAGE_RESPONSE = 12;
}
