package com.huy.webdoan.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Orderdetails")
public class OrderDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long price;
    private Long quantity;
    private Integer size;
    @ManyToOne
    @JoinColumn(name = "Productid")
    private Product product;

    public OrderDetail(Order order, Product product) {
        this.product = product;
    }

    public OrderDetail() {
    }

    public OrderDetail(Long id, Long price, Long quantity, Product product, Order order, Integer size) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.product = product;
        this.size = size;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}