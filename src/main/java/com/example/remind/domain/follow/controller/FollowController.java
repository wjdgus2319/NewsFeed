package com.example.remind.domain.follow.controller;

import com.example.remind.domain.follow.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/follows")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @PostMapping("/{followerId}/follow/{followeeId}")
    public ResponseEntity<String> followUser(@PathVariable Long followerId,
                                             @PathVariable Long followeeId) {
        followService.followUser(followerId, followeeId);
        return ResponseEntity.ok("팔로우가 성공적으로 완료되었습니다.");
    }

    @DeleteMapping("/{followerId}/unfollow/{followeeId}")
    public ResponseEntity<String> unfollowUser(@PathVariable Long followerId,
                                               @PathVariable Long followeeId) {
        followService.unfollowUser(followerId, followeeId);
        return ResponseEntity.ok("언팔로우가 성공적으로 완료되었습니다.");
    }
}








