package com.rti.usecase.post.mapper;

import com.rti.controller.post.response.PostTinyResponse;
import com.rti.controller.user.response.UserPostsResponse;
import com.rti.controller.user.response.UserTinyResponse;
import com.rti.dto.post.PostDto;
import com.rti.dto.user.UserDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-27T22:10:17-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.3 (Amazon.com Inc.)"
)
@Component
public class GetUserPostListUseCaseMapperImpl implements GetUserPostListUseCaseMapper {

    @Override
    public UserPostsResponse mappingUserPostsResponseBy(UserTinyResponse user, List<PostTinyResponse> posts) {
        if ( user == null && posts == null ) {
            return null;
        }

        UserPostsResponse.UserPostsResponseBuilder userPostsResponse = UserPostsResponse.builder();

        userPostsResponse.user( user );
        List<PostTinyResponse> list = posts;
        if ( list != null ) {
            userPostsResponse.posts( new ArrayList<PostTinyResponse>( list ) );
        }

        return userPostsResponse.build();
    }

    @Override
    public UserTinyResponse mappingUserTinyResponseBy(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        UserTinyResponse.UserTinyResponseBuilder userTinyResponse = UserTinyResponse.builder();

        userTinyResponse.id( userDto.getId() );

        return userTinyResponse.build();
    }

    @Override
    public PostTinyResponse mappingPostTinyResponseBy(PostDto postDto) {
        if ( postDto == null ) {
            return null;
        }

        PostTinyResponse.PostTinyResponseBuilder postTinyResponse = PostTinyResponse.builder();

        postTinyResponse.id( postDto.getId() );

        return postTinyResponse.build();
    }
}
