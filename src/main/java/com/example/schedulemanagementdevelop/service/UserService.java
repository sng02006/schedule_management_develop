package com.example.schedulemanagementdevelop.service;

import com.example.schedulemanagementdevelop.dto.scheduleDto.ScheduleResponseDto;
import com.example.schedulemanagementdevelop.dto.userDto.*;
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
    public SignUpResponseDto signup(SignUpRequestDto requestDto) {
        User user = new User(requestDto.getName(), requestDto.getEmail(), requestDto.getPassword());
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

    public LoginResponseDto login(LoginRequestDto requestDto) {


        // 입력받은 email과 일치하는 Database 조회
        Long id = userRepository.findIdByEmail(requestDto.getEmail());
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Does not exists email : " + requestDto.getEmail());
        }
        // 입력받은 password와 Database에 저장된 password 비교
        String savedPassword = userRepository.findPasswordByEmail(requestDto.getEmail());
        if (!savedPassword.equals(requestDto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong password");
        }

        return new LoginResponseDto(id, requestDto.getEmail());
    }

    @Transactional
    public void updateUser(Long id, UserRequestDto requestDto) {
        if (requestDto.getName() == null || requestDto.getEmail() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title and contents are required values.");
        }

        User findUser = userRepository.findByIdOrElseThrow(id);
        findUser.updateUser(requestDto.getName(), requestDto.getEmail());
    }

    @Transactional
    public void updateName(Long id, UserRequestDto requestDto) {
        if (requestDto.getName() == null || requestDto.getEmail() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title and contents are required values.");
        }

        User findUser = userRepository.findByIdOrElseThrow(id);
        findUser.updateName(requestDto.getName());
    }

    @Transactional
    public void updateEmail(Long id, UserRequestDto requestDto) {
        if (requestDto.getName() != null || requestDto.getEmail() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title and contents are required values.");
        }

        User findUser = userRepository.findByIdOrElseThrow(id);
        findUser.updateEmail(requestDto.getEmail());
    }

    @Transactional
    public void updatePassword(Long id, UpdatePasswordRequestDto requestDto) {
        User findUser = userRepository.findByIdOrElseThrow(id);
        if (!findUser.getPassword().equals(requestDto.getOldPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "The password does not match.");
        }
        findUser.updatePassword(requestDto.getNewPassword());
    }

    @Transactional
    public void delete(Long id) {
        User findUser = userRepository.findByIdOrElseThrow(id);
        userRepository.delete(findUser);
    }
}