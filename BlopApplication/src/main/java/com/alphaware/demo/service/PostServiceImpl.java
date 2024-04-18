package com.alphaware.demo.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphaware.demo.models.Category;
import com.alphaware.demo.models.Customer;
import com.alphaware.demo.models.Post;
import com.alphaware.demo.repository.CategoryRepository;
import com.alphaware.demo.repository.CustomerRepository;
import com.alphaware.demo.repository.PostRepository;



@Service
public class PostServiceImpl implements PostService{

	@Autowired
    private PostRepository postRepository;
	
	@Autowired
    private CustomerRepository customerRepository;
	
	@Autowired
    private CategoryRepository categoryRepository;
	
	
	@Override
	public List<Post> getAllPosts() {
		return postRepository.findAll();
	}

	@Override
	public Post getPostById(Long id) {
		return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
	}

	@Override
	public Post createPost(Post post, Long customerId, Long categoryId) {
		post.setCreatedAt(LocalDateTime.now());
	    post.setUpdatedAt(LocalDateTime.now());

	    Customer customer = customerRepository.findByCustId(customerId)
	            .orElseThrow(() -> new RuntimeException("Customer not found"));
	    Category category = categoryRepository.findById(categoryId)
	            .orElseThrow(() -> new RuntimeException("Category not found"));

	    post.setCustomer(customer);
	    post.setCategory(category);

	    return postRepository.save(post);
    }

	@Override
	public Post updatePost(Long id, Post updatedPost) {
		Post existingPost = postRepository.findById(id).orElse(null);
        if (existingPost != null) {
            existingPost.setTitle(updatedPost.getTitle());
            existingPost.setContent(updatedPost.getContent());
            existingPost.setUpdatedAt(LocalDateTime.now());
            return postRepository.save(existingPost);
        }
        return null;
	}

	@Override
	public void deletePost(Long id) {
		postRepository.deleteById(id);
	}

	@Override
	public List<Post> getFeedPosts() {
		 LocalDateTime startOfDay = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);
	        LocalDateTime endOfDay = startOfDay.plusDays(1);
	        return postRepository.findByCreatedAtBetween(startOfDay, endOfDay);
	}


}