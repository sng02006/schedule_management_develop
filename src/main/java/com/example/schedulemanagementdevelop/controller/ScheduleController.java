package com.example.schedulemanagementdevelop.controller;

import com.example.schedulemanagementdevelop.dto.CreateScheduleRequestDto;
import com.example.schedulemanagementdevelop.dto.ScheduleResponseDto;
import com.example.schedulemanagementdevelop.dto.UpdateScheduleRequestDto;
import com.example.schedulemanagementdevelop.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> save(@RequestBody CreateScheduleRequestDto requestDto) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.save(requestDto.getTitle(), requestDto.getToDo(), requestDto.getUsername());
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAll() {
        List<ScheduleResponseDto> scheduleResponseDtoList = scheduleService.findAll();
        return new ResponseEntity<>(scheduleResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable Long id) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.findById(id);
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
        public ResponseEntity<Void> updateSchedule(
                @PathVariable Long id,
                @RequestBody UpdateScheduleRequestDto dto
    ) {
        scheduleService.updateSchedule(id, dto.getTitle(), dto.getToDo(), dto.getUsername());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/title/{id}")
    public ResponseEntity<Void> updateTitle(
        @PathVariable long id,
        @RequestBody UpdateScheduleRequestDto dto
    ){
        scheduleService.updateTitle(id, dto.getTitle(), dto.getToDo(), dto.getUsername());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/toDo/{id}")
    public ResponseEntity<Void> updateToDo(
            @PathVariable long id,
            @RequestBody UpdateScheduleRequestDto dto
    ){
        scheduleService.updateToDo(id, dto.getTitle(), dto.getToDo(), dto.getUsername());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        scheduleService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}