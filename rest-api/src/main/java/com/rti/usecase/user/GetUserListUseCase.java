package com.rti.usecase.user;

import com.rti.controller.user.response.UserTinyResponse;
import com.rti.dto.user.UserDto;
import com.rti.service.user.UserService;
import com.rti.usecase.user.mapper.GetUserListUseCaseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetUserListUseCase {

    private final UserService userService;
    private final GetUserListUseCaseMapper mapper;

    public List<UserTinyResponse> execute(){
        final List<UserDto> users = userService.getAllUsers();
        return users.stream().map(mapper::mappingUserTinyResponseBy).toList();
    }
}
