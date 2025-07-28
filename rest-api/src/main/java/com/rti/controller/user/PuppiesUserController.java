package com.rti.controller.user;

import com.rti.controller.user.request.UserCreateRequest;
import com.rti.controller.user.response.UserFeedResponse;
import com.rti.controller.user.response.UserProfileResponse;
import com.rti.controller.user.response.UserTinyResponse;
import com.rti.usecase.user.GetUserFeedUseCase;
import com.rti.usecase.user.GetUserListUseCase;
import com.rti.usecase.user.GetUserProfileUseCase;
import com.rti.usecase.user.PostCreateUserUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/user")
@Validated
public class PuppiesUserController {

    private final PostCreateUserUseCase postCreateUserUseCase;
    private final GetUserListUseCase getUserListUseCase;
    private final GetUserProfileUseCase getUserProfileUseCase;
    private final GetUserFeedUseCase getUserFeedUseCase;

    @PostMapping()
    public ResponseEntity<UserTinyResponse> postCreateUser(@Valid @RequestBody final UserCreateRequest request) {
        return ResponseEntity.ok(postCreateUserUseCase.execute(request));
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserTinyResponse>> getUserList(){
        return ResponseEntity.ok(getUserListUseCase.execute());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserProfileResponse> getUserProfile(@PathVariable final String userId){
        return ResponseEntity.ok(getUserProfileUseCase.execute(userId));
    }

    @GetMapping("/{userId}/feed")
    public ResponseEntity<UserFeedResponse> getUserFeed(@PathVariable final String userId){
        return ResponseEntity.ok(getUserFeedUseCase.execute(userId));
    }
}
