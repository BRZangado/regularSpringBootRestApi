package com.rti.service.auth;

import com.rti.dto.auth.AuthenticationResponseDto;
import com.rti.entity.User;
import com.rti.exception.BadRequestException;
import com.rti.repository.UserRepository;
import com.rti.service.auth.mapper.AuthenticationServiceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationServiceMapper authenticationServiceMapper;

    public AuthenticationResponseDto authenticate(final String email, final String password) {
        final Optional<User> user = userRepository.findByEmail(email);
        if(user.isEmpty()){
            throw new BadRequestException("INVALID CREDENTIALS");
        }

        var auth = new UsernamePasswordAuthenticationToken(email, password);
        authenticationManager.authenticate(auth);

        final String accessToken = jwtService.generateToken(user.get().getId(), false);
        final String refreshToken = jwtService.generateToken(user.get().getId(), true);
        final LocalDateTime expirationDateTime = jwtService.getExpirationDateTime(accessToken);

        return authenticationServiceMapper.mappingAuthenticationResponseDtoBy(
                accessToken, refreshToken, expirationDateTime);
    }

    public AuthenticationResponseDto refreshToken(final String token) {
        if (!jwtService.isTokenValid(token) || !jwtService.isRefreshToken(token)) {
            throw new RuntimeException("INVALID REFRESH TOKEN.");
        }

        final String userId = jwtService.extractUserId(token);
        final String accessToken = jwtService.generateToken(userId, false);
        final LocalDateTime expirationDateTime = jwtService.getExpirationDateTime(accessToken);

        return authenticationServiceMapper.mappingAuthenticationResponseDtoBy(
                accessToken, token, expirationDateTime);
    }
}
