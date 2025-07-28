package com.rti.usecase.user.mapper;

import com.rti.controller.user.response.UserTinyResponse;
import com.rti.dto.user.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GetUserListUseCaseMapper {
    UserTinyResponse mappingUserTinyResponseBy(final UserDto userDto);
}
