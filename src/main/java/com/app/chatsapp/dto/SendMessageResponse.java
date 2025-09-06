package com.app.chatsapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SendMessageResponse {

    private Long senderId;
    private String fullName;
    private Long receiverId;
    private String content;
    private String status;
    private LocalDateTime sentAt;

}
