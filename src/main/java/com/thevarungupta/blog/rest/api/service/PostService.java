package com.thevarungupta.blog.rest.api.service;

import com.thevarungupta.blog.rest.api.entity.Post;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts();
    Post getPostById(Long id);
    Post savePost(Post post);
    Post updatePost(Long id, Post post);
    void deletePost(Long id);
}
