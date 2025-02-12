package com.example.schedulemanagementdevelop.service;

import com.example.schedulemanagementdevelop.dto.scheduleDto.CreateScheduleRequestDto;
import com.example.schedulemanagementdevelop.dto.scheduleDto.ScheduleResponseDto;
import com.example.schedulemanagementdevelop.dto.scheduleDto.UpdateScheduleRequestDto;
import com.example.schedulemanagementdevelop.entity.Schedule;
import com.example.schedulemanagementdevelop.entity.User;
import com.example.schedulemanagementdevelop.repository.ScheduleRepository;
import com.example.schedulemanagementdevelop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional
    public ScheduleResponseDto save(CreateScheduleRequestDto requestDto) {
        User findUser = userRepository.findUserByUserIdOrElseThrow(requestDto.getUserId());
        Schedule schedule = new Schedule(requestDto.getTitle(), requestDto.getToDo());
        schedule.setUser(findUser);
        Schedule savedSchedule = scheduleRepository.save(schedule);
        verifyPassword(savedSchedule.getId(), requestDto.getPassword());
        return new ScheduleResponseDto(savedSchedule.getId(), savedSchedule.getTitle(), savedSchedule.getToDo());
    }

    public List<ScheduleResponseDto> findAll() {
        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleResponseDto::toDto)
                .toList();
    }

    public ScheduleResponseDto findById(Long id) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        return ScheduleResponseDto.toDto(findSchedule);
    }

    @Transactional
    public void updateSchedule(Long id, UpdateScheduleRequestDto requestDto) {
        verifyPassword(id, requestDto.getPassword());

        if (requestDto.getTitle() == null || requestDto.getToDo() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title and contents are required values.");
        }

        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        findSchedule.updateSchedule(requestDto.getTitle(), requestDto.getToDo());
    }

    @Transactional
    public void updateTitle(Long id, UpdateScheduleRequestDto requestDto) {
        verifyPassword(id, requestDto.getPassword());

        if (requestDto.getTitle() == null || requestDto.getToDo() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title and contents are required values.");
        }

        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        findSchedule.updateTitle(requestDto.getTitle());
    }

    @Transactional
    public void updateToDo(Long id, UpdateScheduleRequestDto requestDto) {
        verifyPassword(id, requestDto.getPassword());

        if (requestDto.getTitle() != null || requestDto.getToDo() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title and contents are required values.");
        }

        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        findSchedule.updateToDo(requestDto.getToDo());
    }

    @Transactional
    public void delete(Long id) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        scheduleRepository.delete(findSchedule);
    }

    private void verifyPassword(Long id, String password) {
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(id);
        User findUser = userRepository.findUserByUserIdOrElseThrow(schedule.getUser().getId());
        if (!findUser.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong password");
        }
    }
}