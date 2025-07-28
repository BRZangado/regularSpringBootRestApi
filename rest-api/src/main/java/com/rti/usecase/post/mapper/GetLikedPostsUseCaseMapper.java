package com.rti.usecase.post.mapper;

import com.rti.controller.post.response.PostCompleteResponse;
import com.rti.dto.post.PostDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GetLikedPostsUseCaseMapper {
    PostCompleteResponse mappingPostCompleteResponseBy(final PostDto postDto, final int likesCount);
}
