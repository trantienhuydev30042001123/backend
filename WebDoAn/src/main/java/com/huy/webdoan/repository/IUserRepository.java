package com.huy.webdoan.repository;

import com.huy.webdoan.model.LogIn.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String name);//Tìm kiếm User naem có tồn tại trong db ko
    Boolean existsByUsername(String username); // username tồn tại rồi thì không cho tạo
    Boolean existsAllByEmail(String email); //Email tồn tại rồi thi không cho tạo
}
