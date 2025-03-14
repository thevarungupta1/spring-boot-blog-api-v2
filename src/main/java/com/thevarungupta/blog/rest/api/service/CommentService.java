package com.thevarungupta.blog.rest.api.service;

import com.thevarungupta.blog.rest.api.entity.Comment;

import java.util.List;

public interface CommentService {
    Comment createComment(Long postId, Comment newComment);
    List<Comment> getCommentByPostId(Long postId);
    Comment getCommentById(Long postId, Long commentId);
    Comment updateComment(Long postId, Long commentId, Comment updateComment);
    void deleteComment(Long postId, Long commentId);
}
