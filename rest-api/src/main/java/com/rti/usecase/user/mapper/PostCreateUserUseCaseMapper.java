package com.rti.usecase.user.mapper;

import com.rti.controller.user.request.UserCreateRequest;
import com.rti.controller.user.response.UserTinyResponse;
import com.rti.dto.user.UserCreateDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostCreateUserUseCaseMapper {
    UserCreateDto mappingUserCreateDto(final UserCreateRequest userCreateRequest);
    UserTinyResponse mappingUserCreateResponse(final String id);
}
