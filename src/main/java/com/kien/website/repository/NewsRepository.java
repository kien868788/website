package com.kien.website.repository;

import com.kien.website.model.post.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface NewsRepository extends PagingAndSortingRepository<News,Long>{
    Page<News> findAll(Pageable pageable);
    Optional<News> findBySeoObject_Url(String url);
}
