package com.example.schedulemanagementdevelop.service;

import com.example.schedulemanagementdevelop.dto.scheduleDto.ScheduleResponseDto;
import com.example.schedulemanagementdevelop.dto.userDto.SignUpResponseDto;
import com.example.schedulemanagementdevelop.dto.userDto.UserResponseDto;
import com.example.schedulemanagementdevelop.entity.Schedule;
import com.example.schedulemanagementdevelop.entity.User;
import com.example.schedulemanagementdevelop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public SignUpResponseDto signup(String name, String email, String password) {
        User user = new User(name, email, password);
        User savedUser = userRepository.save(user);
        return new SignUpResponseDto(savedUser.getId(), savedUser.getName(), savedUser.getEmail());
    }

    public List<UserResponseDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDto::toDto)
                .toList();
    }

    public UserResponseDto findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exists id : " + id);
        }

        User findUser = optionalUser.get();
        return new UserResponseDto(id, findUser.getName(), findUser.getEmail());
    }

    @Transactional
    public void updateUser(Long id, String name, String email) {
        if (name == null || email == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title and contents are required values.");
        }

        User findUser = userRepository.findByIdOrElseThrow(id);
        findUser.updateUser(name, email);
    }

    @Transactional
    public void updateName(Long id, String name, String email) {
        if (name == null || email != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title and contents are required values.");
        }

        User findUser = userRepository.findByIdOrElseThrow(id);
        findUser.updateName(name);
    }

    @Transactional
    public void updateEmail(Long id, String name, String email) {
        if (name != null || email == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title and contents are required values.");
        }

        User findUser = userRepository.findByIdOrElseThrow(id);
        findUser.updateEmail(email);
    }

    @Transactional
    public void updatePassword(Long id, String oldPassword, String newPassword) {
        User findUser = userRepository.findByIdOrElseThrow(id);
        if (!findUser.getPassword().equals(oldPassword)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "The password does not match.");
        }
        findUser.updatePassword(newPassword);
    }

    @Transactional
    public void delete(Long id) {
        User findUser = userRepository.findByIdOrElseThrow(id);
        userRepository.delete(findUser);
    }
}