package com.poit.repository.service.impl;

import com.poit.repository.entity.User;
import com.poit.repository.UserRepository;
import com.poit.repository.service.UserService;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User signIn(String login, String password) {
        try {
            var user = userRepository.findByLogin(login);
            return user.getPassword().equals(password) ? user : null;
        } catch (Exception e) {
            return null;
        }
    }
}
