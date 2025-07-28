package usecase.user;

import com.rti.service.likes.PostLikesService;
import com.rti.service.post.PostService;
import com.rti.service.user.UserService;
import com.rti.usecase.user.GetUserFeedUseCase;
import com.rti.usecase.user.mapper.GetUserFeedUseCaseMapper;
import helper.TestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetUserFeedUseCaseTest {

    @InjectMocks
    private GetUserFeedUseCase getUserFeedUseCase;

    @Mock
    private UserService userService;

    @Mock
    private PostService postService;

    @Mock
    private PostLikesService postLikesService;

    @Mock
    private GetUserFeedUseCaseMapper mapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute() {
        var userId = TestHelper.mockUserId();
        var user = TestHelper.mockUserDto();
        var posts = TestHelper.mockPostDtoList();
        var postDto = TestHelper.mockPostDto();
        var likesCount = TestHelper.mockLikesCount();
        var userProfileResponse = TestHelper.mockUserProfileResponse();
        var postCompleteResponse = TestHelper.mockPostCompleteResponse();
        var postCompleteResponses = TestHelper.mockPostCompleteResponseList();
        var response = TestHelper.mockUserFeedResponse();

        when(userService.getUserById(userId)).thenReturn(user);
        when(postService.getAllPostsByUserId(userId)).thenReturn(posts);
        when(mapper.mappingUserProfileResponseBy(user)).thenReturn(userProfileResponse);
        when(postLikesService.getPostLikesCount(postDto.getId())).thenReturn(likesCount);
        when(mapper.mappingPostCompleteResponseBy(postDto, likesCount)).thenReturn(postCompleteResponse);
        when(mapper.mappingUserFeedResponseBy(userProfileResponse, postCompleteResponses)).thenReturn(response);

        var result = getUserFeedUseCase.execute(userId);

        Assertions.assertNotNull(result);
        verify(userService, times(1)).getUserById(userId);
        verify(postService, times(1)).getAllPostsByUserId(userId);
        verify(mapper, times(1)).mappingUserProfileResponseBy(user);
        verify(postLikesService, times(1)).getPostLikesCount(postDto.getId());
        verify(mapper, times(1)).mappingPostCompleteResponseBy(postDto, likesCount);
    }
}
