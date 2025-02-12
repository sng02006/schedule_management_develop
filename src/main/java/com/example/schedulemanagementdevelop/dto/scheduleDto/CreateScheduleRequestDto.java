package com.example.schedulemanagementdevelop.dto.scheduleDto;

import lombok.Getter;

@Getter
public class CreateScheduleRequestDto {
    private String title;
    private String toDo;
    private Long userId;
    private String password;

    public CreateScheduleRequestDto(String title, String toDo, Long userId, String password) {
        this.title = title;
        this.toDo = toDo;
        this.userId = userId;
        this.password = password;
    }
}