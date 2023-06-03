package com.huy.webdoan.service.impl;

import com.huy.webdoan.dto.request.OderProductQuantity;
import com.huy.webdoan.dto.request.OrderInput;
import com.huy.webdoan.model.Cart;
import com.huy.webdoan.model.LogIn.User;
import com.huy.webdoan.model.Order;
import com.huy.webdoan.model.OrderDetail;
import com.huy.webdoan.model.Product;
import com.huy.webdoan.repository.CartRepository;
import com.huy.webdoan.repository.IAccountRepository;
import com.huy.webdoan.repository.IOderRepository;
import com.huy.webdoan.repository.IProductRepository;
import com.huy.webdoan.security.jwt.jwtTokenFilter;
import com.huy.webdoan.service.IOderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OderServiceImpl implements IOderService {
    @Autowired
    private IOderRepository iOderRepository;
    @Autowired
    private IProductRepository iProductRepository;
    @Autowired
    private IAccountRepository iAccountRepository;
    @Autowired
    private CartRepository cartRepository;
    @Override
    public void placeOrder(OrderInput orderInput) {
//      List<OderProductQuantity> productQuantityList = orderInput.getOderProductQuantityList();
//      for (OderProductQuantity o: productQuantityList){
//        Product product =  iProductRepository.findById(o.getId()).get();
//          List<Cart> cart = cartRepository.findAll();
        List<Product> product = iProductRepository.findAllById(orderInput.getProducId());
//        List<Product> productIds = new ArrayList<>();
//        for (String id : orderInput.getProducId()) {
//            productIds.add(new Product(id));
//        }
//        List<TelcoSub> telcoSub = adminTelcoSubRepository.findAllByIdIn(telcoSubIds);


          String CurrentUser = jwtTokenFilter.CURRENT_USER;
          User user = iAccountRepository.findByname(CurrentUser);

        Order order = new Order(
                  orderInput.getFullname(),
                  orderInput.getAddress(),
                  orderInput.getSdt(),
//                  product,
                  product,
                  user
        );
        iOderRepository.save(order);
      }

    @Override
    public List<Order> finAll() {
        return iOderRepository.findAll();
    }

}
