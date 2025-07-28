package com.rti.usecase.user;

import com.rti.controller.post.response.PostCompleteResponse;
import com.rti.controller.user.response.UserFeedResponse;
import com.rti.controller.user.response.UserProfileResponse;
import com.rti.dto.post.PostDto;
import com.rti.dto.user.UserDto;
import com.rti.service.likes.PostLikesService;
import com.rti.service.post.PostService;
import com.rti.service.user.UserService;
import com.rti.usecase.user.mapper.GetUserFeedUseCaseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetUserFeedUseCase {

    private final UserService userService;
    private final PostService postService;
    private final PostLikesService postLikesService;
    private final GetUserFeedUseCaseMapper mapper;

    public UserFeedResponse execute(final String userId){
        final UserDto user = userService.getUserById(userId);
        final List<PostDto> posts = postService.getAllPostsByUserId(userId);

        final UserProfileResponse userProfileResponse = mapper.mappingUserProfileResponseBy(user);
        final List<PostCompleteResponse> postCompleteResponses = posts.stream().map(postDto -> {
                    final int likesCount = postLikesService.getPostLikesCount(postDto.getId());
                    return mapper.mappingPostCompleteResponseBy(postDto, likesCount);
                })
                .toList();

        return mapper.mappingUserFeedResponseBy(userProfileResponse, postCompleteResponses);
    }
}
