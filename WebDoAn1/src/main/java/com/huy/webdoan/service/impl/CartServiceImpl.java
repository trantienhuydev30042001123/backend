package com.huy.webdoan.service.impl;

import com.huy.webdoan.model.Cart;
import com.huy.webdoan.model.Category;
import com.huy.webdoan.model.Product;
import com.huy.webdoan.model.Size;
import com.huy.webdoan.repository.CartRepository;
import com.huy.webdoan.repository.IAccountRepository;
import com.huy.webdoan.repository.IProductRepository;
import com.huy.webdoan.repository.ISizeRepository;
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
    @Autowired
    ISizeRepository iSizeRepository;

    @Override
    public void addToCart(Long id, Long userId, Integer size) {
        Product product = iProductRepository.findById(id).get();

        // Tìm kiếm Cart dựa trên id sản phẩm
        Optional<Cart> optionalCart = cartRepository.findByProduct(product);

        if (optionalCart.isPresent()) {
            // Nếu Cart đã tồn tại, tăng quantity lên 1
            Cart cart = optionalCart.get();
            cart.setQuantity(cart.getQuantity() + 1);
            cartRepository.save(cart);
        } else {
            // Nếu Cart chưa tồn tại, tạo mới Cart và đặt quantity là 1
            Cart cart = new Cart(product);
            cart.setQuantity(1);
            cart.setUser(userId);
            cart.setSize(size);

            cartRepository.save(cart);
        }
    }

    @Override
    public List<Cart> finAll() {
        return cartRepository.findAll();
    }

    @Override
    public List<Cart> findByUserId(Long userId) {
        return cartRepository.findByUser(userId);
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

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }
}
