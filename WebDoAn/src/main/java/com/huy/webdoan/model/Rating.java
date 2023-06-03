package com.huy.webdoan.model;

import com.huy.webdoan.model.LogIn.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "Rating")
public class Rating implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer stars;

    public Rating(Long id, Integer stars) {
        this.id = id;
        this.stars = stars;
    }

    public Rating() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }
}
