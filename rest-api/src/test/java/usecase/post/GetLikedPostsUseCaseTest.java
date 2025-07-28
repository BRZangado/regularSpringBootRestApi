package usecase.post;

import com.rti.service.auth.PrincipalService;
import com.rti.service.likes.PostLikesService;
import com.rti.service.post.PostService;
import com.rti.usecase.post.GetLikedPostsUseCase;
import com.rti.usecase.post.mapper.GetLikedPostsUseCaseMapper;
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

public class GetLikedPostsUseCaseTest {

    @InjectMocks
    private GetLikedPostsUseCase getLikedPostsUseCase;

    @Mock
    private PrincipalService principalService;

    @Mock
    private PostService postService;

    @Mock
    private PostLikesService postLikesService;

    @Mock
    private GetLikedPostsUseCaseMapper mapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute() {
        var principal = TestHelper.mockPrincipal();
        var userId = TestHelper.mockUserId();
        var postLikesDtos = TestHelper.mockPostLikesDtoList();
        var postDto = TestHelper.mockPostDto();
        var likesCount = TestHelper.mockLikesCount();
        var postCompleteResponse = TestHelper.mockPostCompleteResponse();
        var response = TestHelper.mockPostCompleteResponseList();

        when(principalService.getPrincipalId(principal)).thenReturn(userId);
        when(postLikesService.getUserLikedPosts(userId)).thenReturn(postLikesDtos);
        when(postService.getPostById(postLikesDtos.get(0).getPostId())).thenReturn(postDto);
        when(postLikesService.getPostLikesCount(postDto.getId())).thenReturn(likesCount);
        when(mapper.mappingPostCompleteResponseBy(postDto, likesCount)).thenReturn(postCompleteResponse);

        var result = getLikedPostsUseCase.execute(principal);

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        verify(principalService, times(1)).getPrincipalId(principal);
        verify(postLikesService, times(1)).getUserLikedPosts(userId);
        verify(postService, times(1)).getPostById(postLikesDtos.get(0).getPostId());
        verify(postLikesService, times(1)).getPostLikesCount(postDto.getId());
        verify(mapper, times(1)).mappingPostCompleteResponseBy(postDto, likesCount);
    }
}