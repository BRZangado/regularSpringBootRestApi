package com.rti.usecase.post.mapper;

import com.rti.controller.post.response.PostCompleteResponse;
import com.rti.dto.post.PostDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-27T22:10:17-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.3 (Amazon.com Inc.)"
)
@Component
public class GetPostByIdUseCaseMapperImpl implements GetPostByIdUseCaseMapper {

    @Override
    public PostCompleteResponse mappingPostCompleteResponseBy(PostDto postDto, int likesCount) {
        if ( postDto == null ) {
            return null;
        }

        PostCompleteResponse.PostCompleteResponseBuilder postCompleteResponse = PostCompleteResponse.builder();

        if ( postDto != null ) {
            postCompleteResponse.id( postDto.getId() );
            postCompleteResponse.content( postDto.getContent() );
            postCompleteResponse.imageUrl( postDto.getImageUrl() );
            postCompleteResponse.createdAt( postDto.getCreatedAt() );
        }
        postCompleteResponse.likesCount( likesCount );

        return postCompleteResponse.build();
    }
}
