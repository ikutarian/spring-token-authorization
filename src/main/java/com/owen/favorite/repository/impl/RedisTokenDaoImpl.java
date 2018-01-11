package com.owen.favorite.repository.impl;

import com.owen.favorite.constant.ApiConstant;
import com.owen.favorite.domain.Token;
import com.owen.favorite.repository.TokenDao;
import com.owen.favorite.util.TokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * 通过 Redis 管理 token 的实现类
 */
@Repository
public class RedisTokenDaoImpl implements TokenDao {

    private RedisTemplate<Long, String> redisTemplate;

    @Override
    public Token createToken(long userId) {
        String token = TokenUtil.createToken(userId);
        // 存储到 redis 并设置过期时间
        redisTemplate.boundValueOps(userId).set(token, ApiConstant.Token.EXPIRE_DAYS, TimeUnit.DAYS);
        return new Token(userId, token);
    }

    @Override
    public boolean checkToken(String tokenFromClient) {
        if (StringUtils.isEmpty(tokenFromClient)) {
            return false;
        }
        Long userId = TokenUtil.getUserIdFromToken(tokenFromClient);
        if (userId == null) {
            return false;
        }
        String tokenInRedis = redisTemplate.boundValueOps(userId).get();
        if (tokenFromClient.equals(tokenInRedis)) {
            // 如果验证成功，说明此用户进行了一次有效操作，延长 token 的过期时间
            redisTemplate.boundValueOps(userId).expire(ApiConstant.Token.EXPIRE_DAYS, TimeUnit.DAYS);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void deleteToken(long userId) {
        redisTemplate.delete(userId);
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate<Long, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
