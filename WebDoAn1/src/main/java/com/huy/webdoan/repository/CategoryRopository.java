package com.huy.webdoan.repository;

import com.huy.webdoan.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRopository extends JpaRepository<Category, Long> {
    Category findOneById(Long id);
}
