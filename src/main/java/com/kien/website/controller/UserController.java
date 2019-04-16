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
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    RealEstateRepository realEstateRepository;

    @GetMapping("/admin")
    public String adminLogin(Model model) {
        Pageable pageable = PageRequest.of(0,50,Sort.by("lastModified"));
        Page<RealEstate> realEstatesPage = realEstateRepository.findAll(pageable);
        if (realEstatesPage.hasContent()) {
            List<RealEstate> realEstates = realEstatesPage.getContent();
            model.addAttribute("realEstates", realEstates);
        }
        return "users/admin";
    }

}
