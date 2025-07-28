package com.rti.service.user.mapper;

import com.rti.dto.user.UserCreateDto;
import com.rti.dto.user.UserDto;
import com.rti.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-27T22:10:15-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.3 (Amazon.com Inc.)"
)
@Component
public class UserServiceMapperImpl implements UserServiceMapper {

    @Autowired
    private UserServiceMapperHelper userServiceMapperHelper;

    @Override
    public User mappingUserBy(UserCreateDto userCreateDto) {
        if ( userCreateDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.name( userCreateDto.getName() );
        user.email( userCreateDto.getEmail() );
        user.password( userServiceMapperHelper.setEncryptPassword( userCreateDto ) );
        user.role( userServiceMapperHelper.setRoleUser( userCreateDto ) );

        return user.build();
    }

    @Override
    public UserDto mappingUserDtoBy(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.id( user.getId() );
        userDto.name( user.getName() );
        userDto.email( user.getEmail() );
        userDto.password( user.getPassword() );
        userDto.role( user.getRole() );

        return userDto.build();
    }
}
