package com.app.chatsapp.service;

import com.app.chatsapp.dto.SendMessageRequest;
import com.app.chatsapp.dto.SendMessageResponse;
import com.app.chatsapp.entity.Message;
import com.app.chatsapp.entity.Users;
import com.app.chatsapp.repository.MessageRepository;
import com.app.chatsapp.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class SendMessageService {

    private final UsersRepository usersRepository;
    private final MessageRepository messageRepository;

    public SendMessageResponse send(String logId,SendMessageRequest request){
        log.info("Start process send message request with logId : {} - SenderId : {} - Receiver : {} - content : {}",logId,
                request.getSenderId(), request.getReceiverId(), request.getContent());
        Message message = new Message();
        Users usersSender = usersRepository.findById(request.getSenderId())
                        .orElseThrow(() -> new RuntimeException("Users Sender is not found !"));
        Users usersReceiver = usersRepository.findById(request.getReceiverId())
                        .orElseThrow(() -> new RuntimeException("Users Receiver is not found !"));

        message.setSenderId(usersSender.getId());
        message.setReceiveId(usersReceiver.getId());
        message.setContent(request.getContent());
        message.setFullName(request.getFullName());
        message.setStatus("SENT");
        message.setSentAt(LocalDateTime.now());
        messageRepository.save(message);
        log.info("Finish process send message request with logId : {} - SenderId : {} - Receiver : {} - content : {}",logId,
                request.getSenderId(), request.getReceiverId(), request.getContent());
        return SendMessageResponse.builder().senderId(usersSender.getId())
                .receiverId(usersReceiver.getId())
                .content(message.getContent())
                .fullName(request.getFullName())
                .sentAt(message.getSentAt())
                .status(message.getStatus())
                .build();
    }
}
