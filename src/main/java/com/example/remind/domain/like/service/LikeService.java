package com.example.remind.domain.like.service;

import com.example.remind.domain.like.entity.Like;
import com.example.remind.domain.like.entity.LikeType;
import com.example.remind.domain.like.repository.LikeRepository;
import com.example.remind.domain.user.entity.User;
import com.example.remind.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;

    @Transactional
    public ResponseEntity<String> toggleLike(Long userId, Long contentId, LikeType likeType) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        Optional<Like> existingLike = likeRepository.findByUserIdAndContentIdAndLikeType(userId, contentId, likeType);

        if (existingLike.isPresent()) {
            likeRepository.delete(existingLike.get());
            return ResponseEntity.ok("좋아요 취소됨");
        } else {
            Like like = new Like(user, contentId, likeType);
            likeRepository.save(like);
            return ResponseEntity.ok("좋아요 추가됨");
        }
    }

    @Transactional(readOnly = true)
    public int getLikeCount(Long contentId, LikeType likeType) {
        return likeRepository.countByContentIdAndLikeType(contentId, likeType);
    }
}

