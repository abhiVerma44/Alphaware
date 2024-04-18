package com.alphaware.demo.service;

import java.util.List;

import com.alphaware.demo.models.Post;

public interface PostService {

	public List<Post> getAllPosts();
	public Post getPostById(Long id);
	public Post createPost(Post post, Long customerId, Long categoryId);
	public Post updatePost(Long id, Post post);
	public void deletePost(Long id);
	public List<Post> getFeedPosts();
}
