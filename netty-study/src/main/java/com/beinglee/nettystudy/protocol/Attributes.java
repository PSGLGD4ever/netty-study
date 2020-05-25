package com.beinglee.nettystudy.protocol;

import com.beinglee.nettystudy.server.Session;
import io.netty.util.AttributeKey;

/**
 * @author Luz
 */
public abstract class Attributes {

    public static final AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");

    public static final AttributeKey<Session> SESSION = AttributeKey.newInstance("session");

}
