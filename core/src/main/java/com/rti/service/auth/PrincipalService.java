package com.rti.service.auth;

import com.rti.dto.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class PrincipalService {

    public String getPrincipalId(final Principal principal){
        return ((UserDto) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getId();
    }
}

