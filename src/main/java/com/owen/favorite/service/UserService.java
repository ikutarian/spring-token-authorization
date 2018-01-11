package com.owen.favorite.service;

import com.owen.favorite.domain.User;

public interface UserService {

    User findByUsername(String username);
}
