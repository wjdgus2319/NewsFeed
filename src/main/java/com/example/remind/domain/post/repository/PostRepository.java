package com.example.remind.domain.post.repository;

import com.example.remind.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.author.id IN :followingUserIds")
    Page<Post> findPostsByFollowingUsers(@Param("followingUserIds") List<Long> followingUserIds, Pageable pageable);

    @Query("SELECT p FROM Post p WHERE p.createdAt BETWEEN :startDateTime AND :endDateTime")
    Page<Post> findByCreatedAtBetween(
            @Param("startDateTime") LocalDateTime startDateTime,
            @Param("endDateTime") LocalDateTime endDateTime,
            Pageable pageable
    );
}














