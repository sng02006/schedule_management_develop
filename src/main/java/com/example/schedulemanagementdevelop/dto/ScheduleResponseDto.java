package com.example.schedulemanagementdevelop.dto;

import com.example.schedulemanagementdevelop.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String title;
    private String toDo;
    private String username;

    public ScheduleResponseDto(Long id, String title, String toDo, String username) {
        this.id = id;
        this.title = title;
        this.toDo = toDo;
        this.username = username;
    }

    public static ScheduleResponseDto toDto(Schedule schedule) {
        return new ScheduleResponseDto(schedule.getId(), schedule.getTitle(), schedule.getToDo(), schedule.getUsername());
    }
}