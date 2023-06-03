package com.huy.webdoan.service;

import com.huy.webdoan.model.Cart;
import com.huy.webdoan.model.LogIn.User;
import com.huy.webdoan.model.Product;

import java.util.List;
import java.util.Optional;

public interface ICartService {
    void addToCart(Long id);
    List<Cart> finAll();

    Optional<Cart> findById(Long id);
    void delete(Long id);

    void deleteAllListCart(Long[] cartIdArray);
}
