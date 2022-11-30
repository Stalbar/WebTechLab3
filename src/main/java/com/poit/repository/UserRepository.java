package com.poit.repository;

import com.poit.repository.entity.User;

public interface UserRepository {
    User findByLogin(String login);
}
