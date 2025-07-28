package usecase.user;

import com.rti.service.user.UserService;
import com.rti.usecase.user.PostCreateUserUseCase;
import com.rti.usecase.user.mapper.PostCreateUserUseCaseMapper;
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

public class PostCreateUserUseCaseTest {

    @InjectMocks
    private PostCreateUserUseCase postCreateUserUseCase;

    @Mock
    private UserService userService;

    @Mock
    private PostCreateUserUseCaseMapper mapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute() {
        var request = TestHelper.mockUserCreateRequest();
        var userCreateDto = TestHelper.mockUserCreateDto();
        var createdUser = TestHelper.mockUserDto();
        var response = TestHelper.mockUserTinyResponse();

        when(mapper.mappingUserCreateDto(request)).thenReturn(userCreateDto);
        when(userService.saveUser(userCreateDto)).thenReturn(createdUser);
        when(mapper.mappingUserCreateResponse(createdUser.getId())).thenReturn(response);

        var result = postCreateUserUseCase.execute(request);

        Assertions.assertNotNull(result);
        verify(mapper, times(1)).mappingUserCreateDto(request);
        verify(userService, times(1)).saveUser(userCreateDto);
        verify(mapper, times(1)).mappingUserCreateResponse(createdUser.getId());
    }
}
