package com.fingard.xuesl.im.protocol;

import io.netty.util.AttributeKey;

/**
 * @author xuesl
 * @date 2018/11/6
 */
public interface Attributes {
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}
