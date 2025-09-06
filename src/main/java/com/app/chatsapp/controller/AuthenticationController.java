package com.app.chatsapp.controller;

import com.app.chatsapp.dto.LoginRequest;
import com.app.chatsapp.dto.LoginResponse;
import com.app.chatsapp.dto.RegisterRequest;
import com.app.chatsapp.dto.RegisterResponse;
import com.app.chatsapp.service.LoginService;
import com.app.chatsapp.service.RegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final RegisterService registerService;
    private final LoginService loginService;

    @PostMapping(value = "/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.status(HttpStatus.OK)
                .body(registerService.register(UUID.randomUUID().toString(), request));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.status(HttpStatus.OK)
                .body(loginService.logIn(UUID.randomUUID().toString(), request));
    }
}
