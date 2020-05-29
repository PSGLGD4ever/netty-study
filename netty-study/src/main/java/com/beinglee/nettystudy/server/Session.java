package com.beinglee.nettystudy.server;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhanglu
 * @date 2020/5/25 20:46
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Session {

    private String userId;

    private String userName;

    @Override
    public String toString() {
        return userId + ":" + userName;
    }
}
