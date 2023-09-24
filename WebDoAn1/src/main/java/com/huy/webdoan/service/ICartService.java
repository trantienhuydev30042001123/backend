package com.huy.webdoan.service;

import com.huy.webdoan.model.Cart;
import com.huy.webdoan.model.Category;
import com.huy.webdoan.model.LogIn.User;
import com.huy.webdoan.model.Product;

import java.util.List;
import java.util.Optional;

public interface ICartService {
    void addToCart(Long id,Long userId, Integer size);
    List<Cart> finAll();
    Cart save(Cart cart);
    Optional<Cart> findById(Long id);
    void delete(Long id);

    void deleteAllListCart(Long[] cartIdArray);
    List<Cart> findByUserId(Long userId);
}
