package com.app.chatsapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListPhoneNumberResponse {

    private List<PhoneBooks> phoneBooks;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PhoneBooks {
        private String phoneNumber;
        private Long userId;
        private String fullName;
    }
}
