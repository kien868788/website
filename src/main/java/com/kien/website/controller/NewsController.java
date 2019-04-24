package com.kien.website.controller;

import com.kien.website.exception.PostNotFoundException;
import com.kien.website.model.SEOObject;
import com.kien.website.model.post.News;
import com.kien.website.model.post.NewsCategory;
import com.kien.website.repository.LocationRepository;
import com.kien.website.repository.NewsCategoryRepository;
import com.kien.website.repository.NewsRepository;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tin-tuc")
public class NewsController {

    private static long postTotal = 1;

    private Logger logger = LoggerFactory.getLogger(NewsController.class);

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsCategoryRepository newsCategoryRepository;

    @Autowired
    private LocationRepository locationRepository;

    @GetMapping(params = "form")
    @PreAuthorize("hasRole('ADMIN')")
    public String getPostForm(Model model) {
        List<NewsCategory> newsCategories = (List<NewsCategory>) newsCategoryRepository.findAll();
        model.addAttribute("categories",newsCategories);
        return "news/createNews";
    }

    @GetMapping(value = "/{id}", params = "form")
    public String updateForm(@PathVariable("id") Long id, Model model) throws Exception {
        Optional<News> newsOptional = newsRepository.findById(id);
        if (!newsOptional.isPresent()) {
            throw new PostNotFoundException();
        }
        News news = newsOptional.get();
        model.addAttribute("news", news);
        model.addAttribute("seo", news.getSeoObject());
        List<NewsCategory> newsCategories = (List<NewsCategory>) newsCategoryRepository.findAll();
        model.addAttribute("categories",newsCategories);
        return "news/updateNews";
    }


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public @ResponseBody
    ResponseEntity<String> create(@Valid @RequestBody News news, BindingResult bindingResult,
                                  HttpServletRequest httpServletRequest,  Model model) throws Exception{
        logger.info("Creating a news");
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Cannot update");
        }
        Long newsCategoryId = news.getCategory().getId();
        Optional<NewsCategory> newsCategoryOptional = newsCategoryRepository.findById(newsCategoryId);
        if (!newsCategoryOptional.isPresent()) {
            throw new PostNotFoundException();
        }
        NewsCategory newsCategory = newsCategoryOptional.get();
        newsCategory.add(news);
        model.asMap().clear();
        SEOObject seoObject = news.getSeoObject();
        seoObject.setUrl(UrlUtil.handleTitle(news.getTitle()) + "-" + postTotal++);
        newsRepository.save(news);
        newsCategoryRepository.save(newsCategory);
        return ResponseEntity.ok(httpServletRequest.getRequestURL().toString()  + "/" + seoObject.getUrl());
    }

    @GetMapping("/{url}")
    public String view(@PathVariable String url, Model model) throws Exception {
        logger.info("Getting a news");
        Optional<News> postOptional = newsRepository.findBySeoObject_Url(url);
        if (postOptional.isPresent()) {
            News news = postOptional.get();
            model.addAttribute("news", news);
            model.addAttribute("seo", news.getSeoObject());
            return "news/viewNews";
        }
        throw new PostNotFoundException();
    }

    @PostMapping(value = "/{id}", params = "form")
    @PreAuthorize("hasRole('ADMIN')")
    public @ResponseBody ResponseEntity<String> update(@Valid @RequestBody News news, BindingResult bindingResult,
                                                       HttpServletRequest httpServletRequest, Model model) throws Exception {
        logger.info("Update a News");
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Cannot update");
        }
        Long newsCategoryId = news.getCategory().getId();
        Optional<NewsCategory> newsCategoryOptional = newsCategoryRepository.findById(newsCategoryId);
        if (!newsCategoryOptional.isPresent()) {
            throw new PostNotFoundException();
        }
        NewsCategory newsCategory = newsCategoryOptional.get();
        news.setCategory(newsCategory);
        model.asMap().clear();
        SEOObject seoObject = news.getSeoObject();
        seoObject.setUrl(UrlUtil.handleTitle(news.getTitle()) + "-" + postTotal++);
        newsRepository.save(news);
        return ResponseEntity.ok(httpServletRequest.getRequestURL().toString().replace(news.getId().toString(),"")
                + seoObject.getUrl());
    }

    @GetMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@Param("id") Long id) {
        newsRepository.deleteById(id);
        return "redirect:/users/admin";
    }
}
