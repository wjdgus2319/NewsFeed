package com.example.remind.domain.like.repository;

import com.example.remind.domain.like.entity.Like;
import com.example.remind.domain.like.entity.LikeType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByUserIdAndContentIdAndLikeType(Long userId, Long contentId, LikeType likeType);
    int countByContentIdAndLikeType(Long contentId, LikeType likeType);
}

