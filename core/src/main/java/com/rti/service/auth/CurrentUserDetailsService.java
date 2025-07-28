package com.rti.service.auth;

import com.rti.enums.Role;
import com.rti.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrentUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        final com.rti.entity.User manager = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("USER NOT FOUND"));

        return User.builder()
                .username(manager.getEmail())
                .password(manager.getPassword())
                .roles(Role.USER.name())
                .build();
    }
}
