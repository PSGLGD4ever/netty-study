package com.beinglee.nettystudy.utils;


import com.beinglee.nettystudy.protocol.Attributes;
import io.netty.channel.Channel;
import io.netty.util.Attribute;

import java.util.Optional;

public abstract class LoginUtils {

    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);
        return Optional.ofNullable(loginAttr.get()).orElse(false);
    }
}
