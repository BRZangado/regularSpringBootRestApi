package com.rti.usecase.auth;

import com.rti.controller.auth.response.AccessResponse;
import com.rti.dto.auth.AuthenticationResponseDto;
import com.rti.service.auth.AuthenticationService;
import com.rti.usecase.auth.mapper.PostAccessUseCaseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostAccessUseCase {

    private final AuthenticationService authenticationService;
    private final PostAccessUseCaseMapper postAccessUseCaseMapper;

    public AccessResponse execute(final String username, final String password){
        final AuthenticationResponseDto authenticationResponseDto
                = authenticationService.authenticate(username, password);
        return postAccessUseCaseMapper.mappingAccessResponseBy(authenticationResponseDto);
    }
}
