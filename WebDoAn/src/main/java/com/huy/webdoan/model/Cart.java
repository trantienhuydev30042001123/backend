package com.huy.webdoan.model;

import com.huy.webdoan.model.LogIn.User;
import lombok.Getter;
import lombok.Setter;

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
    @OneToOne
    private User user;

    public Cart(Product product) {
        this.product = product;
    }

    public Cart(Long id, int quantity, Product product, User user) {
        this.id = id;
        this.quantity = quantity;
        this.product = product;
        this.user = user;
    }

    public Cart() {

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
