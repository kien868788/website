package com.kien.website.repository;

import com.kien.website.model.post.NewsCategory;
import org.springframework.data.repository.CrudRepository;

public interface NewsCategoryRepository extends CrudRepository<NewsCategory,Long> {
}
