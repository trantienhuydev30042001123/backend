package com.huy.webdoan.service;

import com.huy.webdoan.model.LogIn.Role;
import com.huy.webdoan.model.LogIn.RoleName;
import com.huy.webdoan.model.LogIn.User;

import java.util.List;
import java.util.Optional;

public interface IRoleService {
    Optional<Role> findByName(RoleName name);

    List<Role> finAll();
    Optional<Role> findById(Long id);
    Role save(Role role);
    void delete(Long id);
}
