package com.example.remind.domain.user.service;

import com.example.remind.domain.user.dto.PasswordChangeDto;
import com.example.remind.domain.user.dto.UserSignupDto;
import com.example.remind.domain.user.dto.UserUpdateDto;
import com.example.remind.domain.user.entity.User;
import com.example.remind.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void signup(UserSignupDto userSignupDto) {
        if (userRepository.existsByEmail(userSignupDto.getEmail())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }

        User user = new User(userSignupDto.getName(), userSignupDto.getEmail(), userSignupDto.getPassword());
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User getUserProfile(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
    }

    @Transactional
    public void changePassword(Long userId, PasswordChangeDto passwordChangeDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        if (!user.getPassword().equals(passwordChangeDto.getCurrentPassword())) {
            throw new IllegalArgumentException("현재 비밀번호가 올바르지 않습니다.");
        }

        if (user.getPassword().equals(passwordChangeDto.getNewPassword())) {
            throw new IllegalArgumentException("새로운 비밀번호는 현재 비밀번호와 동일할 수 없습니다.");
        }

        if (!isValidPassword(passwordChangeDto.getNewPassword())) {
            throw new IllegalArgumentException("비밀번호 형식이 올바르지 않습니다. (최소 8자, 대소문자 및 숫자 포함)");
        }

        user.changePassword(passwordChangeDto.getNewPassword());
        userRepository.save(user);
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 8 &&
                password.matches(".*[A-Z].*") && // 대문자 포함
                password.matches(".*[a-z].*") && // 소문자 포함
                password.matches(".*\\d.*");     // 숫자 포함
    }

    @Transactional
    public User updateUserProfile(Long userId, UserUpdateDto userUpdateDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        if (userUpdateDto.getName() != null) {
            user.setName(userUpdateDto.getName());
        }

        if (userUpdateDto.getEmail() != null) {
            user.setEmail(userUpdateDto.getEmail());
        }

        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);

        return user;
    }
}





