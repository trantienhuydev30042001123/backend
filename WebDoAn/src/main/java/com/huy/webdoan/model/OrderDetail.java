package com.huy.webdoan.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
@Getter
@Setter
@Entity
@Table(name = "Orderdetails")
public class OrderDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double price;
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "Productid")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "Orderid")
    private Order order;
}