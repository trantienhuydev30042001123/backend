package com.huy.webdoan.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Cart")
public class Cart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int quantity;
    @OneToOne
    private Product product;
    private Integer size;

    private Long user;

    public Cart(Product product) {
        this.product = product;
    }

    public Cart(Long id, int quantity, Product product, Long user, Integer size) {
        this.id = id;
        this.quantity = quantity;
        this.product = product;
        this.user = user;
        this.size = size;
    }

    public Cart() {

    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
