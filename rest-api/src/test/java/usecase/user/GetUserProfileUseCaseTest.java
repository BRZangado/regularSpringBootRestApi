package usecase.user;

import com.rti.service.user.UserService;
import com.rti.usecase.user.GetUserProfileUseCase;
import com.rti.usecase.user.mapper.GetUserProfileUseCaseMapper;
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

public class GetUserProfileUseCaseTest {

    @InjectMocks
    private GetUserProfileUseCase getUserProfileUseCase;

    @Mock
    private UserService userService;

    @Mock
    private GetUserProfileUseCaseMapper mapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute() {
        var userId = TestHelper.mockUserId();
        var user = TestHelper.mockUserDto();
        var response = TestHelper.mockUserProfileResponse();

        when(userService.getUserById(userId)).thenReturn(user);
        when(mapper.mappingUserCompleteResponseBy(user)).thenReturn(response);

        var result = getUserProfileUseCase.execute(userId);

        Assertions.assertNotNull(result);
        verify(userService, times(1)).getUserById(userId);
        verify(mapper, times(1)).mappingUserCompleteResponseBy(user);
    }
}
