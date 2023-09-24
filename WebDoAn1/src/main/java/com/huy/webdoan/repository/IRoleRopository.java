package com.huy.webdoan.repository;

import com.huy.webdoan.model.LogIn.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRopository extends JpaRepository<Role,Long> {
}
