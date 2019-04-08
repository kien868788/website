package com.kien.website.repository;

import com.kien.website.model.Category;
import com.kien.website.model.Location;
import com.kien.website.model.post.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;


public interface PostRepository extends PagingAndSortingRepository<Post,Long>{
    Page<Post> findAll(Pageable pageable);
    Page<Post> findAllByCategory(Category category, Pageable pageable);
    Page<Post> findAllByCategoryAndLocation(Category category, Location location,Pageable pageable);
    Optional<Post> findBySeoObject_Url(String url);
}
