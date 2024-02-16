package com.example.springdatacloud.controllers;




import com.example.springdatacloud.entities.User;
import com.example.springdatacloud.repositories.UserRepository;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "User" , description = "Функционал взаимодействия с User")
public class UserDataController {
    private final UserRepository userRepository;
    @GetMapping("/find/{username}")
    public User getUser(@PathVariable String username){
        Optional<com.example.springdatacloud.entities.User> optionalUser = userRepository.findByUsername(username);
        return optionalUser.get();
    }
    @Hidden
    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception ex) {
        return new ResponseEntity<>("Возникла необработнная ошибка: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
