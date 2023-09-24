package com.huy.webdoan.dto.request;

import java.util.List;

public class OrderInput {
    private String fullname;

    private String address;
    private List<Integer> size;
    private String sdt;
    private List<Long> producId;
    private List<Long> price;
    private List<Long> quantity;

    private int totalMoney;
    private Long userId;

    public List<Integer> getSize() {
        return size;
    }

    public void setSize(List<Integer> size) {
        this.size = size;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public OrderInput() {
    }

    public List<Long> getPrice() {
        return price;
    }

    public void setPrice(List<Long> price) {
        this.price = price;
    }

    public List<Long> getQuantity() {
        return quantity;
    }

    public void setQuantity(List<Long> quantity) {
        this.quantity = quantity;
    }

    public List<Long> getProducId() {
        return producId;
    }

    public void setProducId(List<Long> producId) {
        this.producId = producId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

//    public List<OderProductQuantity> getOderProductQuantityList() {
//        return oderProductQuantityList;
//    }
//
//    public void setOderProductQuantityList(List<OderProductQuantity> oderProductQuantityList) {
//        this.oderProductQuantityList = oderProductQuantityList;
//    }
}
