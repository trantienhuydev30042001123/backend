package com.huy.webdoan.repository;

import com.huy.webdoan.model.Cart;
import com.huy.webdoan.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    Optional<Cart> findByProduct(Product product);

    List<Cart> findByUser(Long userId);
}
