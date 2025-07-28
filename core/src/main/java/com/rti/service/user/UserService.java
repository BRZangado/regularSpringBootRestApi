package com.rti.service.user;

import com.rti.dto.user.UserCreateDto;
import com.rti.dto.user.UserDto;
import com.rti.entity.User;
import com.rti.exception.EntityPersistException;
import com.rti.exception.ResourceNotFoundException;
import com.rti.repository.UserRepository;
import com.rti.service.user.mapper.UserServiceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserServiceMapper mapper;

    public UserDto saveUser(final UserCreateDto userCreateDto) {

        try {
            final User user = mapper.mappingUserBy(userCreateDto);
            final User savedEntity = userRepository.save(user);
            return mapper.mappingUserDtoBy(savedEntity);
        } catch (Exception e) {
            log.error("USER SAVING ERROR -> \n {}", e.getMessage());
            throw new EntityPersistException("ERROR WHILE SAVING USER, CHECK INPUT DATA");
        }
    }

    public List<UserDto> getAllUsers() {
        final List<User> users = userRepository.findAll();
        return users.stream().map(mapper::mappingUserDtoBy).toList();
    }

    public UserDto getUserById(final String id) {
        final User user = getUserEntityById(id);
        return mapper.mappingUserDtoBy(user);
    }

    private User getUserEntityById(final String id) {
        final Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("USER NOT FOUND FOR GIVEN ID");
        }
        return user.get();
    }
}
