package com.kien.website.repository;

import com.kien.website.model.Location;
import com.kien.website.model.post.RentHouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface RentHouseRepository extends PagingAndSortingRepository<RentHouse,Long> {
    Page<RentHouse>  findAll(Pageable pageable);
    Page<RentHouse> findByLocation(Location location,Pageable pageable);
    Optional<RentHouse> findBySeoObject_Url(String url);
}
