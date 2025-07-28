package com.rti.usecase.post;

import com.rti.controller.post.response.PostTinyResponse;
import com.rti.controller.user.response.UserPostsResponse;
import com.rti.controller.user.response.UserTinyResponse;
import com.rti.dto.post.PostDto;
import com.rti.dto.user.UserDto;
import com.rti.service.auth.PrincipalService;
import com.rti.service.post.PostService;
import com.rti.service.user.UserService;
import com.rti.usecase.post.mapper.GetUserPostListUseCaseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GetUserPostListUseCase {

    private final UserService userService;
    private final PostService postService;
    private final PrincipalService principalService;
    private final GetUserPostListUseCaseMapper mapper;

    public UserPostsResponse execute(final Principal principal){
        final String userId = principalService.getPrincipalId(principal);
        final UserDto user = userService.getUserById(userId);
        final List<PostDto> posts = postService.getAllPostsByUserId(userId);

        final UserTinyResponse userTinyResponse = mapper.mappingUserTinyResponseBy(user);
        final List<PostTinyResponse> postTinyResponses
                = posts.stream().map(mapper::mappingPostTinyResponseBy).toList();

        return mapper.mappingUserPostsResponseBy(userTinyResponse, postTinyResponses);
    }
}
