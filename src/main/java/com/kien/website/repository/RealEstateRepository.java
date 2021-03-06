package com.kien.website.repository;

import com.kien.website.model.Location;
import com.kien.website.model.post.RealEstate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;


public interface RealEstateRepository extends PagingAndSortingRepository<RealEstate,Long>{
    Page<RealEstate> findAll(Pageable pageable);
    Page<RealEstate> findByLocation(Location location, Pageable pageable);
    Optional<RealEstate> findBySeoObject_Url(String url);
}
