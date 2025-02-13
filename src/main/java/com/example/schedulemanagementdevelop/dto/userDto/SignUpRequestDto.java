package com.example.schedulemanagementdevelop.dto.userDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
public class SignUpRequestDto {
    private final String name;
    private final String email;
    private final String password;

    public SignUpRequestDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}