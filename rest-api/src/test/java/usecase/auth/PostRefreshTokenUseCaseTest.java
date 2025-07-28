package usecase.auth;

import com.rti.service.auth.AuthenticationService;
import com.rti.usecase.auth.PostRefreshTokenUseCase;
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

public class PostRefreshTokenUseCaseTest {

    @InjectMocks
    private PostRefreshTokenUseCase postRefreshTokenUseCase;

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
        var refreshToken = TestHelper.mockRefreshToken();
        var authenticationResponseDto = TestHelper.mockAuthenticationResponseDto();
        var response = TestHelper.mockAccessResponse();

        when(authenticationService.refreshToken(refreshToken)).thenReturn(authenticationResponseDto);
        when(postAccessUseCaseMapper.mappingAccessResponseBy(authenticationResponseDto)).thenReturn(response);

        var result = postRefreshTokenUseCase.execute(refreshToken);

        Assertions.assertNotNull(result);
        verify(authenticationService, times(1)).refreshToken(refreshToken);
        verify(postAccessUseCaseMapper, times(1)).mappingAccessResponseBy(authenticationResponseDto);
    }
}
