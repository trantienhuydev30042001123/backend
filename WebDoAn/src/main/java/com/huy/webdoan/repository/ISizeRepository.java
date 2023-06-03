package com.huy.webdoan.repository;

import com.huy.webdoan.model.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISizeRepository extends JpaRepository<Size, Long> {
    Boolean existsBySize(Integer size);
}
