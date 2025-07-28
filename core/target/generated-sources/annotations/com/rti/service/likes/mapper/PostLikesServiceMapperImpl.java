package com.rti.service.likes.mapper;

import com.rti.dto.postLikes.PostLikesDto;
import com.rti.entity.PostLikes;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-27T22:10:14-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.3 (Amazon.com Inc.)"
)
@Component
public class PostLikesServiceMapperImpl implements PostLikesServiceMapper {

    @Override
    public PostLikes mappingPostLikesBy(String userId, String postId) {
        if ( userId == null && postId == null ) {
            return null;
        }

        PostLikes.PostLikesBuilder postLikes = PostLikes.builder();

        postLikes.userId( userId );
        postLikes.postId( postId );

        return postLikes.build();
    }

    @Override
    public PostLikesDto mappingPostLikesDtoBy(PostLikes postLikes) {
        if ( postLikes == null ) {
            return null;
        }

        PostLikesDto.PostLikesDtoBuilder postLikesDto = PostLikesDto.builder();

        postLikesDto.id( postLikes.getId() );
        postLikesDto.postId( postLikes.getPostId() );
        postLikesDto.userId( postLikes.getUserId() );

        return postLikesDto.build();
    }
}
