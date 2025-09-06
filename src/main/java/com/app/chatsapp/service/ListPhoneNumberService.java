package com.app.chatsapp.service;

import com.app.chatsapp.dto.ListPhoneNumberResponse;
import com.app.chatsapp.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ListPhoneNumberService {

    private final UsersRepository usersRepository;

    public ListPhoneNumberResponse listPhoneBooks(){
        log.info("List Phone Books..");
        return ListPhoneNumberResponse.builder()
                .phoneBooks(usersRepository.findAll().stream()
                        .map(phoneBook -> ListPhoneNumberResponse.PhoneBooks
                                .builder()
                                .userId(phoneBook.getId())
                                .phoneNumber(phoneBook.getPhoneNumber())
                                .fullName(phoneBook.getName())
                                .build()).toList())
                .build();
    }
}
