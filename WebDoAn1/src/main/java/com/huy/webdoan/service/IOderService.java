package com.huy.webdoan.service;

import com.huy.webdoan.dto.request.OrderInput;
import com.huy.webdoan.model.Cart;
import com.huy.webdoan.model.Order;
import com.huy.webdoan.model.OrderDetail;
import com.huy.webdoan.service.impl.OderServiceImpl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IOderService {
    void  placeOrder(OrderInput orderInput);
    List<Map> finAll(Long userId);
    List<Map> finAll();

    List<OrderDetail> getlist();

    Order deleteOder(Long id);
    Order updateStatus(Long id, Integer status);
}
