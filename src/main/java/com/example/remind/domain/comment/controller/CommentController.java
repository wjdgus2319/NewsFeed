package com.example.remind.domain.comment.controller;

import com.example.remind.domain.comment.dto.CommentRequestDto;
import com.example.remind.domain.comment.dto.CommentResponseDto;
import com.example.remind.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{userId}/{postId}")
    public CommentResponseDto createComment(@PathVariable Long userId, @PathVariable Long postId, @RequestBody CommentRequestDto requestDto) {
        return commentService.createComment(userId, postId, requestDto);
    }

    @GetMapping("/{postId}")
    public List<CommentResponseDto> getComments(@PathVariable Long postId) {
        return commentService.getComments(postId);
    }

    @PutMapping("/{userId}/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long userId, @PathVariable Long commentId, @RequestBody CommentRequestDto requestDto) {
        return commentService.updateComment(userId, commentId, requestDto);
    }

    @DeleteMapping("/{userId}/{commentId}")
    public String deleteComment(@PathVariable Long userId, @PathVariable Long commentId) {
        commentService.deleteComment(userId, commentId);
        return "댓글이 성공적으로 삭제되었습니다.";
    }
}



