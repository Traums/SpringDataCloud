package com.example.springdatacloud.controllers;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@Tag(name = "Main" , description = "Функционал разделения ролей")
public class MainController {
    @Operation(summary = "Общедоступный раздел")
    @GetMapping("/unsecured")
    public String unsecuredData(){
        return "Unsecured data";
    }
    @Operation(summary = "Защищенный раздел")
    @GetMapping("/secured")
    public String securedData(){
        return "Secured data";
    }
    @Operation(summary = "Раздел для пользователей с правами администратора")
    @GetMapping("/admin")
    public String adminData(){
        return "Admin data";
    }
    @Operation(summary = "Получение имени авторизованного пользователя")
    @GetMapping("/info")
    public String userData(Principal principal){
        return principal.getName();
    }
    @Hidden
    @GetMapping ("/")
    public ResponseEntity<Object> index(){
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create("http://localhost:8189/api/swagger-ui/index.html"))
                .build();
    }
}
