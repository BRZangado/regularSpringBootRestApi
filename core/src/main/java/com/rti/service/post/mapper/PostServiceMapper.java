package com.rti.service.post.mapper;

import com.rti.dto.post.PostCreateDto;
import com.rti.dto.post.PostDto;
import com.rti.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "Spring")
public interface PostServiceMapper {

    @Mapping(target = "userId", source = "postCreateDto.userId")
    @Mapping(target = "content", source = "postCreateDto.content")
    @Mapping(target = "imageUrl", source = "postCreateDto.imageUrl")
    Post mappingPostEntityBy(final PostCreateDto postCreateDto);
    PostDto mappingPostDtoBy(final Post post);
}
