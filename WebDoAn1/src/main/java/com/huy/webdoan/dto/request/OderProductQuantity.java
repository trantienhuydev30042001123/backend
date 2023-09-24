package com.huy.webdoan.dto.request;

public class OderProductQuantity {
private Long id;
private Integer quantity;

    public OderProductQuantity(Long id, Integer quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public OderProductQuantity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
