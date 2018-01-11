package com.owen.favorite.service.impl;

import com.owen.favorite.domain.Token;
import com.owen.favorite.repository.TokenDao;
import com.owen.favorite.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenDao tokenRepository;

    @Override
    public Token createToken(long userId) {
        return tokenRepository.createToken(userId);
    }

    @Override
    public boolean checkToken(String token) {
        return tokenRepository.checkToken(token);
    }

    @Override
    public void deleteToken(long userId) {
        tokenRepository.deleteToken(userId);
    }
}
