package com.huy.webdoan.service.impl;

import com.huy.webdoan.model.LogIn.Role;
import com.huy.webdoan.model.LogIn.User;
import com.huy.webdoan.repository.IAccountRepository;
import com.huy.webdoan.repository.IRoleRopository;
import com.huy.webdoan.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AccountServiceImpl implements IAccountService {
   @Autowired
   private IAccountRepository iAccountRepository;
   @Autowired
   private IRoleRopository iRoleRopository;
    @Override
    public List<User> finAll() {
        return iAccountRepository.findAll();
    }

    @Override
    public List<Role> finAllRoles() {
        return iRoleRopository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return iAccountRepository.findById(id);
    }

    @Override
    public Boolean existsByUserName(String username) {
        return iAccountRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return iAccountRepository.existsByEmail(email);
    }

    @Override
    public User save(User user) {
        return iAccountRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        iAccountRepository.deleteById(id);
    }
}
