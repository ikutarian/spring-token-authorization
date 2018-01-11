package com.owen.favorite.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Date;

public class TokenUtil {

    /**
     * 1. 如果用“.”作为分隔的话，必须是如下写法：String.split("\\."),这样才能正确的分隔开，不能用String.split(".");
     * 2. 如果用“|”作为分隔的话，必须是如下写法：String.split("\\|"),这样才能正确的分隔开，不能用String.split("|");
     */
    private static final String SEPARATOR = "-";

    /**
     * Token格式：时间戳.userId.随机字符串
     */
    public static String createToken(long userId) {
        return new Date().getTime() + SEPARATOR + userId + SEPARATOR + RandomStringUtils.random(10, true, true);
    }

    /**
     * 解析Token，从中取得userId
     */
    public static Long getUserIdFromToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        String[] param = token.split(SEPARATOR);
        if (param.length != 3) {
            return null;
        }
        try {
            return NumberUtils.createLong(param[1]);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
