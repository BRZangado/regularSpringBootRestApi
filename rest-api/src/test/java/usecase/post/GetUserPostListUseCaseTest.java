package usecase.post;

import com.rti.service.auth.PrincipalService;
import com.rti.service.post.PostService;
import com.rti.service.user.UserService;
import com.rti.usecase.post.GetUserPostListUseCase;
import com.rti.usecase.post.mapper.GetUserPostListUseCaseMapper;
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

public class GetUserPostListUseCaseTest {

    @InjectMocks
    private GetUserPostListUseCase getUserPostListUseCase;

    @Mock
    private UserService userService;

    @Mock
    private PostService postService;

    @Mock
    private PrincipalService principalService;

    @Mock
    private GetUserPostListUseCaseMapper mapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute() {
        var principal = TestHelper.mockPrincipal();
        var userId = TestHelper.mockUserId();
        var user = TestHelper.mockUserDto();
        var posts = TestHelper.mockPostDtoList();
        var userTinyResponse = TestHelper.mockUserTinyResponse();
        var postTinyResponse = TestHelper.mockPostTinyResponse();
        var postTinyResponses = TestHelper.mockPostTinyResponseList();
        var response = TestHelper.mockUserPostsResponse();

        when(principalService.getPrincipalId(principal)).thenReturn(userId);
        when(userService.getUserById(userId)).thenReturn(user);
        when(postService.getAllPostsByUserId(userId)).thenReturn(posts);
        when(mapper.mappingUserTinyResponseBy(user)).thenReturn(userTinyResponse);
        when(mapper.mappingPostTinyResponseBy(posts.get(0))).thenReturn(postTinyResponse);
        when(mapper.mappingUserPostsResponseBy(userTinyResponse, postTinyResponses)).thenReturn(response);

        var result = getUserPostListUseCase.execute(principal);

        Assertions.assertNotNull(result);
        verify(principalService, times(1)).getPrincipalId(principal);
        verify(userService, times(1)).getUserById(userId);
        verify(postService, times(1)).getAllPostsByUserId(userId);
        verify(mapper, times(1)).mappingUserTinyResponseBy(user);
    }
}
