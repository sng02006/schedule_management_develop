package com.example.schedulemanagementdevelop.repository;

import com.example.schedulemanagementdevelop.entity.Schedule;
import com.example.schedulemanagementdevelop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    default User findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id));
    }

    Optional<User> findUserById(Long userId);

    default User findUserByUserIdOrElseThrow(Long userId){
        return findUserById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist userId = " + userId));
    }

    Optional<User> findUserByEmail(String email);

//    default User findUserByEmailOrElseThrow(String email){
//        return findUserByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist userId = " + email));
//    }

    default Long findIdByEmail(String email){
        return findUserByEmail(email).stream()
                .filter(user -> user.getEmail().equals(email))
                .map(User::getId)
                .findFirst()
                .orElse(null);
    }

    default String findPasswordByEmail(String email){
        return findUserByEmail(email).stream()
                .filter(user -> user.getEmail().equals(email))
                .map(User::getPassword)
                .findFirst()
                .orElse(null);
    }
}