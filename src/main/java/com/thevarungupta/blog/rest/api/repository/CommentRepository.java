package com.thevarungupta.blog.rest.api.repository;

import com.thevarungupta.blog.rest.api.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);
}
