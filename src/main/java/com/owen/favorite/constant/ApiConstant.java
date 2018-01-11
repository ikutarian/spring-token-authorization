package com.owen.favorite.constant;

public class ApiConstant {

    /**
     * 服务器数据返回码
     */
    public static final class Code {
        public static final int OK = 200;
        public static final int NG = 404;
    }

    /**
     * 有关 Token 的配置
     */
    public static final class Token {
        /**
         * 过期时间
         */
        public static final int EXPIRE_DAYS = 10;
    }

    public static final class RequestParam {
        public static final String TOKEN = "token";
        public static final String USER_ID = "user_id";
    }
}
