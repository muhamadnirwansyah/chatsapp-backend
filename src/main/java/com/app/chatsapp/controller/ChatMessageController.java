package com.app.chatsapp.controller;

import com.app.chatsapp.dto.SendMessageRequest;
import com.app.chatsapp.dto.SendMessageResponse;
import com.app.chatsapp.service.SendMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ChatMessageController {

    private final SimpMessagingTemplate messagingTemplate;
    private final SendMessageService sendMessageService;

    @MessageMapping("/chat.send")
    public void sendMessage(SendMessageRequest request){
        SendMessageResponse sendMessageResponse = sendMessageService.send(UUID.randomUUID().toString(),request);
        //broadcast message receiver
        messagingTemplate.convertAndSend("/queue/user-"+sendMessageResponse.getReceiverId(),sendMessageResponse);
        log.info("Send WS to User {} -> {} ",sendMessageResponse.getReceiverId(), sendMessageResponse.getContent());
        //broadcast message also to sender
        messagingTemplate.convertAndSend("/queue/user-"+sendMessageResponse.getSenderId(), sendMessageResponse);
        log.info("Send WS to User {} -> {} ",sendMessageResponse.getSenderId(), sendMessageResponse.getContent());
        //broadcast message global
        //messagingTemplate.convertAndSend("/topic/messages",sendMessageResponse);
    }
}
