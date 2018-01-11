package com.owen.favorite.service.impl;

import com.owen.favorite.domain.User;
import com.owen.favorite.repository.UserDao;
import com.owen.favorite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
