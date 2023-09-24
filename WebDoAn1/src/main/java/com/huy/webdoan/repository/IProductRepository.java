package com.huy.webdoan.repository;

import com.huy.webdoan.model.Category;
import com.huy.webdoan.model.Product;
import com.huy.webdoan.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(Category category);
    List<Product> findByRating(Rating rating);
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByPriceBetween(Integer startPrice,Integer endprice);
}
