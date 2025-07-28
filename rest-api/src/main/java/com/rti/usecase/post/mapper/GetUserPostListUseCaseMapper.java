package com.rti.usecase.post.mapper;

import com.rti.controller.post.response.PostTinyResponse;
import com.rti.controller.user.response.UserPostsResponse;
import com.rti.controller.user.response.UserTinyResponse;
import com.rti.dto.post.PostDto;
import com.rti.dto.user.UserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GetUserPostListUseCaseMapper {
    UserPostsResponse mappingUserPostsResponseBy(final UserTinyResponse user,
                                                 final List<PostTinyResponse> posts);
    UserTinyResponse mappingUserTinyResponseBy(final UserDto userDto);
    PostTinyResponse mappingPostTinyResponseBy(final PostDto postDto);
}
