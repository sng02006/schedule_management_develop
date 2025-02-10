package com.example.schedulemanagementdevelop.dto;

import lombok.Getter;

@Getter
public class CreateScheduleRequestDto {
    private String title;
    private String toDo;
    private String username;

    public CreateScheduleRequestDto(String title, String toDo, String username) {
        this.title = title;
        this.toDo = toDo;
        this.username = username;
    }
}