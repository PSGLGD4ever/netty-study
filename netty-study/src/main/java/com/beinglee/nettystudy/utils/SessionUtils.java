package com.beinglee.nettystudy.utils;

import com.beinglee.nettystudy.protocol.Attributes;
import com.beinglee.nettystudy.server.Session;
import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhanglu
 * @date 2020/5/25 20:39
 */
public abstract class SessionUtils {

    private static Map<String, Channel> userIdChannels = null;

    public SessionUtils() {
        userIdChannels = new ConcurrentHashMap<>();
    }

    public static void bindSession(Session session, Channel channel) {
        userIdChannels.put(session.getUserId(), channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    public static Channel getChannel(String userId) {
        return userIdChannels.get(userId);
    }

    public static Session getSession() {
        return null;
    }


}
