package usecase.user;

import com.rti.service.user.UserService;
import com.rti.usecase.user.GetUserListUseCase;
import com.rti.usecase.user.mapper.GetUserListUseCaseMapper;
import helper.TestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetUserListUseCaseTest {

    @InjectMocks
    private GetUserListUseCase getUserListUseCase;

    @Mock
    private UserService userService;

    @Mock
    private GetUserListUseCaseMapper mapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute() {
        var users = TestHelper.mockUserDtoList();
        var response = TestHelper.mockUserTinyResponseList();
        var userDto = TestHelper.mockUserDto();
        var userTinyResponse = TestHelper.mockUserTinyResponse();

        when(userService.getAllUsers()).thenReturn(users);
        when(mapper.mappingUserTinyResponseBy(userDto)).thenReturn(userTinyResponse);

        var result = getUserListUseCase.execute();

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        verify(userService, times(1)).getAllUsers();
        verify(mapper, times(1)).mappingUserTinyResponseBy(userDto);
    }
}
