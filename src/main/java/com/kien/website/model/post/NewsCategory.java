package com.kien.website.model.post;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class NewsCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<News> newses = new HashSet<>();

    private NewsCategory() {}

    public NewsCategory(@NotNull String name) {
        this.name = name;
    }

    public boolean add(News news) {
        news.setCategory(this);
        return newses.add(news);
    }

    public Long getId() {
        return this.id;
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
}
