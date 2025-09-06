package com.app.chatsapp.service;

import com.app.chatsapp.dto.RegisterRequest;
import com.app.chatsapp.dto.RegisterResponse;
import com.app.chatsapp.entity.Users;
import com.app.chatsapp.repository.RolesRepository;
import com.app.chatsapp.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final RolesRepository rolesRepository;
    private final UsersRepository usersRepository;

    public RegisterResponse register(String logId, RegisterRequest request){
        log.info("Start - Register with logId : {} - data : {}",logId, request);
        if (!request.getPassword().equals(request.getConfirmPassword())){
            log.info("Error Password & Confirm password - Register with logId : {} - data : {}",logId, request);
            throw new RuntimeException("Password and confirm password do not match !");
        }
        Users users = new Users();
        users.setName(request.getName());
        users.setPhoneNumber(request.getPhoneNumber());
        users.setPassword(passwordEncoder.encode(request.getPassword()));
        users.setCreatedAt(LocalDateTime.now());
        var role = rolesRepository.findByName("ADMIN")
                .orElseThrow(() -> new RuntimeException("Roles not found !"));
        users.setRoles(role);
        usersRepository.save(users);
        log.info("Finish - Register with logId : {} - data : {}",logId, request);
        return RegisterResponse.builder()
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .createdAt(users.getCreatedAt())
                .build();
    }
}
