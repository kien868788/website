package com.kien.website.repository;

import com.kien.website.model.SEOObject;
import org.springframework.data.repository.CrudRepository;

public interface SEORepository extends CrudRepository<SEOObject,Long> {
}
