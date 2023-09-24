package com.huy.webdoan.model;

import com.huy.webdoan.model.LogIn.Role;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fullname;
    private String address;
    private String sdt;
    private int totalMoney;

    @Temporal(TemporalType.DATE)
    @Column(name = "Createdate")
    private Date createDate = new Date();
    @OneToMany
    @JoinColumn(name = "orderDetailId")
    private List<OrderDetail> orderDetail;
    private Long user;
    private int size;
    private int status = 0;
    public Order(String fullname, String address, String sdt, Long user, int totalMoney) {
        this.fullname = fullname;
        this.address = address;
        this.user = user;
        this.sdt = sdt;
        this.totalMoney = totalMoney;
    }

    public Order() {

    }


    public List<OrderDetail> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderDetail> orderDetail) {
        this.orderDetail = orderDetail;
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

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Integer getStatus() {
        return status;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
