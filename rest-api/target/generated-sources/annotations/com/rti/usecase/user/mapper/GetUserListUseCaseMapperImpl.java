package com.rti.usecase.user.mapper;

import com.rti.controller.user.response.UserTinyResponse;
import com.rti.dto.user.UserDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-27T22:10:17-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.3 (Amazon.com Inc.)"
)
@Component
public class GetUserListUseCaseMapperImpl implements GetUserListUseCaseMapper {

    @Override
    public UserTinyResponse mappingUserTinyResponseBy(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        UserTinyResponse.UserTinyResponseBuilder userTinyResponse = UserTinyResponse.builder();

        userTinyResponse.id( userDto.getId() );

        return userTinyResponse.build();
    }
}
