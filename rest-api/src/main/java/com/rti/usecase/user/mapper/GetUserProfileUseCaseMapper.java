package com.rti.usecase.user.mapper;

import com.rti.controller.user.response.UserProfileResponse;
import com.rti.dto.user.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GetUserProfileUseCaseMapper {
    UserProfileResponse mappingUserCompleteResponseBy(final UserDto userDto);
}
