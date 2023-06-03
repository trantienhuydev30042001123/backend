package com.huy.webdoan.dto.request;

import com.huy.webdoan.model.Cart;
import com.huy.webdoan.model.Product;

import java.util.List;

public class OrderInput {
    private String fullname;

    private String address;

    private String sdt;
    private List<Long> producId;

    public OrderInput() {
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

//    public List<OderProductQuantity> getOderProductQuantityList() {
//        return oderProductQuantityList;
//    }
//
//    public void setOderProductQuantityList(List<OderProductQuantity> oderProductQuantityList) {
//        this.oderProductQuantityList = oderProductQuantityList;
//    }
}
