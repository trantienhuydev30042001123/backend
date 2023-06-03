package com.huy.webdoan.service;

import com.huy.webdoan.model.LogIn.Role;
import com.huy.webdoan.model.LogIn.User;
import com.huy.webdoan.model.Size;

import java.util.List;
import java.util.Optional;

public interface IAccountService {
    List<User> finAll();
    List<Role> finAllRoles();
    Optional<User> findById(Long id);
    Boolean existsByUserName(String username);
    Boolean existsByEmail(String email);
    User save(User user);
    void delete(Long id);
}
