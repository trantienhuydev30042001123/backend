package com.huy.webdoan.service;

import com.huy.webdoan.model.Category;
import com.huy.webdoan.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.Optional;

public interface IcategoryService {
    Optional<Category> findById(Long id);
    Category save(Category category);
    void delete(Long id);
}
