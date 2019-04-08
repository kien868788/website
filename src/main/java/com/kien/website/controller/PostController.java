package com.kien.website.controller;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.kien.website.exception.PostNotFoundException;
import com.kien.website.model.Category;
import com.kien.website.model.Location;
import com.kien.website.model.SEOObject;
import com.kien.website.model.post.Post;
import com.kien.website.model.post.PostFormWrapper;
import com.kien.website.model.post.Tag;
import com.kien.website.repository.CategoryRepository;
import com.kien.website.repository.LocationRepository;
import com.kien.website.repository.SEORepository;
import com.kien.website.service.PostService;
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
public class PostController {

    static long postTotal = 0;

    private Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    PostService postService;

    @Autowired
    SEORepository seoRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping(params = "form")
    @PreAuthorize("hasRole('ADMIN')")
    public String getPostForm() {
        return "posts/create";
    }

    @PostMapping
    @CrossOrigin
    @PreAuthorize("hasRole('ADMIN')")
    public @ResponseBody ResponseEntity<String> create(@Valid @RequestBody PostFormWrapper postFormWrapper, BindingResult bindingResult,
                          HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Model model) {
        logger.info("Creating a post");
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Cannot update");
        }
        model.asMap().clear();
        Post post = postFormWrapper.getPost();
        Location location = locationRepository.findById(post.getLocation().getId()).get();
        SEOObject seoObject = post.getSeoObject();
        seoObject.setUrl(UrlUtil.handleTitle(post.getTitle()) + "-" + postTotal++);
        Category category = categoryRepository.findById(postFormWrapper.getCategory()).get();
        post.setLocation(location);
        System.err.println("Location ID : " + location.getId());
        post.setCategory(category);
        postService.save(post);
        return ResponseEntity.ok(httpServletRequest.getRequestURL().toString()  + "/" + seoObject.getUrl());
    }

    @GetMapping("/{url}")
    public String view(@PathVariable String url, Model model) throws Exception {
        logger.info("Getting a posts");
        Optional<Post> postOptional = postService.findBySEOObjectUrl(url);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            model.addAttribute("post", post);
            model.addAttribute("tag", post.getTag());
            model.addAttribute("seo", post.getSeoObject());
            return "posts/view";
        }
        throw new PostNotFoundException(url);
    }


    @GetMapping(value = "/{id}", params = "form")
    public String updateForm(@PathVariable("id") Long id, Model model) throws Exception {
        Optional<Post> postOptional = postService.findById(id);
        Post post = postOptional.get();
        model.addAttribute("post", post);
        model.addAttribute("tag", post.getTag());
        model.addAttribute("seo", post.getSeoObject());
        return "posts/update";
    }

    @PostMapping(value = "/{id}", params = "form")
    @CrossOrigin
    @PreAuthorize("hasRole('ADMIN')")
    public @ResponseBody ResponseEntity<String> update(@Valid @RequestBody PostFormWrapper postFormWrapper, BindingResult bindingResult,
                                                       HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Model model) {
        logger.info("Update a post");
        System.err.println("Update");
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Cannot update");
        }
        model.asMap().clear();
        Post post = postFormWrapper.getPost();
        Location location = locationRepository.findById(post.getLocation().getId()).get();
        SEOObject seoObject = post.getSeoObject();
        seoObject.setUrl(UrlUtil.handleTitle(post.getTitle()) + "-" + postTotal++);
        Category category = categoryRepository.findById(postFormWrapper.getCategory()).get();
        post.setCategory(category);
        post.setLocation(location);
        postService.save(post);
        return ResponseEntity.ok(httpServletRequest.getRequestURL().toString().replace(post.getId().toString(),"")
                 + seoObject.getUrl());
    }


    @GetMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@Param("id") Long id) {
        postService.deleteById(id);
        return "redirect:/users/admin";
    }
}
