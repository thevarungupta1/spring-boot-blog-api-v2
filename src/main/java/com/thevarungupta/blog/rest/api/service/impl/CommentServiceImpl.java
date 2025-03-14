package com.thevarungupta.blog.rest.api.service.impl;

import com.thevarungupta.blog.rest.api.entity.Comment;
import com.thevarungupta.blog.rest.api.entity.Post;
import com.thevarungupta.blog.rest.api.exception.BlogApiException;
import com.thevarungupta.blog.rest.api.exception.ResourceNotFoundException;
import com.thevarungupta.blog.rest.api.repository.CommentRepository;
import com.thevarungupta.blog.rest.api.repository.PostRepository;
import com.thevarungupta.blog.rest.api.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment createComment(Long postId, Comment newComment) {
        // retrieve post by id
        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));
        // set post to comment
        newComment.setPost(post);
        // save comment
        return commentRepository.save(newComment);
    }

    @Override
    public List<Comment> getCommentByPostId(Long postId) {
       // retrieve comment by post id
        return commentRepository.findByPostId(postId);
    }

    @Override
    public Comment getCommentById(Long postId, Long commentId) {
        // retrieve post by id
        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));
        // retrieve comments by comment id
        Comment comment = commentRepository
                .findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("comment", "commentId", commentId));
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "comment does not belong to post");
        }
        return comment;
    }

    @Override
    public Comment updateComment(Long postId, Long commentId, Comment updateComment) {
        // retrieve post by id
        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));
        // retrieve comments by comment id
        Comment comment = commentRepository
                .findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("comment", "commentId", commentId));
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "comment does not belong to post");
        }
        comment.setName(updateComment.getName());
        comment.setEmail(updateComment.getEmail());
        comment.setBody(updateComment.getBody());
        return commentRepository.save(comment);

    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        // retrieve post by id
        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));
        // retrieve comments by comment id
        Comment comment = commentRepository
                .findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("comment", "commentId", commentId));
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "comment does not belong to post");
        }
        commentRepository.deleteById(commentId);
    }
}
