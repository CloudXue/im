package com.fingard.xuesl.im.util;

import com.fingard.xuesl.im.protocol.Attributes;
import io.netty.channel.Channel;
import io.netty.util.Attribute;

/**
 * @author xuesl
 * @date 2018/11/7
 */
public class LoginUtil {
    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> loginFlag = channel.attr(Attributes.LOGIN);
        return loginFlag.get() != null && loginFlag.get();
    }

    public static void login(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }
}
