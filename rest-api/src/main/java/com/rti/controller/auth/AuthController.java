package com.rti.controller.auth;

import com.rti.controller.auth.response.AccessResponse;
import com.rti.usecase.auth.PostAccessUseCase;
import com.rti.usecase.auth.PostRefreshTokenUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
@Validated
public class AuthController {

    private final PostAccessUseCase postAccessUseCase;
    private final PostRefreshTokenUseCase postRefreshTokenUseCase;

    @PostMapping()
    public ResponseEntity<AccessResponse> postAccess(@RequestPart(value = "username") final String username,
                                                     @RequestPart(value = "password") final String password){

        return ResponseEntity.ok(postAccessUseCase.execute(username, password));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AccessResponse> postRefresh(@RequestPart(value = "refresh_token") final String refreshToken) {
        return ResponseEntity.ok(postRefreshTokenUseCase.execute(refreshToken));
    }
}
