package com.rti.usecase.user;

import com.rti.controller.user.response.UserProfileResponse;
import com.rti.dto.user.UserDto;
import com.rti.service.user.UserService;
import com.rti.usecase.user.mapper.GetUserProfileUseCaseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetUserProfileUseCase {

    private final UserService userService;
    private final GetUserProfileUseCaseMapper mapper;

    public UserProfileResponse execute(final String userId){
        final UserDto user = userService.getUserById(userId);
        return mapper.mappingUserCompleteResponseBy(user);
    }
}
