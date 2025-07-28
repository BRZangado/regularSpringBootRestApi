package com.rti.usecase.post.mapper;

import com.rti.controller.post.request.PostCreateRequest;
import com.rti.controller.post.response.PostTinyResponse;
import com.rti.dto.post.PostCreateDto;
import com.rti.dto.post.PostDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-27T22:10:17-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.3 (Amazon.com Inc.)"
)
@Component
public class PostCreatePostUseCaseMapperImpl implements PostCreatePostUseCaseMapper {

    @Override
    public PostTinyResponse mappingPostTinyResponseBy(PostDto postDto) {
        if ( postDto == null ) {
            return null;
        }

        PostTinyResponse.PostTinyResponseBuilder postTinyResponse = PostTinyResponse.builder();

        postTinyResponse.id( postDto.getId() );

        return postTinyResponse.build();
    }

    @Override
    public PostCreateDto mappingPostCreateDtoBy(PostCreateRequest request, String userId) {
        if ( request == null && userId == null ) {
            return null;
        }

        PostCreateDto.PostCreateDtoBuilder postCreateDto = PostCreateDto.builder();

        if ( request != null ) {
            postCreateDto.content( request.getContent() );
            postCreateDto.imageUrl( request.getImageUrl() );
        }
        postCreateDto.userId( userId );

        return postCreateDto.build();
    }
}
