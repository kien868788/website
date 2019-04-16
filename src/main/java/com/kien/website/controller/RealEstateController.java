package com.kien.website.controller;

import com.kien.website.exception.PostNotFoundException;
import com.kien.website.model.Category;
import com.kien.website.model.Location;
import com.kien.website.model.SEOObject;
import com.kien.website.model.post.RealEstate;
import com.kien.website.model.post.RealEastateFormWrapper;
import com.kien.website.repository.CategoryRepository;
import com.kien.website.repository.LocationRepository;
import com.kien.website.repository.RealEstateRepository;
import com.kien.website.util.UrlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/posts")
public class RealEstateController {

    private static long postTotal = 1;

    private Logger logger = LoggerFactory.getLogger(RealEstateController.class);

    @Autowired
    RealEstateRepository realEstateRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping(params = "form")
    @PreAuthorize("hasRole('ADMIN')")
    public String getPostForm() {
        return "realEstate/createRealEstate";
    }

    @PostMapping
    @CrossOrigin
    @PreAuthorize("hasRole('ADMIN')")
    public @ResponseBody ResponseEntity<String> create(@Valid @RequestBody RealEastateFormWrapper realEastateFormWrapper, BindingResult bindingResult,
                                                       HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Model model) {
        logger.info("Creating a realEstate");
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Cannot update");
        }
        model.asMap().clear();
        RealEstate realEstate = realEastateFormWrapper.getRealEstate();
        Location location = locationRepository.findById(realEstate.getLocation().getId()).get();
        SEOObject seoObject = realEstate.getSeoObject();
        seoObject.setUrl(UrlUtil.handleTitle(realEstate.getTitle()) + "-" + postTotal++);
        Category category = categoryRepository.findById(realEastateFormWrapper.getCategory()).get();
        realEstate.setLocation(location);
        System.err.println("Location ID : " + location.getId());
        realEstate.setCategory(category);
        realEstateRepository.save(realEstate);
        return ResponseEntity.ok(httpServletRequest.getRequestURL().toString()  + "/" + seoObject.getUrl());
    }

    @GetMapping("/{url}")
    public String view(@PathVariable String url, Model model) throws Exception {
        logger.info("Getting a realEstate");
        Optional<RealEstate> postOptional = realEstateRepository.findBySeoObject_Url(url);
        if (postOptional.isPresent()) {
            RealEstate realEstate = postOptional.get();
            model.addAttribute("realEstate", realEstate);
            model.addAttribute("tag", realEstate.getTag());
            model.addAttribute("seo", realEstate.getSeoObject());
            return "realEstate/viewRealEstate";
        }
        throw new PostNotFoundException(url);
    }


    @GetMapping(value = "/{id}", params = "form")
    public String updateForm(@PathVariable("id") Long id, Model model) throws Exception {
        Optional<RealEstate> postOptional = realEstateRepository.findById(id);
        RealEstate realEstate = postOptional.get();
        model.addAttribute("realEstate", realEstate);
        model.addAttribute("tag", realEstate.getTag());
        model.addAttribute("seo", realEstate.getSeoObject());
        return "realEstate/updateRealEstate";
    }

    @PostMapping(value = "/{id}", params = "form")
    @CrossOrigin
    @PreAuthorize("hasRole('ADMIN')")
    public @ResponseBody ResponseEntity<String> update(@Valid @RequestBody RealEastateFormWrapper realEastateFormWrapper, BindingResult bindingResult,
                                                       HttpServletRequest httpServletRequest, Model model) {
        logger.info("Update a realEstate");
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Cannot update");
        }
        model.asMap().clear();
        RealEstate realEstate = realEastateFormWrapper.getRealEstate();
        Location location = locationRepository.findById(realEstate.getLocation().getId()).get();
        Category category = categoryRepository.findById(realEastateFormWrapper.getCategory()).get();
        SEOObject seoObject = realEstate.getSeoObject();
        seoObject.setUrl(UrlUtil.handleTitle(realEstate.getTitle()) + "-" + postTotal++);
        realEstate.setCategory(category);
        realEstate.setLocation(location);
        realEstateRepository.save(realEstate);
        return ResponseEntity.ok(httpServletRequest.getRequestURL().toString().replace(realEstate.getId().toString(),"")
                 + seoObject.getUrl());
    }


    @GetMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@Param("id") Long id) {
        realEstateRepository.deleteById(id);
        return "redirect:/users/admin";
    }
}
