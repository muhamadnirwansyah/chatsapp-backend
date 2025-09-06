package com.app.chatsapp.controller;

import com.app.chatsapp.dto.ListPhoneNumberResponse;
import com.app.chatsapp.service.ListPhoneNumberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/secure")
@RequiredArgsConstructor
public class SecureController {

    private final ListPhoneNumberService listPhoneNumberService;

    @GetMapping(value = "/ping")
    public ResponseEntity<String> ping(){
        return ResponseEntity.ok()
                .body("PONG !!");
    }

    @GetMapping(value = "/phone-books")
    public ResponseEntity<ListPhoneNumberResponse> phoneBooks(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(listPhoneNumberService.listPhoneBooks());
    }
}
