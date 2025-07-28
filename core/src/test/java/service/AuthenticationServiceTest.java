package service;

import com.rti.exception.BadRequestException;
import com.rti.repository.UserRepository;
import com.rti.service.auth.AuthenticationService;
import com.rti.service.auth.JwtService;
import com.rti.service.auth.mapper.AuthenticationServiceMapper;
import helper.TestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

public class AuthenticationServiceTest {

    @InjectMocks
    private AuthenticationService authenticationService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationServiceMapper authenticationServiceMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAuthenticate_Success() {
        var email = TestHelper.mockEmail();
        var password = TestHelper.mockPassword();
        var userEntity = TestHelper.mockOptionalUserEntity();
        var accessToken = TestHelper.mockAccessToken();
        var refreshToken = TestHelper.mockRefreshToken();
        var expirationDateTime = TestHelper.mockDateTime();
        var expectedDto = TestHelper.mockAuthenticationResponseDto();

        when(userRepository.findByEmail(email)).thenReturn(userEntity);
        when(jwtService.generateToken(userEntity.get().getId(), false)).thenReturn(accessToken);
        when(jwtService.generateToken(userEntity.get().getId(), true)).thenReturn(refreshToken);
        when(jwtService.getExpirationDateTime(accessToken)).thenReturn(expirationDateTime);
        when(authenticationServiceMapper.mappingAuthenticationResponseDtoBy(
                accessToken, refreshToken, expirationDateTime)).thenReturn(expectedDto);

        var result = authenticationService.authenticate(email, password);

        Assertions.assertNotNull(result);
        verify(userRepository, times(1)).findByEmail(email);
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtService, times(1)).generateToken(userEntity.get().getId(), false);
        verify(jwtService, times(1)).generateToken(userEntity.get().getId(), true);
        verify(jwtService, times(1)).getExpirationDateTime(accessToken);
        verify(authenticationServiceMapper, times(1)).mappingAuthenticationResponseDtoBy(
                accessToken, refreshToken, expirationDateTime);
    }

    @Test
    public void testAuthenticate_ThrowsBadRequestException() {
        var email = TestHelper.mockEmail();
        var password = TestHelper.mockPassword();

        when(userRepository.findByEmail(email)).thenReturn(TestHelper.mockEmptyOptionalUser());

        var exception = Assertions.assertThrows(
                BadRequestException.class,
                () -> authenticationService.authenticate(email, password)
        );

        Assertions.assertEquals("INVALID CREDENTIALS", exception.getMessage());
        verify(userRepository, times(1)).findByEmail(email);
        verifyNoInteractions(authenticationManager);
        verifyNoInteractions(jwtService);
        verifyNoInteractions(authenticationServiceMapper);
    }

    @Test
    public void testRefreshToken_Success() {
        var refreshToken = TestHelper.mockRefreshToken();
        var userId = TestHelper.mockUserId();
        var accessToken = TestHelper.mockAccessToken();
        var expirationDateTime = TestHelper.mockDateTime();
        var expectedDto = TestHelper.mockAuthenticationResponseDto();

        when(jwtService.isTokenValid(refreshToken)).thenReturn(true);
        when(jwtService.isRefreshToken(refreshToken)).thenReturn(true);
        when(jwtService.extractUserId(refreshToken)).thenReturn(userId);
        when(jwtService.generateToken(userId, false)).thenReturn(accessToken);
        when(jwtService.getExpirationDateTime(accessToken)).thenReturn(expirationDateTime);
        when(authenticationServiceMapper.mappingAuthenticationResponseDtoBy(
                accessToken, refreshToken, expirationDateTime)).thenReturn(expectedDto);

        var result = authenticationService.refreshToken(refreshToken);

        Assertions.assertNotNull(result);
        verify(jwtService, times(1)).isTokenValid(refreshToken);
        verify(jwtService, times(1)).isRefreshToken(refreshToken);
        verify(jwtService, times(1)).extractUserId(refreshToken);
        verify(jwtService, times(1)).generateToken(userId, false);
        verify(jwtService, times(1)).getExpirationDateTime(accessToken);
        verify(authenticationServiceMapper, times(1)).mappingAuthenticationResponseDtoBy(
                accessToken, refreshToken, expirationDateTime);
    }

    @Test
    public void testRefreshToken_InvalidToken_ThrowsRuntimeException() {
        var refreshToken = TestHelper.mockRefreshToken();

        when(jwtService.isTokenValid(refreshToken)).thenReturn(false);

        var exception = Assertions.assertThrows(
                RuntimeException.class,
                () -> authenticationService.refreshToken(refreshToken)
        );

        Assertions.assertEquals("INVALID REFRESH TOKEN.", exception.getMessage());
        verify(jwtService, times(1)).isTokenValid(refreshToken);
        verifyNoInteractions(authenticationServiceMapper);
    }

    @Test
    public void testRefreshToken_NotRefreshToken_ThrowsRuntimeException() {
        var refreshToken = TestHelper.mockRefreshToken();

        when(jwtService.isTokenValid(refreshToken)).thenReturn(true);
        when(jwtService.isRefreshToken(refreshToken)).thenReturn(false);

        var exception = Assertions.assertThrows(
                RuntimeException.class,
                () -> authenticationService.refreshToken(refreshToken)
        );

        Assertions.assertEquals("INVALID REFRESH TOKEN.", exception.getMessage());
        verify(jwtService, times(1)).isTokenValid(refreshToken);
        verify(jwtService, times(1)).isRefreshToken(refreshToken);
        verifyNoInteractions(authenticationServiceMapper);
    }
}
