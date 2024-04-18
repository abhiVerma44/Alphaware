package com.alphaware.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alphaware.demo.models.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
}