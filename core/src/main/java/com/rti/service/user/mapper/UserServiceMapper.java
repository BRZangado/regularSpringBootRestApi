package com.rti.service.user.mapper;

import com.rti.dto.user.UserCreateDto;
import com.rti.dto.user.UserDto;
import com.rti.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserServiceMapperHelper.class})
public interface UserServiceMapper {

    @Mapping(target = "name", source = "userCreateDto.name")
    @Mapping(target = "email", source = "userCreateDto.email")
    @Mapping(target = "password", source = "userCreateDto", qualifiedByName = "setEncryptPassword")
    @Mapping(target = "role", source = "userCreateDto", qualifiedByName = "setRoleUser")
    User mappingUserBy(final UserCreateDto userCreateDto);

    UserDto mappingUserDtoBy(final User user);
}
