package com.poit.repository.service;

import com.poit.repository.entity.User;

public interface UserService {

    User signIn(String login, String password);
}
