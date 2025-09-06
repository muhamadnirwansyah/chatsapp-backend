package com.app.chatsapp.service;

import com.app.chatsapp.dto.LoginRequest;
import com.app.chatsapp.dto.LoginResponse;
import com.app.chatsapp.repository.UsersRepository;
import com.app.chatsapp.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse logIn(String logId, LoginRequest loginRequest){
        log.info("Start - LogIn with logId : {} - and phone number : {}",logId, loginRequest.getPhoneNumber());
        var findingData = usersRepository.findByPhoneNumber(loginRequest.getPhoneNumber())
                .orElseThrow(() -> new RuntimeException("User with phone number is not found !"));
        if (!passwordEncoder.matches(loginRequest.getPassword(), findingData.getPassword())){
            log.info("Wrong LogIn with logId : {} - and phone number : {}",logId, loginRequest.getPhoneNumber());
            throw new RuntimeException("Wrong password !");
        }
        log.info("Finish - LogIn with logId : {} - and phone number : {}",logId, loginRequest.getPhoneNumber());
        return LoginResponse.builder()
                .userId(findingData.getId())
                .fullName(findingData.getName())
                .token(JwtUtils.generateToken(findingData.getPhoneNumber()))
                .build();
    }
}
