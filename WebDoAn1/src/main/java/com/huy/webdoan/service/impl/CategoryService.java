package com.huy.webdoan.service.impl;

import com.huy.webdoan.model.Category;
import com.huy.webdoan.model.Product;
import com.huy.webdoan.repository.CategoryRopository;
import com.huy.webdoan.service.IProductService;
import com.huy.webdoan.service.IcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements IcategoryService {
    @Autowired
    CategoryRopository categoryRopository;
    public List<Category> listCategory(){
      return   categoryRopository.findAll();
    }
    @Override
    public Optional<Category> findById(Long id) {
        return categoryRopository.findById(id);
    }

    @Override
    public Category save(Category category) {
       return categoryRopository.save(category);
    }

    @Override
    public void delete(Long id) {
        categoryRopository.deleteById(id);
    }
}
