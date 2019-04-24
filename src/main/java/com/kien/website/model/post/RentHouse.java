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
@Table(name = "RENTHOUSE")
public class RentHouse extends SellPost{

    public RentHouse() {
        this.setCategory("/nha-cho-thue/");
    }

    public RentHouse(@NotNull String title, String description, BigDecimal price, double square, Tag tag, String street,Location location) {
        super(title, description, price, square, tag, street,  location);
        this.setCategory("/nha-cho-thue/");
    }
}
