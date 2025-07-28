package com.rti.usecase.post;

import com.rti.service.auth.PrincipalService;
import com.rti.service.likes.PostLikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
@RequiredArgsConstructor
public class PostLikePostUseCase {

    private final PostLikesService postLikesService;
    private final PrincipalService principalService;

    public void execute(final String postId, final Principal principal){
        final String userId = principalService.getPrincipalId(principal);
        postLikesService.likePost(userId, postId);
    }
}
