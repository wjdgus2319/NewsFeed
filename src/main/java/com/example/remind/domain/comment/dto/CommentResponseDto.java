package com.example.remind.domain.comment.dto;

import com.example.remind.domain.comment.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {
    private Long id;
    private Long postId;
    private Long authorId;
    private String content;
    private String createdAt;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.postId = comment.getPost().getId();
        this.authorId = comment.getAuthor().getId();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt().toString();
    }
}



