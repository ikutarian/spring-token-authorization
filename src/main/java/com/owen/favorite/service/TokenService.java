package com.owen.favorite.service;

import com.owen.favorite.domain.Token;

public interface TokenService {

    /**
     * 创建一个 token 并关联上指定用户
     */
    Token createToken(long userId);

    /**
     *  检查 token 是否有效
     */
    boolean checkToken(String token);

    /**
     * 清除 token
     */
    void deleteToken (long userId);
}
