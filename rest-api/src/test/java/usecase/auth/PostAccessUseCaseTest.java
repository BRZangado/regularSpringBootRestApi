package usecase.auth;

import com.rti.service.auth.AuthenticationService;
import com.rti.usecase.auth.PostAccessUseCase;
import com.rti.usecase.auth.mapper.PostAccessUseCaseMapper;
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

public class PostAccessUseCaseTest {

    @InjectMocks
    private PostAccessUseCase postAccessUseCase;

    @Mock
    private AuthenticationService authenticationService;

    @Mock
    private PostAccessUseCaseMapper postAccessUseCaseMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute() {
        var username = TestHelper.mockEmail();
        var password = TestHelper.mockPassword();
        var authenticationResponseDto = TestHelper.mockAuthenticationResponseDto();
        var response = TestHelper.mockAccessResponse();

        when(authenticationService.authenticate(username, password)).thenReturn(authenticationResponseDto);
        when(postAccessUseCaseMapper.mappingAccessResponseBy(authenticationResponseDto)).thenReturn(response);

        var result = postAccessUseCase.execute(username, password);

        Assertions.assertNotNull(result);
        verify(authenticationService, times(1)).authenticate(username, password);
        verify(postAccessUseCaseMapper, times(1)).mappingAccessResponseBy(authenticationResponseDto);
    }
}
