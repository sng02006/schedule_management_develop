package com.example.schedulemanagementdevelop.dto;

import lombok.Getter;

@Getter
public class UpdateScheduleRequestDto {
    private String title;
    private String toDo;
    private String username;

    public UpdateScheduleRequestDto(String title, String toDo, String username) {
        this.title = title;
        this.toDo = toDo;
        this.username = username;
    }
}