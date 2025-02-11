package com.example.schedulemanagementdevelop.dto.scheduleDto;

import com.example.schedulemanagementdevelop.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String title;
    private String toDo;

    public ScheduleResponseDto(Long id, String title, String toDo) {
        this.id = id;
        this.title = title;
        this.toDo = toDo;
    }

    public static ScheduleResponseDto toDto(Schedule schedule) {
        return new ScheduleResponseDto(schedule.getId(), schedule.getTitle(), schedule.getToDo());
    }
}