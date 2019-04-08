package com.kien.website.service;

import com.kien.website.model.news.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("newsService")
public interface NewsService {
    void deleteById(Long id);
    void save(News news);
    Page<News> findAll(Pageable pageable);
}
