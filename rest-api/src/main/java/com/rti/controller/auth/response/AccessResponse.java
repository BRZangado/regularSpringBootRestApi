package com.rti.controller.auth.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccessResponse {
    private String accessToken;
    private String refreshToken;
    private LocalDateTime expirationDateTime;
}
