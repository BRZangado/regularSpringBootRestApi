package com.rti.usecase.user.mapper;

import com.rti.controller.user.request.UserCreateRequest;
import com.rti.controller.user.response.UserTinyResponse;
import com.rti.dto.user.UserCreateDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-27T22:10:17-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.3 (Amazon.com Inc.)"
)
@Component
public class PostCreateUserUseCaseMapperImpl implements PostCreateUserUseCaseMapper {

    @Override
    public UserCreateDto mappingUserCreateDto(UserCreateRequest userCreateRequest) {
        if ( userCreateRequest == null ) {
            return null;
        }

        UserCreateDto.UserCreateDtoBuilder userCreateDto = UserCreateDto.builder();

        userCreateDto.name( userCreateRequest.getName() );
        userCreateDto.email( userCreateRequest.getEmail() );
        userCreateDto.password( userCreateRequest.getPassword() );

        return userCreateDto.build();
    }

    @Override
    public UserTinyResponse mappingUserCreateResponse(String id) {
        if ( id == null ) {
            return null;
        }

        UserTinyResponse.UserTinyResponseBuilder userTinyResponse = UserTinyResponse.builder();

        userTinyResponse.id( id );

        return userTinyResponse.build();
    }
}
