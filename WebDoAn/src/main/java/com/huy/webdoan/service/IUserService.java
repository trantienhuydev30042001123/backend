package com.huy.webdoan.service;

import com.huy.webdoan.model.LogIn.User;

import java.util.Optional;

public interface IUserService {
    Optional<User> finByUsername(String name);
    Boolean existsByUsername(String username);
    Boolean existsAllByEmail(String email);
    User save(User user);
}
