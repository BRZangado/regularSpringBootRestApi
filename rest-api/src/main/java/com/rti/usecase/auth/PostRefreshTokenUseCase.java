package com.rti.usecase.auth;

import com.rti.controller.auth.response.AccessResponse;
import com.rti.dto.auth.AuthenticationResponseDto;
import com.rti.service.auth.AuthenticationService;
import com.rti.usecase.auth.mapper.PostAccessUseCaseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostRefreshTokenUseCase {

    private final AuthenticationService authenticationService;
    private final PostAccessUseCaseMapper postAccessUseCaseMapper;

    public AccessResponse execute(final String refreshToken){
        final AuthenticationResponseDto authenticationResponseDto = authenticationService.refreshToken(refreshToken);
        return postAccessUseCaseMapper.mappingAccessResponseBy(authenticationResponseDto);
    }
}
