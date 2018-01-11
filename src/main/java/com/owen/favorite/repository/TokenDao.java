package com.owen.favorite.repository;

import com.owen.favorite.domain.Token;

/**
 * http://www.scienjus.com/restful-token-authorization/
 * http://blog.leapoahead.com/2015/09/07/user-authentication-with-jwt/
 */
public interface TokenDao {

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
