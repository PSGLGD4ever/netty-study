package com.beinglee.nettystudy.utils;

import java.util.UUID;

public abstract class IDUtils {

    public static String randomId() {
        return UUID.randomUUID().toString().split("-")[0];
    }
}
