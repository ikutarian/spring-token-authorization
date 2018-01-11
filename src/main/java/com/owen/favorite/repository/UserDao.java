package com.owen.favorite.repository;

import com.owen.favorite.domain.User;

public interface UserDao {

    User findByUsername(String username);
}
