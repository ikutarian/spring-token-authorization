package com.owen.favorite.domain;

public class Token {

    private long userId;
    private String token;

    public Token(long userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public long getUserId() {
        return userId;
    }

    public String getToken() {
        return token;
    }
}
