package com.huy.webdoan.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "products")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    @Size(min = 3, max = 50)
    private String name;
    @Lob
    private String image;
    @Lob
    private String image2;
    @Lob
    private String image3;
    private Integer price;
    private Integer discount;
    @Size(max = 10000)
    private String description;
    private Boolean available;
    @ManyToOne @JoinColumn(name = "Categoryid")
    private Category category;
    @ManyToOne@JoinColumn(name = "Ratingid")
    private Rating rating;
    @OneToMany(mappedBy = "product")
    List<OrderDetail> orderDetails;

    public Product(Long id, String name, String image, String image2, String image3, Integer price, Integer discount, String description, Boolean available, Category category, Rating rating, List<OrderDetail> orderDetails) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.image2 = image2;
        this.image3 = image3;
        this.price = price;
        this.discount = discount;
        this.description = description;
        this.available = available;
        this.category = category;
        this.rating = rating;
        this.orderDetails = orderDetails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return this.image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Product() {
    }
}
