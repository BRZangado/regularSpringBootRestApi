package service;

import com.rti.service.auth.JwtService;
import helper.TestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class JwtServiceTest {

    @InjectMocks
    private JwtService jwtService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGenerateToken_AccessToken_Success() {
        var userId = TestHelper.mockUserId();
        var isRefreshToken = false;

        var result = jwtService.generateToken(userId, isRefreshToken);

        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.length() > 0);
    }

    @Test
    public void testGenerateToken_RefreshToken_Success() {
        var userId = TestHelper.mockUserId();
        var isRefreshToken = true;

        var result = jwtService.generateToken(userId, isRefreshToken);

        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.length() > 0);
    }

    @Test
    public void testExtractUserId_Success() {
        var userId = TestHelper.mockUserId();
        var token = jwtService.generateToken(userId, false);

        var result = jwtService.extractUserId(token);

        Assertions.assertEquals(userId, result);
    }

    @Test
    public void testIsTokenValid_ValidToken_ReturnsTrue() {
        var userId = TestHelper.mockUserId();
        var token = jwtService.generateToken(userId, false);

        var result = jwtService.isTokenValid(token);

        Assertions.assertTrue(result);
    }

    @Test
    public void testIsTokenValid_InvalidToken_ReturnsFalse() {
        var invalidToken = "invalid.token.here";

        var result = jwtService.isTokenValid(invalidToken);

        Assertions.assertFalse(result);
    }

    @Test
    public void testIsRefreshToken_RefreshToken_ReturnsTrue() {
        var userId = TestHelper.mockUserId();
        var refreshToken = jwtService.generateToken(userId, true);

        var result = jwtService.isRefreshToken(refreshToken);

        Assertions.assertTrue(result);
    }

    @Test
    public void testIsRefreshToken_AccessToken_ReturnsFalse() {
        var userId = TestHelper.mockUserId();
        var accessToken = jwtService.generateToken(userId, false);

        var result = jwtService.isRefreshToken(accessToken);

        Assertions.assertFalse(result);
    }

    @Test
    public void testGetExpirationDateTime_Success() {
        var userId = TestHelper.mockUserId();
        var token = jwtService.generateToken(userId, false);

        var result = jwtService.getExpirationDateTime(token);

        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.isAfter(TestHelper.mockDateTime()));
    }
}
