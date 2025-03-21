package com.example.remind.domain.follow.service;

import com.example.remind.domain.follow.entity.Follow;
import com.example.remind.domain.follow.repository.FollowRepository;
import com.example.remind.domain.user.entity.User;
import com.example.remind.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    @Transactional
    public void followUser(Long followerId, Long followeeId) {
        User follower = userRepository.findById(followerId)
                .orElseThrow(() -> new IllegalArgumentException("팔로워를 찾을 수 없습니다."));

        User followee = userRepository.findById(followeeId)
                .orElseThrow(() -> new IllegalArgumentException("팔로우 대상 사용자를 찾을 수 없습니다."));

        if (followRepository.findByFollowerIdAndFollowingId(followerId, followeeId).isPresent()) {
            throw new IllegalArgumentException("이미 팔로우 중입니다.");
        }

        Follow follow = new Follow(follower, followee);
        followRepository.save(follow);
    }

    @Transactional
    public void unfollowUser(Long followerId, Long followeeId) {
        Follow follow = followRepository.findByFollowerIdAndFollowingId(followerId, followeeId)
                .orElseThrow(() -> new IllegalArgumentException("팔로우 관계가 존재하지 않습니다."));

        followRepository.delete(follow);
    }
}





