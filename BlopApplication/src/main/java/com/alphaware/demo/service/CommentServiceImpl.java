package com.alphaware.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphaware.demo.models.Comment;
import com.alphaware.demo.models.Customer;
import com.alphaware.demo.models.Post;
import com.alphaware.demo.repository.CommentRepository;
import com.alphaware.demo.repository.CustomerRepository;
import com.alphaware.demo.repository.PostRepository;



@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private CustomerRepository customerRepository;
	
	@Override
	public List<Comment> getCommentsByPostId(Long postId) {
		return commentRepository.findByPostId(postId);
	}

	@Override
	public Comment createComment(Long postId, Long customerId, Comment comment) {
	    Post post = postRepository.findById(postId)
	            .orElseThrow(() -> new RuntimeException("Post not found"));
	    Customer customer = customerRepository.findByCustId(customerId)
	            .orElseThrow(() -> new RuntimeException("Customer not found"));
	    comment.setPost(post);
	    comment.setCustomer(customer); // Set the customer for the comment
	    return commentRepository.save(comment);
	}



	@Override
	public Comment updateComment(Long id, Comment comment) {
		Comment existingComment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        existingComment.setContent(comment.getContent());
        return commentRepository.save(existingComment);
	}

	@Override
	public void deleteComment(Long id) {
		commentRepository.deleteById(id);
	}

}