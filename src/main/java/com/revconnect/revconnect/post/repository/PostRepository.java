package com.revconnect.revconnect.post.repository;

import com.revconnect.revconnect.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByUserIdOrderByCreatedAtDesc(Long userId);

    List<Post> findByStatusOrderByCreatedAtDesc(String status);
}