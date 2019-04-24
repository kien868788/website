package com.kien.website.model.post;

import com.kien.website.model.Location;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = InheritanceType.JOINED)
abstract public class SellPost extends Post{

    private BigDecimal price;

    private double square;



    private String street;

    private String category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "LOCATION_ID",nullable = false)
    private Location location;

    private Tag tag;

    public SellPost() {}

    public SellPost(@NotNull String title, String description,  BigDecimal price, double square, Tag tag, String street, Location location) {
        super(title, description);
        this.price = price;
        this.square = square;
        this.tag = tag;
        this.street = street;
        this.location = location;
    }

    @Access(AccessType.PROPERTY)
    public Tag getTag() {
        return (this.tag == null) ? new Tag() : this.tag;
    }
}

