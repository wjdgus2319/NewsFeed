package com.example.remind.domain.user.controller;

import com.example.remind.domain.user.dto.PasswordChangeDto;
import com.example.remind.domain.user.dto.UserSignupDto;
import com.example.remind.domain.user.dto.UserUpdateDto;
import com.example.remind.domain.user.entity.User;
import com.example.remind.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserSignupDto userSignupDto) {
        userService.signup(userSignupDto);
        return ResponseEntity.ok("회원가입이 성공적으로 완료되었습니다.");
    }

    @GetMapping("/{userId}")
    public User getUserProfile(@PathVariable Long userId) {
        return userService.getUserProfile(userId);
    }

    @PatchMapping("/{userId}/password")
    public void changePassword(@PathVariable Long userId, @RequestBody PasswordChangeDto passwordChangeDto) {
        userService.changePassword(userId, passwordChangeDto);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUserProfile(
            @PathVariable Long userId,
            @RequestBody UserUpdateDto userUpdateDto) {

        User updatedUser = userService.updateUserProfile(userId, userUpdateDto);
        return ResponseEntity.ok(updatedUser);
    }
}






