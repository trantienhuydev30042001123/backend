package com.huy.webdoan.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huy.webdoan.model.LogIn.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "Orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fullname;
    private String address;
    private String sdt;
    @Temporal(TemporalType.DATE)
    @Column(name = "Createdate")
    private Date createDate = new Date();
//    @OneToOne
//    private Product product;
    @OneToMany
    private List<Product> products;
    @ManyToOne @JoinColumn(name = "Username")
    private User user;
    private Boolean status = false;
    @JsonIgnore
    @OneToMany(mappedBy = "order")
    List<OrderDetail> orderDetails;

    public Order(String fullname, String address, String sdt, List<Product> products, User user) {
        this.fullname = fullname;
        this.address = address;
        this.sdt = sdt;
        this.products =  products;
        this.user = user;
    }

    public Order(Long id, String fullname, String address, String sdt, Date createDate, List<Product> products, User user, Boolean status, List<OrderDetail> orderDetails) {
        this.id = id;
        this.fullname = fullname;
        this.address = address;
        this.sdt = sdt;
        this.createDate = createDate;
        this.products = products;
        this.user = user;
        this.status = status;
        this.orderDetails = orderDetails;
    }

    public Order() {

    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
