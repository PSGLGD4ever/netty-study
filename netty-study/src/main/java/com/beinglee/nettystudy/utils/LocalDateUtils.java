package com.beinglee.nettystudy.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author zhanglu
 * @date 2020/5/22 18:04
 */
public abstract class LocalDateUtils {

    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String now() {
        return LocalDateTime.now().format(formatter);
    }

}
