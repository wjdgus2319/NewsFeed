package com.example.remind.domain.user.dto;

import lombok.Getter;

@Getter
public class PasswordChangeDto {
    private String currentPassword;
    private String newPassword;
}


