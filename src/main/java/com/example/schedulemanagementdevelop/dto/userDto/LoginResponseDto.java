package com.example.schedulemanagementdevelop.dto.userDto;

import lombok.Getter;

@Getter
public class LoginResponseDto {
    // 유저 식별자
    private final Long id;
    // 유저 이름
    private final String email;

    public LoginResponseDto(Long id, String email) {
        this.id = id;
        this.email = email;
    }
}