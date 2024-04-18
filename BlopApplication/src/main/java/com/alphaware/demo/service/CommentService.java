package com.alphaware.demo.service;

import java.util.List;

import com.alphaware.demo.models.Comment;

public interface CommentService {

	public List<Comment> getCommentsByPostId(Long postId);
	public Comment createComment(Long postId, Long customerId, Comment comment);
	public Comment updateComment(Long id, Comment comment);
	public void deleteComment(Long id);
	

}
