package com.rti.usecase.auth.mapper;

import com.rti.controller.auth.response.AccessResponse;
import com.rti.dto.auth.AuthenticationResponseDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-27T22:10:17-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.3 (Amazon.com Inc.)"
)
@Component
public class PostAccessUseCaseMapperImpl implements PostAccessUseCaseMapper {

    @Override
    public AccessResponse mappingAccessResponseBy(AuthenticationResponseDto authenticationResponseDto) {
        if ( authenticationResponseDto == null ) {
            return null;
        }

        AccessResponse.AccessResponseBuilder accessResponse = AccessResponse.builder();

        accessResponse.accessToken( authenticationResponseDto.getAccessToken() );
        accessResponse.refreshToken( authenticationResponseDto.getRefreshToken() );
        accessResponse.expirationDateTime( authenticationResponseDto.getExpirationDateTime() );

        return accessResponse.build();
    }
}
