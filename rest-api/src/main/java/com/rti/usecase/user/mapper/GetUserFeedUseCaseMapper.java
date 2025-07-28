package com.rti.usecase.user.mapper;

import com.rti.controller.post.response.PostCompleteResponse;
import com.rti.controller.user.response.UserFeedResponse;
import com.rti.controller.user.response.UserProfileResponse;
import com.rti.dto.post.PostDto;
import com.rti.dto.user.UserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GetUserFeedUseCaseMapper {
    UserFeedResponse mappingUserFeedResponseBy(final UserProfileResponse user, final List<PostCompleteResponse> posts);
    UserProfileResponse mappingUserProfileResponseBy(final UserDto userDto);
    PostCompleteResponse mappingPostCompleteResponseBy(final PostDto postDto, final int likesCount);
}
