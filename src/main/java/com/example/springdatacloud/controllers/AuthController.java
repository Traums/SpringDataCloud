package com.example.springdatacloud.controllers;

import com.example.springdatacloud.Validation.ValidationErrorResponse;
import com.example.springdatacloud.Validation.Violation;
import com.example.springdatacloud.dto.JwtRequest;
import com.example.springdatacloud.dto.RegistrationUserDto;
import com.example.springdatacloud.service.AuthService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Tag(name = "Authorization" , description = "Функционал авторизации")
//@Validated
public class AuthController {
    private final AuthService authService;
    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest){
        return authService.createAuthToken(authRequest);
    }
    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@Valid @RequestBody RegistrationUserDto registrationUserDto){
        return authService.createNewUser(registrationUserDto);
    }
    @ControllerAdvice
    public class ErrorHandlingControllerAdvice {
        @ResponseBody
        @ExceptionHandler(ConstraintViolationException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public ValidationErrorResponse onConstraintValidationException(
                ConstraintViolationException e
        ) {
            final List<Violation> violations = e.getConstraintViolations().stream()
                    .map(
                            violation -> new Violation(
                                    violation.getPropertyPath().toString(),
                                    violation.getMessage()
                            )
                    )
                    .collect(Collectors.toList());
            return new ValidationErrorResponse(violations);
        }
        @ExceptionHandler(MethodArgumentNotValidException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ResponseBody
        public ValidationErrorResponse onMethodArgumentNotValidException(
                MethodArgumentNotValidException e
        ) {
            final List<Violation> violations = e.getBindingResult().getFieldErrors().stream()
                    .map(error -> new Violation(error.getField(), error.getDefaultMessage()))
                    .collect(Collectors.toList());
            return new ValidationErrorResponse(violations);
        }
    }
//    @Hidden
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity handleException(Exception ex) {
//        return new ResponseEntity<>("Возникла необработнная ошибка: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
