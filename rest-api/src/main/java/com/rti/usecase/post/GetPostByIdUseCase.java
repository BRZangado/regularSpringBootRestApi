package com.rti.usecase.post;

import com.rti.controller.post.response.PostCompleteResponse;
import com.rti.dto.post.PostDto;
import com.rti.service.likes.PostLikesService;
import com.rti.service.post.PostService;
import com.rti.usecase.post.mapper.GetPostByIdUseCaseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetPostByIdUseCase {

    private final PostService postService;
    private final PostLikesService postLikesService;
    private final GetPostByIdUseCaseMapper mapper;

    public PostCompleteResponse execute(final String postId){
        final PostDto postDto = postService.getPostById(postId);
        final int likesCount = postLikesService.getPostLikesCount(postDto.getId());
        return mapper.mappingPostCompleteResponseBy(postDto, likesCount);
    }
}
