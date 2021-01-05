package com.springsecurity.security5_practice.repository;

import com.springsecurity.security5_practice.model.User;

public interface UserDetailsDao {
    User findUserByUserName(String uname);
}
