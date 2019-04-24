package com.kien.website.repository;

import com.kien.website.model.Location;
import com.kien.website.model.post.SellPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SellPostRepository extends PagingAndSortingRepository<SellPost,Long> {
    Page<SellPost> findAll(Pageable pageable);
    Page<SellPost> findAllByLocation(Location location, Pageable pageable);
}
