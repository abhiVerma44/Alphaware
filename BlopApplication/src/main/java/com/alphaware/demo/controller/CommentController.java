package com.alphaware.demo.controller;


import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.alphaware.demo.models.Comment;
import com.alphaware.demo.service.CommentService;


@RestController
@RequestMapping("/api/comments")
public class CommentController {

 @Autowired
 private CommentService commentService;

 @GetMapping("/post/{postId}")
 public ResponseEntity<List<Comment>> getCommentsByPostId(@PathVariable Long postId) {
     List<Comment> comments = commentService.getCommentsByPostId(postId);
     return new ResponseEntity<>(comments, HttpStatus.OK);
 }

 @PostMapping("/post/{postId}/customer/{customerId}")
 public ResponseEntity<Comment> createComment(@PathVariable Long postId, @PathVariable Long customerId, @RequestBody Comment comment) {
     Comment createdComment = commentService.createComment(postId, customerId, comment);
     return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
 }



 @PutMapping("/{id}")
 public ResponseEntity<Comment> updateComment(@PathVariable Long id, @RequestBody Comment comment) {
     Comment updatedComment = commentService.updateComment(id, comment);
     return new ResponseEntity<>(updatedComment, HttpStatus.OK);
 }

 @DeleteMapping("/{id}")
 public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
     commentService.deleteComment(id);
     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
 }
}