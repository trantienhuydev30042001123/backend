package com.huy.webdoan.service.impl;

import com.huy.webdoan.model.LogIn.Role;
import com.huy.webdoan.model.LogIn.RoleName;
import com.huy.webdoan.repository.IRoleRepository;
import com.huy.webdoan.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    IRoleRepository roleRepository;
    @Override
    public Optional<Role> findByName(RoleName name) {
        return roleRepository.findByName(name);
    }

    @Override
    public List<Role> finAll() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }
}
