package com.kien.website.controller;

import com.kien.website.exception.PostNotFoundException;
import com.kien.website.model.Location;
import com.kien.website.model.SEOObject;
import com.kien.website.model.post.RentHouse;
import com.kien.website.repository.LocationRepository;
import com.kien.website.repository.RentHouseRepository;
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
@RequestMapping("/nha-cho-thue")
public class RentHouseController {
    private static long postTotal = 1;
    private Logger logger = LoggerFactory.getLogger(RentHouseController.class);

    @Autowired
    RentHouseRepository rentHouseRepository;

    @Autowired
    LocationRepository locationRepository;

    @GetMapping(params = "form")
    @PreAuthorize("hasRole('ADMIN')")
    public String getPostForm() {
        return "rentHouse/createRentHouse";
    }

    @GetMapping(value = "/{id}", params = "form")
    public String updateForm(@PathVariable("id") Long id, Model model) throws Exception {
        Optional<RentHouse> postOptional = rentHouseRepository.findById(id);
        RentHouse rentHouse = postOptional.get();
        model.addAttribute("rentHouse", rentHouse);
        model.addAttribute("tag", rentHouse.getTag());
        model.addAttribute("seo", rentHouse.getSeoObject());
        return "rentHouse/updateRentHouse";
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public @ResponseBody
    ResponseEntity<String> create(@Valid @RequestBody RentHouse rentHouse, BindingResult bindingResult,
                                  HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Model model) {
        logger.info("Creating a rent house");
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Cannot update");
        }

        model.asMap().clear();
        Location location = locationRepository.findById(rentHouse.getLocation().getId()).get();
        SEOObject seoObject = rentHouse.getSeoObject();
        seoObject.setUrl(UrlUtil.handleTitle(rentHouse.getTitle()) + "-" + postTotal++);
        rentHouse.setLocation(location);
        rentHouseRepository.save(rentHouse);
        return ResponseEntity.ok(httpServletRequest.getRequestURL().toString()  + "/" + seoObject.getUrl());
    }

    @GetMapping("/{url}")
    public String view(@PathVariable String url, Model model) throws Exception {
        logger.info("Getting a realEstate");
        Optional<RentHouse> postOptional = rentHouseRepository.findBySeoObject_Url(url);
        if (postOptional.isPresent()) {
            RentHouse rentHouse = postOptional.get();
            model.addAttribute("rentHouse", rentHouse);
            model.addAttribute("tag", rentHouse.getTag());
            model.addAttribute("seo", rentHouse.getSeoObject());
            return "rentHouse/viewRentHouse";
        }
        throw new PostNotFoundException();
    }

    @PostMapping(value = "/{id}", params = "form")
    @PreAuthorize("hasRole('ADMIN')")
    public @ResponseBody ResponseEntity<String> update(@Valid @RequestBody RentHouse rentHouse, BindingResult bindingResult,
                                                       HttpServletRequest httpServletRequest, Model model) {
        logger.info("Update a realEstate");
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Cannot update");
        }

        model.asMap().clear();
        Location location = locationRepository.findById(rentHouse.getLocation().getId()).get();
        SEOObject seoObject = rentHouse.getSeoObject();
        seoObject.setUrl(UrlUtil.handleTitle(rentHouse.getTitle()) + "-" + postTotal++);
        rentHouse.setLocation(location);
        rentHouseRepository.save(rentHouse);
        return ResponseEntity.ok(httpServletRequest.getRequestURL().toString().replace(rentHouse.getId().toString(),"")
                + seoObject.getUrl());
    }

    @GetMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@Param("id") Long id) {
        rentHouseRepository.deleteById(id);
        return "redirect:/users/admin";
    }

    @GetMapping(params = "lo")
    public String diadiem(@RequestParam("lo") String locationString, Model model) throws Exception{
        logger.info("Getting all rent house by location");
        Long id =  (long) (locationString.charAt(locationString.length()-1) - 48);
        Optional<Location> locationOptional = locationRepository.findById(id);
        if (!locationOptional.isPresent()) {
            throw new PostNotFoundException();
        }
        Location location = locationOptional.get();
        Pageable pageable = PageRequest.of(0,10, Sort.by("lastModified"));
        Page<RentHouse> rentHousePage = rentHouseRepository.findByLocation(location,pageable);
        model.addAttribute("location",location);
        if (rentHousePage.hasContent()) {
            List<RentHouse> rentHouses = rentHousePage.getContent();
            model.addAttribute("rentHouses",rentHouses);
        }
        return "rentHouse/locationRentHouse";
    }

}
