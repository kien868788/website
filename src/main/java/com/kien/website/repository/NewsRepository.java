package com.kien.website.repository;

import com.kien.website.model.post.News;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NewsRepository extends PagingAndSortingRepository<News,Long>{
}
