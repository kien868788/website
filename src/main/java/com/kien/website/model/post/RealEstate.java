package com.kien.website.model.post;

import com.kien.website.model.Location;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "REALESTATE")
public class RealEstate extends SellPost {

    private int lo;

    private String block;

    private String juridical;

    public RealEstate() {
        this.setCategory("/bat-dong-san/");
    }

    public RealEstate(@NotNull String title, String description, BigDecimal price, double square, Tag tag, String street, Location location, int lo, String block, String juridical) {
        super(title, description, price, square, tag, street,  location);
        this.setCategory("/bat-dong-san/");
        this.lo = lo;
        this.block = block;
        this.juridical = juridical;
    }

}

