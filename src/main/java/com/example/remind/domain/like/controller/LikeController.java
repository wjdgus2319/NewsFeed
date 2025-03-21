package com.example.remind.domain.like.controller;

import com.example.remind.domain.like.entity.LikeType;
import com.example.remind.domain.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/{contentId}")
    public ResponseEntity<String> toggleLike(@PathVariable Long contentId, @RequestParam LikeType likeType, @RequestParam Long userId) {
        return likeService.toggleLike(userId, contentId, likeType);
    }

    @GetMapping("/{contentId}/count")
    public int getLikeCount(@PathVariable Long contentId, @RequestParam LikeType likeType) {
        return likeService.getLikeCount(contentId, likeType);
    }
}

