package com.rti.service.user.mapper;

import com.rti.dto.user.UserCreateDto;
import com.rti.enums.Role;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserServiceMapperHelper {

    private final PasswordEncoder passwordEncoder;

    @Named("setEncryptPassword")
    public String setEncryptPassword(final UserCreateDto userCreateDto){
        return passwordEncoder.encode(userCreateDto.getPassword());
    }

    @Named("setRoleUser")
    public String setRoleUser(final UserCreateDto userCreateDto){
        return Role.USER.name();
    }
}
