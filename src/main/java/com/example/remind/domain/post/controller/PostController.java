package com.example.remind.domain.post.controller;

import com.example.remind.domain.post.dto.PostRequestDto;
import com.example.remind.domain.post.entity.Post;
import com.example.remind.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/{userId}")
    public Post createPost(@PathVariable Long userId, @RequestBody PostRequestDto requestDto) {
        return postService.createPost(userId, requestDto);
    }

    @GetMapping("/{postId}")
    public Post getPost(@PathVariable Long postId) {
        return postService.getPost(postId);
    }

    @PutMapping("/{userId}/{postId}")
    public Post updatePost(@PathVariable Long userId, @PathVariable Long postId, @RequestBody PostRequestDto requestDto) {
        return postService.updatePost(userId, postId, requestDto);
    }

    @DeleteMapping("/{userId}/{postId}")
    public String deletePost(@PathVariable Long userId, @PathVariable Long postId) {
        return postService.deletePost(userId, postId);
    }

    @GetMapping("/{userId}/feed")
    public Page<Post> getFeed(@PathVariable Long userId,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size) {
        return postService.getNewsFeed(userId, page, size);
    }
}












