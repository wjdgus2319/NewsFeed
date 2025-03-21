package com.example.remind.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSignupDto {
    private String name;
    private String email;
    private String password;
}

