package com.kien.website.controller;

import com.kien.website.model.post.RealEstate;
import com.kien.website.repository.RealEstateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    RealEstateRepository realEstateRepository;


    @GetMapping("/")
    public String index(Model model) {
        Pageable pageableRealEstate = PageRequest.of(0,8, Sort.by("lastModified"));
        Page<RealEstate> realEstatePage = realEstateRepository.findAll(pageableRealEstate);
        if(realEstatePage.hasContent()) {
            model.addAttribute("realEstates",realEstatePage.getContent());
        }
        return "index";
    }
}
