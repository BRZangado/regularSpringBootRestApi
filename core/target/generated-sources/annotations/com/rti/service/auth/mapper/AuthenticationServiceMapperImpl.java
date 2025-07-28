package com.rti.service.auth.mapper;

import com.rti.dto.auth.AuthenticationResponseDto;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-27T22:10:14-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.3 (Amazon.com Inc.)"
)
@Component
public class AuthenticationServiceMapperImpl implements AuthenticationServiceMapper {

    @Override
    public AuthenticationResponseDto mappingAuthenticationResponseDtoBy(String accessToken, String refreshToken, LocalDateTime expirationDateTime) {
        if ( accessToken == null && refreshToken == null && expirationDateTime == null ) {
            return null;
        }

        AuthenticationResponseDto.AuthenticationResponseDtoBuilder authenticationResponseDto = AuthenticationResponseDto.builder();

        authenticationResponseDto.accessToken( accessToken );
        authenticationResponseDto.refreshToken( refreshToken );
        authenticationResponseDto.expirationDateTime( expirationDateTime );

        return authenticationResponseDto.build();
    }
}
