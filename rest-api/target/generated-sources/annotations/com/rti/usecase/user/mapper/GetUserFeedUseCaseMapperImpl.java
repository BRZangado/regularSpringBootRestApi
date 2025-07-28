package com.rti.usecase.user.mapper;

import com.rti.controller.post.response.PostCompleteResponse;
import com.rti.controller.user.response.UserFeedResponse;
import com.rti.controller.user.response.UserProfileResponse;
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
public class GetUserFeedUseCaseMapperImpl implements GetUserFeedUseCaseMapper {

    @Override
    public UserFeedResponse mappingUserFeedResponseBy(UserProfileResponse user, List<PostCompleteResponse> posts) {
        if ( user == null && posts == null ) {
            return null;
        }

        UserFeedResponse.UserFeedResponseBuilder userFeedResponse = UserFeedResponse.builder();

        userFeedResponse.user( user );
        List<PostCompleteResponse> list = posts;
        if ( list != null ) {
            userFeedResponse.posts( new ArrayList<PostCompleteResponse>( list ) );
        }

        return userFeedResponse.build();
    }

    @Override
    public UserProfileResponse mappingUserProfileResponseBy(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        UserProfileResponse.UserProfileResponseBuilder userProfileResponse = UserProfileResponse.builder();

        userProfileResponse.id( userDto.getId() );
        userProfileResponse.name( userDto.getName() );
        userProfileResponse.email( userDto.getEmail() );

        return userProfileResponse.build();
    }

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
