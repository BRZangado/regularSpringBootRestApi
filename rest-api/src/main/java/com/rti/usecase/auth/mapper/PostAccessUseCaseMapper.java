package com.rti.usecase.auth.mapper;

import com.rti.controller.auth.response.AccessResponse;
import com.rti.dto.auth.AuthenticationResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostAccessUseCaseMapper {
    AccessResponse mappingAccessResponseBy(final AuthenticationResponseDto authenticationResponseDto);
}
