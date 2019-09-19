package com.fingard.xuesl.im.session;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author xuesl
 * @date 2018/12/11
 */
@Setter
@Getter
public class Session implements Serializable {
    private static final long serialVersionUID = -5484689861818832739L;
    private String userId;

    private String userName;

    public Session (String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Session{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
