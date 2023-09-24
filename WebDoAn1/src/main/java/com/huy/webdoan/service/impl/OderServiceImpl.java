package com.huy.webdoan.service.impl;

import com.huy.webdoan.dto.request.OderProductQuantity;
import com.huy.webdoan.dto.request.OrderInput;
import com.huy.webdoan.model.*;
import com.huy.webdoan.model.LogIn.User;
import com.huy.webdoan.repository.*;
import com.huy.webdoan.security.jwt.jwtTokenFilter;
import com.huy.webdoan.service.IOderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OderServiceImpl implements IOderService {
    @Autowired
    private IOderRepository iOderRepository;
    @Autowired
    private IProductRepository iProductRepository;
    @Autowired
    private IAccountRepository iAccountRepository;
    @Autowired
    private iOrderDetailRepository iOrderDetailRepository1;
    @Autowired
    private CartRepository cartRepository;
    @Override
    public void placeOrder(OrderInput orderInput) {
        Order order = new Order(
                orderInput.getFullname(),
                orderInput.getAddress(),
                orderInput.getSdt(),
                orderInput.getUserId(),
                orderInput.getTotalMoney()
        );
        List<Product> products = iProductRepository.findAllById(orderInput.getProducId());
        List<Long> prices = orderInput.getPrice();
        List<Long> quantities = orderInput.getQuantity();
        List<Integer> sizes = orderInput.getSize();

        OrderDetail orderDetail = null;
        List<OrderDetail> orderDetailIds = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            Long price = prices.get(i);
            Long quantity = quantities.get(i);
            Integer size = sizes.get(i);

            orderDetail = new OrderDetail(order, product);

            orderDetail.setPrice(price);
            orderDetail.setQuantity(quantity);
            orderDetail.setSize(size);
            iOrderDetailRepository1.save(orderDetail);
            orderDetailIds.add(orderDetail);
        }
        order.setOrderDetail(orderDetailIds);
        iOderRepository.save(order);
    }
    @Override
    public List<Map> finAll(Long userId) {
        List<Order> orders = iOderRepository.findByUser(userId)
                .stream().sorted(Comparator.comparingInt(Order::getStatus)).collect(Collectors.toList());
        List<Map> list = new ArrayList<>();
        if (orders != null && orders.size() > 0){
            for (Order order : orders){
                Map<String, Object> map = new HashMap<>();
                map.put("oder", order);
                List<Product> products = order.getOrderDetail().stream().map(OrderDetail::getProduct).collect(Collectors.toList());
                List<String> nameProducts = products.stream().map(Product::getName).collect(Collectors.toList());
                List<String> images =  products.stream().map(Product::getImage).collect(Collectors.toList());

                List<Long> prices =  order.getOrderDetail().stream().map(OrderDetail::getPrice).collect(Collectors.toList());
                List<Long> quantity =  order.getOrderDetail().stream().map(OrderDetail::getQuantity).collect(Collectors.toList());
                List<Integer> sizes = order.getOrderDetail().stream()
                        .map(OrderDetail::getSize)
                        .collect(Collectors.toList());

                map.put("nameProducts", nameProducts);
                map.put("images", images);
                map.put("prices", prices);
                map.put("quantity", quantity);
                map.put("sizes", sizes);
                map.put("status", order.getStatus());
                map.put("createdDate", order.getCreateDate());


                list.add(map);
            }
        }
        return list;
    }

    @Override
    public List<Map> finAll() {
        List<Order> orders = iOderRepository.findAll()
                .stream().sorted(Comparator.comparingInt(Order::getStatus)).collect(Collectors.toList());

        List<Map> list = new ArrayList<>();
        if (orders != null && orders.size() > 0){
            for (Order order : orders){
                Map<String, Object> map = new HashMap<>();
                map.put("oder", order);
                List<Product> products = order.getOrderDetail().stream().map(OrderDetail::getProduct).collect(Collectors.toList());
                List<String> nameProducts = products.stream().map(Product::getName).collect(Collectors.toList());
                List<String> images =  products.stream().map(Product::getImage).collect(Collectors.toList());

                List<Long> prices =  order.getOrderDetail().stream().map(OrderDetail::getPrice).collect(Collectors.toList());
                List<Long> quantity =  order.getOrderDetail().stream().map(OrderDetail::getQuantity).collect(Collectors.toList());
                List<Integer> sizes = order.getOrderDetail().stream()
                        .map(OrderDetail::getSize)
                        .collect(Collectors.toList());
                map.put("id", order.getId());
                map.put("nameProducts", nameProducts);
                map.put("images", images);
                map.put("prices", prices);
                map.put("quantity", quantity);
                map.put("sizes", sizes);
                map.put("status", order.getStatus());
                map.put("createdDate", order.getCreateDate());
                map.put("fullname", order.getFullname());
                map.put("sdt", order.getSdt());
                map.put("address", order.getAddress());


                list.add(map);


            }
        }
        return list;
    }

    @Override
    public List<OrderDetail> getlist() {
        return iOrderDetailRepository1.findAll();
    }

    public Page<Map<String, Object>> findAllOrders(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Order> ordersPage = iOderRepository.findAll(pageable);

        return ordersPage.map(order -> {
            Map<String, Object> map = new HashMap<>();
            map.put("order", order);

            List<Product> products = order.getOrderDetail().stream()
                    .map(OrderDetail::getProduct)
                    .collect(Collectors.toList());
            List<String> nameProducts = products.stream()
                    .map(Product::getName)
                    .collect(Collectors.toList());
            List<String> images = products.stream()
                    .map(Product::getImage)
                    .collect(Collectors.toList());
            List<Long> prices = order.getOrderDetail().stream()
                    .map(OrderDetail::getPrice)
                    .collect(Collectors.toList());
            List<Long> quantity = order.getOrderDetail().stream()
                    .map(OrderDetail::getQuantity)
                    .collect(Collectors.toList());
            List<Integer> sizes = order.getOrderDetail().stream()
                    .map(OrderDetail::getSize)
                    .collect(Collectors.toList());


            map.put("nameProducts", nameProducts);
            map.put("images", images);
            map.put("prices", prices);
            map.put("quantity", quantity);
            map.put("sizes", sizes);
            map.put("status", order.getStatus());


            return map;
        });
    }

    @Override
    public Order deleteOder(Long id) {
        Order order = iOderRepository.findById(id).orElse(null);
        if (order != null){
            order.setStatus(3); // hủy đơn
            iOderRepository.save(order);
            return order;
        }
     return null;
    }

    @Override
    public Order updateStatus(Long id, Integer status) {
        Order order = iOderRepository.findById(id).orElse(null);
        if (order != null){
            order.setStatus(status);
            iOderRepository.save(order);
            return order;
        }
        return null;
    }

}
