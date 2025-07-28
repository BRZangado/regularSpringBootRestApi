package com.rti.service.post.mapper;

import com.rti.dto.post.PostCreateDto;
import com.rti.dto.post.PostDto;
import com.rti.entity.Post;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-27T22:10:14-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.3 (Amazon.com Inc.)"
)
@Component
public class PostServiceMapperImpl implements PostServiceMapper {

    @Override
    public Post mappingPostEntityBy(PostCreateDto postCreateDto) {
        if ( postCreateDto == null ) {
            return null;
        }

        Post.PostBuilder post = Post.builder();

        post.userId( postCreateDto.getUserId() );
        post.content( postCreateDto.getContent() );
        post.imageUrl( postCreateDto.getImageUrl() );

        return post.build();
    }

    @Override
    public PostDto mappingPostDtoBy(Post post) {
        if ( post == null ) {
            return null;
        }

        PostDto.PostDtoBuilder postDto = PostDto.builder();

        postDto.id( post.getId() );
        postDto.userId( post.getUserId() );
        postDto.content( post.getContent() );
        postDto.imageUrl( post.getImageUrl() );
        postDto.createdAt( post.getCreatedAt() );

        return postDto.build();
    }
}
