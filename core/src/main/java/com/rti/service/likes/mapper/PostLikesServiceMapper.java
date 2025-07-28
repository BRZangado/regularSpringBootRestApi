package com.rti.service.likes.mapper;

import com.rti.dto.postLikes.PostLikesDto;
import com.rti.entity.PostLikes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostLikesServiceMapper {
    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "postId", source = "postId")
    PostLikes mappingPostLikesBy(final String userId, final String postId);

    PostLikesDto mappingPostLikesDtoBy(final PostLikes postLikes);
}
