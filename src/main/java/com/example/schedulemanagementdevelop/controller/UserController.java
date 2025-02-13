package com.example.schedulemanagementdevelop.controller;

import com.example.schedulemanagementdevelop.dto.userDto.*;
import com.example.schedulemanagementdevelop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signup(@RequestBody SignUpRequestDto requestDto) {
        SignUpResponseDto signUpResponseDto = userService.signup(requestDto);
        return new ResponseEntity<>(signUpResponseDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto requestDto, HttpServletRequest request) {
        LoginResponseDto responseDto = userService.login(requestDto);
        Long userId = responseDto.getId();

        // 로그인 실패
        if (userId == null) {
            return new ResponseEntity<>(responseDto, HttpStatus.UNAUTHORIZED);
        }

        // 로그인 성공
        HttpSession session = request.getSession();
        // 회원 정보 조회
        UserResponseDto loginUser = userService.findById(userId);
        // Session에 로그인 회원 정보 저장
        session.setAttribute("sessionKey", loginUser);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<LoginResponseDto> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll() {
        List<UserResponseDto> userResponseDtoList = userService.findAll();
        return new ResponseEntity<>(userResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {
        UserResponseDto userResponseDto = userService.findById(id);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable Long id,
            @RequestBody UserRequestDto requestDto
    ){
        userService.updateUser(id, requestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/name/{id}")
    public ResponseEntity<UserResponseDto> updateName(
            @PathVariable Long id,
            @RequestBody UserRequestDto requestDto
    ) {
        userService.updateName(id, requestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/email/{id}")
    public ResponseEntity<UserResponseDto> updateEmail(
            @PathVariable Long id,
            @RequestBody UserRequestDto requestDto
    ) {
        userService.updateEmail(id, requestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/password/{id}")
    public ResponseEntity<Void> updatePassword(
            @PathVariable Long id,
            @RequestBody UpdatePasswordRequestDto requestDto
    ) {
        userService.updatePassword(id, requestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}