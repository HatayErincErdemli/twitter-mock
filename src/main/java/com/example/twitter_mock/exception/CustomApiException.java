package com.example.twitter_mock.exception;


import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CustomApiException {
    private final String message;      // Hata mesajı
    private final String details;      // Daha fazla bilgi
    private final LocalDateTime timestamp; // Hata zamanı
}
