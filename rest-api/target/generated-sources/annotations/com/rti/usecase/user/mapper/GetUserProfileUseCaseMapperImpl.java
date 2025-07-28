package com.rti.usecase.user.mapper;

import com.rti.controller.user.response.UserProfileResponse;
import com.rti.dto.user.UserDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-27T22:10:17-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.3 (Amazon.com Inc.)"
)
@Component
public class GetUserProfileUseCaseMapperImpl implements GetUserProfileUseCaseMapper {

    @Override
    public UserProfileResponse mappingUserCompleteResponseBy(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        UserProfileResponse.UserProfileResponseBuilder userProfileResponse = UserProfileResponse.builder();

        userProfileResponse.id( userDto.getId() );
        userProfileResponse.name( userDto.getName() );
        userProfileResponse.email( userDto.getEmail() );

        return userProfileResponse.build();
    }
}
