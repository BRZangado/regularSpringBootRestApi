package com.rti.config;

import com.rti.dto.user.UserDto;
import com.rti.service.auth.JwtService;
import com.rti.service.user.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.GenericFilter;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilter {

    private final JwtService jwtService;
    private final UserService userService;

    private static final String ROLE_PREFIX = "ROLE_";
    private static final String UNAUTHORIZED = "Unauthorized";
    private static final String HEADER = "Authorization";
    private static final String BEARER = "Bearer ";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        final HttpServletRequest httpReq = (HttpServletRequest) request;
        final String authHeader = httpReq.getHeader(HEADER);

        if (authHeader != null && authHeader.startsWith(BEARER)) {
            final String token = authHeader.substring(7);

            if (jwtService.isTokenValid(token)) {
                final String userId = jwtService.extractUserId(token);
                final UserDto user = userService.getUserById(userId);

                if (user != null) {
                    final List<SimpleGrantedAuthority> authorities = List.of(
                            new SimpleGrantedAuthority(ROLE_PREFIX + user.getRole()));

                    final UsernamePasswordAuthenticationToken authToken
                            = new UsernamePasswordAuthenticationToken(user, null, authorities);

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpReq));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                } else {
                    ((jakarta.servlet.http.HttpServletResponse) response).sendError(
                            jakarta.servlet.http.HttpServletResponse.SC_UNAUTHORIZED,
                            UNAUTHORIZED
                    );
                    return;
                }
            } else {
                ((jakarta.servlet.http.HttpServletResponse) response).sendError(
                        jakarta.servlet.http.HttpServletResponse.SC_UNAUTHORIZED,
                        UNAUTHORIZED
                );
                return;
            }
        }

        chain.doFilter(request, response);
    }
}
