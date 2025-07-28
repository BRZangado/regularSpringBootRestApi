package helper;

import com.rti.dto.auth.AuthenticationResponseDto;
import com.rti.dto.post.PostCreateDto;
import com.rti.dto.post.PostDto;
import com.rti.dto.postLikes.PostLikesDto;
import com.rti.dto.user.UserCreateDto;
import com.rti.dto.user.UserDto;
import com.rti.entity.Post;
import com.rti.entity.PostLikes;
import com.rti.entity.User;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;

public class TestHelper {

    // Entity mocks
    public static User mockUserEntity() {
        EasyRandomParameters parameters = new EasyRandomParameters();
        return new EasyRandom(parameters).nextObject(User.class);
    }

    public static Post mockPostEntity() {
        EasyRandomParameters parameters = new EasyRandomParameters();
        return new EasyRandom(parameters).nextObject(Post.class);
    }

    public static PostLikes mockPostLikesEntity() {
        EasyRandomParameters parameters = new EasyRandomParameters();
        return new EasyRandom(parameters).nextObject(PostLikes.class);
    }

    // Optional mocks
    public static Optional<User> mockOptionalUserEntity() {
        return Optional.of(mockUserEntity());
    }

    public static Optional<User> mockEmptyOptionalUser() {
        return Optional.empty();
    }

    public static Optional<Post> mockOptionalPostEntity() {
        return Optional.of(mockPostEntity());
    }

    public static Optional<Post> mockEmptyOptionalPost() {
        return Optional.empty();
    }

    public static Optional<PostLikes> mockOptionalPostLikesEntity() {
        return Optional.of(mockPostLikesEntity());
    }

    public static Optional<PostLikes> mockEmptyOptionalPostLikes() {
        return Optional.empty();
    }

    public static UserCreateDto mockUserCreateDto() {
        EasyRandomParameters parameters = new EasyRandomParameters();
        return new EasyRandom(parameters).nextObject(UserCreateDto.class);
    }

    public static UserDto mockUserDto() {
        EasyRandomParameters parameters = new EasyRandomParameters();
        return new EasyRandom(parameters).nextObject(UserDto.class);
    }

    public static PostCreateDto mockPostCreateDto() {
        EasyRandomParameters parameters = new EasyRandomParameters();
        return new EasyRandom(parameters).nextObject(PostCreateDto.class);
    }

    public static PostDto mockPostDto() {
        EasyRandomParameters parameters = new EasyRandomParameters();
        return new EasyRandom(parameters).nextObject(PostDto.class);
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

    // Principal mock
    public static Principal mockPrincipal() {
        return mock(Principal.class);
    }

    // Lists
    public static List<User> mockUserEntityList() {
        return List.of(mockUserEntity());
    }

    public static List<UserDto> mockUserDtoList() {
        return List.of(mockUserDto());
    }

    public static List<Post> mockPostEntityList() {
        return List.of(mockPostEntity());
    }

    public static List<PostDto> mockPostDtoList() {
        return List.of(mockPostDto());
    }

    public static List<PostLikes> mockPostLikesEntityList() {
        return List.of(mockPostLikesEntity());
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

    public static String mockLikeId() {
        return "like-id-123";
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

    public static String mockAccessToken() {
        return "access-token-123";
    }

    public static String mockEncodedPassword() {
        return "encodedPassword";
    }

    public static String mockJwtToken() {
        return "jwt-token-123";
    }

    public static int mockLikesCount() {
        return 5;
    }

    public static LocalDateTime mockDateTime() {
        return LocalDateTime.now();
    }

    public static RuntimeException mockRuntimeException() {
        return new RuntimeException("Unexpected error");
    }
}
