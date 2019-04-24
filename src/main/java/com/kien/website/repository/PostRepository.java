package com.kien.website.repository;

import com.kien.website.model.post.Post;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostRepository extends PagingAndSortingRepository<Post,Long> {
}
