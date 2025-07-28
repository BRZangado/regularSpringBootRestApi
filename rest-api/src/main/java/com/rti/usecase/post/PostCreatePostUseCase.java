package com.rti.usecase.post;

import com.rti.controller.post.request.PostCreateRequest;
import com.rti.controller.post.response.PostTinyResponse;
import com.rti.dto.post.PostCreateDto;
import com.rti.dto.post.PostDto;
import com.rti.service.auth.PrincipalService;
import com.rti.service.post.PostService;
import com.rti.usecase.post.mapper.PostCreatePostUseCaseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
@RequiredArgsConstructor
public class PostCreatePostUseCase {

    private final PrincipalService principalService;
    private final PostService postService;
    private final PostCreatePostUseCaseMapper mapper;

    public PostTinyResponse execute(final PostCreateRequest request, final Principal principal){
        final String userId = principalService.getPrincipalId(principal);
        final PostCreateDto postCreateDto = mapper.mappingPostCreateDtoBy(request, userId);
        final PostDto createdPost = postService.createPost(postCreateDto);
        return mapper.mappingPostTinyResponseBy(createdPost);
    }
}
