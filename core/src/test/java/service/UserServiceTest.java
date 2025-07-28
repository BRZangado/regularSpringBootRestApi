package service;

import com.rti.exception.EntityPersistException;
import com.rti.exception.ResourceNotFoundException;
import com.rti.repository.UserRepository;
import com.rti.service.user.UserService;
import com.rti.service.user.mapper.UserServiceMapper;
import helper.TestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserServiceMapper userServiceMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveUser_Success() {
        var userCreateDto = TestHelper.mockUserCreateDto();
        var userEntity = TestHelper.mockUserEntity();
        var savedUserEntity = TestHelper.mockUserEntity();
        var expectedDto = TestHelper.mockUserDto();

        when(userServiceMapper.mappingUserBy(userCreateDto)).thenReturn(userEntity);
        when(userRepository.save(userEntity)).thenReturn(savedUserEntity);
        when(userServiceMapper.mappingUserDtoBy(savedUserEntity)).thenReturn(expectedDto);

        var result = userService.saveUser(userCreateDto);

        Assertions.assertNotNull(result);
        verify(userServiceMapper, times(1)).mappingUserBy(userCreateDto);
        verify(userRepository, times(1)).save(userEntity);
        verify(userServiceMapper, times(1)).mappingUserDtoBy(savedUserEntity);
    }

    @Test
    public void testSaveUser_ThrowsEntityPersistException() {
        var userCreateDto = TestHelper.mockUserCreateDto();

        when(userServiceMapper.mappingUserBy(userCreateDto))
                .thenThrow(TestHelper.mockRuntimeException());

        var exception = Assertions.assertThrows(
                EntityPersistException.class,
                () -> userService.saveUser(userCreateDto)
        );

        Assertions.assertEquals("ERROR WHILE SAVING USER, CHECK INPUT DATA", exception.getMessage());
        verify(userServiceMapper, times(1)).mappingUserBy(userCreateDto);
        verifyNoInteractions(userRepository);
    }

    @Test
    public void testGetAllUsers_Success() {
        var userEntities = TestHelper.mockUserEntityList();
        var userDto = TestHelper.mockUserDto();

        when(userRepository.findAll()).thenReturn(userEntities);
        when(userServiceMapper.mappingUserDtoBy(userEntities.get(0))).thenReturn(userDto);

        var result = userService.getAllUsers();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        verify(userRepository, times(1)).findAll();
        verify(userServiceMapper, times(1)).mappingUserDtoBy(userEntities.get(0));
    }

    @Test
    public void testGetUserById_Success() {
        var userId = TestHelper.mockUserId();
        var userEntity = TestHelper.mockOptionalUserEntity();
        var userDto = TestHelper.mockUserDto();

        when(userRepository.findById(userId)).thenReturn(userEntity);
        when(userServiceMapper.mappingUserDtoBy(userEntity.get())).thenReturn(userDto);

        var result = userService.getUserById(userId);

        Assertions.assertNotNull(result);
        verify(userRepository, times(1)).findById(userId);
        verify(userServiceMapper, times(1)).mappingUserDtoBy(userEntity.get());
    }

    @Test
    public void testGetUserById_ThrowsResourceNotFoundException() {
        var userId = "non-existent-id";

        when(userRepository.findById(userId)).thenReturn(TestHelper.mockEmptyOptionalUser());

        var exception = Assertions.assertThrows(
                ResourceNotFoundException.class,
                () -> userService.getUserById(userId)
        );

        Assertions.assertEquals("USER NOT FOUND FOR GIVEN ID", exception.getMessage());
        verify(userRepository, times(1)).findById(userId);
        verifyNoInteractions(userServiceMapper);
    }
}
