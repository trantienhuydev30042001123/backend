package com.huy.webdoan.service.impl;

import com.huy.webdoan.model.Cart;
import com.huy.webdoan.model.Product;
import com.huy.webdoan.repository.CartRepository;
import com.huy.webdoan.repository.IAccountRepository;
import com.huy.webdoan.repository.IProductRepository;
import com.huy.webdoan.security.jwt.jwtProvider;
import com.huy.webdoan.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    IProductRepository iProductRepository;
    @Autowired
    jwtProvider jwtProvider;
    @Autowired
    IAccountRepository iAccountRepository;
    @Override
    public void addToCart(Long id) {
        Product product = iProductRepository.findById(id).get();
            Cart cart = new Cart(product);
            cart.setQuantity(1);
            cartRepository.save(cart);
    }

    @Override
    public List<Cart> finAll() {
        return cartRepository.findAll();
    }

    @Override
    public Optional<Cart> findById(Long id) {
        return cartRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public void deleteAllListCart(Long[] cartIdArray) {
        cartRepository.deleteAllById(Arrays.asList(cartIdArray));
    }
}
