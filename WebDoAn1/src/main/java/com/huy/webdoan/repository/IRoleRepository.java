package com.huy.webdoan.repository;

import com.huy.webdoan.model.LogIn.Role;
import com.huy.webdoan.model.LogIn.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IRoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(RoleName name);
}
