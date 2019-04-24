package com.kien.website.controller;

import com.kien.website.model.post.News;
import com.kien.website.model.post.SellPost;
import com.kien.website.repository.NewsRepository;
import com.kien.website.repository.SellPostRepository;
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
    SellPostRepository sellPostRepository;


    @Autowired
    NewsRepository newsRepository;

    @GetMapping("/admin")
    public String adminLogin(Model model) {
        Pageable pageable = PageRequest.of(0,50,Sort.by("lastModified"));
        Page<SellPost> sellPostsPage = sellPostRepository.findAll(pageable);
        Page<News> newsPage = newsRepository.findAll(pageable);
        if (sellPostsPage.hasContent()) {
            List<SellPost> sellPosts = sellPostsPage.getContent();
            model.addAttribute("sellPosts", sellPosts);
        }
        if (newsPage.hasContent()) {
           List<News> newses = newsPage.getContent();
           model.addAttribute("newses",newses);
        }
        return "users/admin";
    }

}
