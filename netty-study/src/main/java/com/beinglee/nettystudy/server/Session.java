package com.beinglee.nettystudy.server;

import lombok.Builder;
import lombok.Getter;

/**
 * @author zhanglu
 * @date 2020/5/25 20:46
 */
@Getter
@Builder
public class Session {

    private String userId;

    private String userName;

    @Override
    public String toString() {
        return userId + ":" + userName;
    }
}
