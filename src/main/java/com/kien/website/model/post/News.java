package com.kien.website.model.post;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "NEWS")
public class News extends Post {

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    private NewsCategory category;

    public News() {}

    public News(@NotNull String title, String description) {
        super(title, description);
    }

}
