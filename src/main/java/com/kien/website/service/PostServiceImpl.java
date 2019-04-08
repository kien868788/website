package com.kien.website.service;

import com.kien.website.model.Category;
import com.kien.website.model.post.Post;
import com.kien.website.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service("postService")
@Transactional
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;

    @Override
    public void save(Post post) {
        postRepository.save(post);
    }

    @Override
    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Page<Post> findByCategory(Category category,Pageable pageable) {
        return postRepository.findAllByCategory(category,pageable);
    }


    @Override
    public Optional<Post> findBySEOObjectUrl(String url) {
        return postRepository.findBySeoObject_Url(url);
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

}
