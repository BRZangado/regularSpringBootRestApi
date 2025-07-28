package com.rti.usecase.post;

import com.rti.controller.post.response.PostCompleteResponse;
import com.rti.dto.post.PostDto;
import com.rti.dto.postLikes.PostLikesDto;
import com.rti.service.auth.PrincipalService;
import com.rti.service.likes.PostLikesService;
import com.rti.service.post.PostService;
import com.rti.usecase.post.mapper.GetLikedPostsUseCaseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class GetLikedPostsUseCase {

    private final PrincipalService principalService;
    private final PostService postService;
    private final PostLikesService postLikesService;
    private final GetLikedPostsUseCaseMapper mapper;

    public List<PostCompleteResponse> execute(final Principal principal){
        final String userId = principalService.getPrincipalId(principal);
        final List<PostLikesDto> postLikesDtos = postLikesService.getUserLikedPosts(userId);
        final List<PostDto> postDtos = postLikesDtos.stream()
                .map(postLike -> postService.getPostById(postLike.getPostId()))
                .filter(Objects::nonNull)
                .toList();

        return postDtos.stream()
                .map(postDto -> {
                    final int likesCount = postLikesService.getPostLikesCount(postDto.getId());
                    return mapper.mappingPostCompleteResponseBy(postDto, likesCount);
                })
                .toList();
    }
}
