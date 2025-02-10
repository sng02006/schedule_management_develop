package com.example.schedulemanagementdevelop.service;

import com.example.schedulemanagementdevelop.dto.ScheduleResponseDto;
import com.example.schedulemanagementdevelop.entity.Schedule;
import com.example.schedulemanagementdevelop.repository.ScheduleRepository;
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

    public ScheduleResponseDto save(String title, String toDo, String username) {
        Schedule schedule = new Schedule(title, toDo, username);
        Schedule saveSchedule = scheduleRepository.save(schedule);
        return new ScheduleResponseDto(saveSchedule.getId(), saveSchedule.getTitle(), saveSchedule.getToDo(), saveSchedule.getUsername());
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
    public void updateSchedule(Long id, String title, String toDo, String username) {
        if (title == null || toDo == null || username == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title and contents are required values.");
        }

        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        findSchedule.updateSchedule(title, toDo, username);
    }

    @Transactional
    public void updateTitle(long id, String title, String toDo, String username) {
        if (title == null || toDo != null || username != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title and contents are required values.");
        }

        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        findSchedule.updateTitle(title);
    }

    @Transactional
    public void updateToDo(long id, String title, String toDo, String username) {
        if (title != null || toDo == null || username != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title and contents are required values.");
        }

        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        findSchedule.updateToDo(toDo);
    }

    public void delete(Long id) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        scheduleRepository.delete(findSchedule);
    }
}