package com.example.schedulemanagementdevelop.controller;

import com.example.schedulemanagementdevelop.dto.scheduleDto.ScheduleResponseDto;
import com.example.schedulemanagementdevelop.dto.userDto.SignUpRequestDto;
import com.example.schedulemanagementdevelop.dto.userDto.SignUpResponseDto;
import com.example.schedulemanagementdevelop.dto.userDto.UserRequestDto;
import com.example.schedulemanagementdevelop.dto.userDto.UserResponseDto;
import com.example.schedulemanagementdevelop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signup(@RequestBody SignUpRequestDto requestDto) {
        SignUpResponseDto signUpResponseDto = userService.signup(requestDto.getName(), requestDto.getEmail());
        return new ResponseEntity<>(signUpResponseDto, HttpStatus.CREATED);
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
        userService.updateUser(id, requestDto.getName(), requestDto.getEmail());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/name/{id}")
    public ResponseEntity<UserResponseDto> updateName(
            @PathVariable Long id,
            @RequestBody UserRequestDto requestDto
    ) {
        userService.updateName(id, requestDto.getName(), requestDto.getEmail());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/email/{id}")
    public ResponseEntity<UserResponseDto> updateEmail(
            @PathVariable Long id,
            @RequestBody UserRequestDto requestDto
    ) {
        userService.updateEmail(id, requestDto.getName(), requestDto.getEmail());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}