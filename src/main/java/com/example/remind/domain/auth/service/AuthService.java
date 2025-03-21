package com.example.remind.domain.auth.service;

import com.example.remind.config.JwtTokenProvider;
import com.example.remind.config.PasswordEncoder;
import com.example.remind.domain.auth.dto.LoginRequestDto;
import com.example.remind.domain.auth.dto.SignUpRequestDto;
import com.example.remind.domain.user.entity.User;
import com.example.remind.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public void signUp(SignUpRequestDto requestDto) {
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());
        User user = new User(requestDto.getName(), requestDto.getEmail(), encodedPassword);

        userRepository.save(user);
    }

    @Transactional
    public String login(LoginRequestDto requestDto) {
        User user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("이메일이 존재하지 않습니다."));

        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 올바르지 않습니다.");
        }

        return jwtTokenProvider.generateToken(user.getEmail());
    }
}

