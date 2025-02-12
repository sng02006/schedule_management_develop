package com.example.schedulemanagementdevelop.dto.scheduleDto;

import lombok.Getter;

@Getter
public class UpdateScheduleRequestDto {
    private String title;
    private String toDo;
    private String password;

    public UpdateScheduleRequestDto(String title, String toDo, String password) {
        this.title = title;
        this.toDo = toDo;
        this.password = password;
    }
}