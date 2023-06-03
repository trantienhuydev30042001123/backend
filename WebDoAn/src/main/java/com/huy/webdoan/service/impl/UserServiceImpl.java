package com.huy.webdoan.service.impl;

import com.huy.webdoan.model.LogIn.User;
import com.huy.webdoan.repository.IUserRepository;
import com.huy.webdoan.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    IUserRepository userRepository;

    @Override
    public Optional<User> finByUsername(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsAllByEmail(String email) {
        return userRepository.existsAllByEmail(email);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
