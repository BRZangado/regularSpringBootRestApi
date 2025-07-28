package com.rti.usecase.post.mapper;

import com.rti.controller.post.request.PostCreateRequest;
import com.rti.controller.post.response.PostTinyResponse;
import com.rti.dto.post.PostCreateDto;
import com.rti.dto.post.PostDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostCreatePostUseCaseMapper {
    PostTinyResponse mappingPostTinyResponseBy(final PostDto postDto);
    PostCreateDto mappingPostCreateDtoBy(final PostCreateRequest request, final String userId);
}
