package com.example.schedulemanagementdevelop.controller;

import com.example.schedulemanagementdevelop.dto.scheduleDto.CreateScheduleRequestDto;
import com.example.schedulemanagementdevelop.dto.scheduleDto.ScheduleResponseDto;
import com.example.schedulemanagementdevelop.dto.scheduleDto.UpdateScheduleRequestDto;
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
        ScheduleResponseDto scheduleResponseDto = scheduleService.save(requestDto);
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
                @RequestBody UpdateScheduleRequestDto requestDto
    ) {
        scheduleService.updateSchedule(id, requestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/title/{id}")
    public ResponseEntity<Void> updateTitle(
        @PathVariable Long id,
        @RequestBody UpdateScheduleRequestDto requestDto
    ){
        scheduleService.updateTitle(id, requestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/toDo/{id}")
    public ResponseEntity<Void> updateToDo(
            @PathVariable Long id,
            @RequestBody UpdateScheduleRequestDto requestDto
    ){
        scheduleService.updateToDo(id, requestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        scheduleService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}