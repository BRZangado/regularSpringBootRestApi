package com.rti.usecase.user;

import com.rti.controller.user.request.UserCreateRequest;
import com.rti.controller.user.response.UserTinyResponse;
import com.rti.dto.user.UserCreateDto;
import com.rti.dto.user.UserDto;
import com.rti.service.user.UserService;
import com.rti.usecase.user.mapper.PostCreateUserUseCaseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostCreateUserUseCase {

    private final UserService userService;
    private final PostCreateUserUseCaseMapper mapper;

    public UserTinyResponse execute(final UserCreateRequest userCreateRequest){
        final UserCreateDto userCreateDto = mapper.mappingUserCreateDto(userCreateRequest);
        final UserDto createdUser = userService.saveUser(userCreateDto);
        return mapper.mappingUserCreateResponse(createdUser.getId());
    }
}
