package com.beinglee.nettystudy.protocol;

import io.netty.util.AttributeKey;

public abstract class Attributes {

    public static final AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}
