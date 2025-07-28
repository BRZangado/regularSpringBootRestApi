package com.rti.service.auth.mapper;

import com.rti.dto.auth.AuthenticationResponseDto;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface AuthenticationServiceMapper {
    AuthenticationResponseDto mappingAuthenticationResponseDtoBy(final String accessToken,
                                                                 final String refreshToken,
                                                                 final LocalDateTime expirationDateTime);
}
