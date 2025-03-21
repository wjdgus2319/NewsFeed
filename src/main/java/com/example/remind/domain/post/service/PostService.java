package com.example.remind.domain.post.service;

import com.example.remind.domain.follow.repository.FollowRepository;
import com.example.remind.domain.post.dto.PostRequestDto;
import com.example.remind.domain.post.entity.Post;
import com.example.remind.domain.post.repository.PostRepository;
import com.example.remind.domain.user.entity.User;
import com.example.remind.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    @Transactional
    public Post createPost(Long userId, PostRequestDto requestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        Post post = new Post(user, requestDto.getTitle(), requestDto.getContent(), requestDto.getBody());
        return postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public Post getPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시물을 찾을 수 없습니다."));
    }

    @Transactional
    public Post updatePost(Long userId, Long postId, PostRequestDto requestDto) {
        Post post = getPost(postId);
        if (!post.getAuthor().getId().equals(userId)) {
            throw new IllegalArgumentException("게시물을 수정할 권한이 없습니다.");
        }
        if (requestDto.getTitle() != null) post.setTitle(requestDto.getTitle());
        if (requestDto.getContent() != null) post.updateContent(requestDto.getContent());
        if (requestDto.getBody() != null) post.setBody(requestDto.getBody());
        return postRepository.save(post);
    }

    @Transactional
    public String deletePost(Long userId, Long postId) {
        Post post = getPost(postId);
        if (!post.getAuthor().getId().equals(userId)) {
            throw new IllegalArgumentException("게시물을 삭제할 권한이 없습니다.");
        }
        postRepository.delete(post);
        return "게시물이 성공적으로 삭제되었습니다.";
    }

    @Transactional(readOnly = true)
    public Page<Post> getNewsFeed(Long userId, int page, int size) {
        List<Long> followingUserIds = followRepository.findFollowingUserIdsByUserId(userId);
        if (followingUserIds.isEmpty()) {
            return Page.empty();
        }
        return postRepository.findPostsByFollowingUsers(followingUserIds, PageRequest.of(page, size));
    }

    @Transactional(readOnly = true)
    public Page<Post> searchPosts(String sort, String startDate, String endDate, int page, int size) {
        Pageable pageable;

        if ("updatedAt".equals(sort)) {
            pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "updatedAt"));
        } else if ("likes".equals(sort)) {
            pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "likeCount"));
        } else {
            pageable = PageRequest.of(page, size);
        }

        if (startDate != null && endDate != null) {
            LocalDateTime startDateTime = LocalDate.parse(startDate).atStartOfDay();
            LocalDateTime endDateTime = LocalDate.parse(endDate).atTime(23, 59, 59);
            return postRepository.findByCreatedAtBetween(startDateTime, endDateTime, pageable);
        }

        return postRepository.findAll(pageable);
    }
}
















