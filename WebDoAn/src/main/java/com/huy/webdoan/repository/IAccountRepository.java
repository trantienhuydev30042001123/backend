package com.huy.webdoan.repository;

import com.huy.webdoan.model.LogIn.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRepository extends JpaRepository<User,Long> {
    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    User findByname(String currentUser);
}
