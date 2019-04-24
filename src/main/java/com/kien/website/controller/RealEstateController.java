package com.kien.website.controller;

import com.kien.website.exception.PostNotFoundException;
import com.kien.website.model.Location;
import com.kien.website.model.SEOObject;
import com.kien.website.model.post.RealEstate;
import com.kien.website.model.post.Tag;
import com.kien.website.repository.LocationRepository;
import com.kien.website.repository.RealEstateRepository;
import com.kien.website.util.UrlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/bat-dong-san")
public class RealEstateController {

    private static long postTotal = 1;

    private Logger logger = LoggerFactory.getLogger(RealEstateController.class);

    @Autowired
    RealEstateRepository realEstateRepository;

    @Autowired
    LocationRepository locationRepository;

    @GetMapping(params = "form")
    @PreAuthorize("hasRole('ADMIN')")
    public String getPostForm() {
        return "realEstate/createRealEstate";
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public @ResponseBody ResponseEntity<String> create(@Valid @RequestBody RealEstate realEstate, BindingResult bindingResult,
                                                       HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Model model) {
        logger.info("Creating a realEstate");
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().stream().forEach( e -> System.err.println(e));
            return ResponseEntity.badRequest().body("Cannot update");
        }

        model.asMap().clear();
        Location location = locationRepository.findById(realEstate.getLocation().getId()).get();
        SEOObject seoObject = realEstate.getSeoObject();
        seoObject.setUrl(UrlUtil.handleTitle(realEstate.getTitle()) + "-" + postTotal++);
        realEstate.setLocation(location);
        System.err.println("Location ID : " + location.getId());
        realEstateRepository.save(realEstate);
        return ResponseEntity.ok(httpServletRequest.getRequestURL().toString()  + "/" + seoObject.getUrl());
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
    @PreAuthorize("hasRole('ADMIN')")
    public @ResponseBody ResponseEntity<String> update(@Valid @RequestBody RealEstate realEstate, BindingResult bindingResult,
                                                       HttpServletRequest httpServletRequest, Model model) {
        logger.info("Update a realEstate");
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Cannot update");
        }

        if (realEstate.getTag() == null) {
            realEstate.setTag(new Tag());
        }

        model.asMap().clear();
        Location location = locationRepository.findById(realEstate.getLocation().getId()).get();
        SEOObject seoObject = realEstate.getSeoObject();
        seoObject.setUrl(UrlUtil.handleTitle(realEstate.getTitle()) + "-" + postTotal++);
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
        throw new PostNotFoundException();
    }

    @GetMapping(params = "lo")
    public String diadiem(@RequestParam("lo") String locationString, Model model) throws Exception{
        logger.info("Getting all real estate by location");
        Long id =  (long) (locationString.charAt(locationString.length()-1) - 48);
        Optional<Location> locationOptional = locationRepository.findById(id);
        if (!locationOptional.isPresent()) {
            throw new PostNotFoundException();
        }
        Location location = locationOptional.get();
        Pageable pageable = PageRequest.of(0,10, Sort.by("lastModified"));
        Page<RealEstate> realEstatePage = realEstateRepository.findByLocation(location,pageable);
        model.addAttribute("location",location);
        if (realEstatePage.hasContent()) {
            List<RealEstate> realEstates = realEstatePage.getContent();
            model.addAttribute("realEstates",realEstates);
        }
        return "realEstate/locationRealEstate";
    }

    @GetMapping
    public String findAll() {
        return "realEstate/viewAll";
    }

}
