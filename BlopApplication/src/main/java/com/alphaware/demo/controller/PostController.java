package com.alphaware.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alphaware.demo.models.Post;
import com.alphaware.demo.service.PostService;


@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", posts);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getPostById(@PathVariable Long id) {
        Post post = postService.getPostById(id);
        Map<String, Object> response = new HashMap<>();
        if (post != null) {
            response.put("status", "success");
            response.put("data", post);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("status", "error");
            response.put("message", "Post not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/feed")
    public ResponseEntity<Map<String, Object>> getFeedPosts() {
        List<Post> feedPosts = postService.getFeedPosts();
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", feedPosts);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Map<String, Object>> createPost(@RequestBody Post post, @RequestParam Long customerId, @RequestParam Long categoryId) {
        Post createdPost = postService.createPost(post,customerId, categoryId);
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", createdPost);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updatePost(@PathVariable Long id, @RequestBody Post updatedPost) {
        Post updatedPostResponse = postService.updatePost(id, updatedPost);
        Map<String, Object> response = new HashMap<>();
        if (updatedPostResponse != null) {
            response.put("status", "success");
            response.put("data", updatedPostResponse);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("status", "error");
            response.put("message", "Post not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}