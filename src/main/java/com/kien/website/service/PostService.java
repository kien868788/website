package com.kien.website.service;

import com.kien.website.model.Category;
import com.kien.website.model.post.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface PostService {
    void deleteById(Long id);
    void save(Post post);
    Page<Post> findByCategory(Category category,Pageable pageable);
    Optional<Post> findBySEOObjectUrl(String url);
    Optional<Post> findById(Long id);
    Page<Post> findAll(Pageable pageable);
}
