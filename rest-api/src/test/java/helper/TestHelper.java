package helper;

import com.rti.controller.auth.response.AccessResponse;
import com.rti.controller.post.request.PostCreateRequest;
import com.rti.controller.post.response.PostCompleteResponse;
import com.rti.controller.post.response.PostTinyResponse;
import com.rti.controller.user.request.UserCreateRequest;
import com.rti.controller.user.response.UserFeedResponse;
import com.rti.controller.user.response.UserPostsResponse;
import com.rti.controller.user.response.UserProfileResponse;
import com.rti.controller.user.response.UserTinyResponse;
import com.rti.dto.auth.AuthenticationResponseDto;
import com.rti.dto.post.PostCreateDto;
import com.rti.dto.post.PostDto;
import com.rti.dto.postLikes.PostLikesDto;
import com.rti.dto.user.UserCreateDto;
import com.rti.dto.user.UserDto;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import java.security.Principal;
import java.util.List;

import static org.mockito.Mockito.mock;

public class TestHelper {

    public static UserCreateRequest mockUserCreateRequest() {
        EasyRandomParameters parameters = new EasyRandomParameters();
        return new EasyRandom(parameters).nextObject(UserCreateRequest.class);
    }

    public static UserCreateDto mockUserCreateDto() {
        EasyRandomParameters parameters = new EasyRandomParameters();
        return new EasyRandom(parameters).nextObject(UserCreateDto.class);
    }

    public static UserDto mockUserDto() {
        EasyRandomParameters parameters = new EasyRandomParameters();
        return new EasyRandom(parameters).nextObject(UserDto.class);
    }

    public static UserTinyResponse mockUserTinyResponse() {
        EasyRandomParameters parameters = new EasyRandomParameters();
        return new EasyRandom(parameters).nextObject(UserTinyResponse.class);
    }

    public static UserProfileResponse mockUserProfileResponse() {
        EasyRandomParameters parameters = new EasyRandomParameters();
        return new EasyRandom(parameters).nextObject(UserProfileResponse.class);
    }

    public static UserFeedResponse mockUserFeedResponse() {
        EasyRandomParameters parameters = new EasyRandomParameters();
        return new EasyRandom(parameters).nextObject(UserFeedResponse.class);
    }

    public static UserPostsResponse mockUserPostsResponse() {
        EasyRandomParameters parameters = new EasyRandomParameters();
        return new EasyRandom(parameters).nextObject(UserPostsResponse.class);
    }

    // Post related mocks
    public static PostCreateRequest mockPostCreateRequest() {
        EasyRandomParameters parameters = new EasyRandomParameters();
        return new EasyRandom(parameters).nextObject(PostCreateRequest.class);
    }

    public static PostCreateDto mockPostCreateDto() {
        EasyRandomParameters parameters = new EasyRandomParameters();
        return new EasyRandom(parameters).nextObject(PostCreateDto.class);
    }

    public static PostDto mockPostDto() {
        EasyRandomParameters parameters = new EasyRandomParameters();
        return new EasyRandom(parameters).nextObject(PostDto.class);
    }

    public static PostTinyResponse mockPostTinyResponse() {
        EasyRandomParameters parameters = new EasyRandomParameters();
        return new EasyRandom(parameters).nextObject(PostTinyResponse.class);
    }

    public static PostCompleteResponse mockPostCompleteResponse() {
        EasyRandomParameters parameters = new EasyRandomParameters();
        return new EasyRandom(parameters).nextObject(PostCompleteResponse.class);
    }

    // Post Likes related mocks
    public static PostLikesDto mockPostLikesDto() {
        EasyRandomParameters parameters = new EasyRandomParameters();
        return new EasyRandom(parameters).nextObject(PostLikesDto.class);
    }

    // Auth related mocks
    public static AuthenticationResponseDto mockAuthenticationResponseDto() {
        EasyRandomParameters parameters = new EasyRandomParameters();
        return new EasyRandom(parameters).nextObject(AuthenticationResponseDto.class);
    }

    public static AccessResponse mockAccessResponse() {
        EasyRandomParameters parameters = new EasyRandomParameters();
        return new EasyRandom(parameters).nextObject(AccessResponse.class);
    }

    // Principal mock
    public static Principal mockPrincipal() {
        return mock(Principal.class);
    }

    // Lists
    public static List<UserDto> mockUserDtoList() {
        return List.of(mockUserDto());
    }

    public static List<UserTinyResponse> mockUserTinyResponseList() {
        return List.of(mockUserTinyResponse());
    }

    public static List<PostDto> mockPostDtoList() {
        return List.of(mockPostDto());
    }

    public static List<PostTinyResponse> mockPostTinyResponseList() {
        return List.of(mockPostTinyResponse());
    }

    public static List<PostCompleteResponse> mockPostCompleteResponseList() {
        return List.of(mockPostCompleteResponse());
    }

    public static List<PostLikesDto> mockPostLikesDtoList() {
        return List.of(mockPostLikesDto());
    }

    // Constants
    public static String mockUserId() {
        return "user-id-123";
    }

    public static String mockPostId() {
        return "post-id-123";
    }

    public static String mockEmail() {
        return "test@example.com";
    }

    public static String mockPassword() {
        return "password123";
    }

    public static String mockRefreshToken() {
        return "refresh-token-123";
    }

    public static int mockLikesCount() {
        return 5;
    }
}
