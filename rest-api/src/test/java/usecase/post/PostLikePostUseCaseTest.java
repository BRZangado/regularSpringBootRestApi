package usecase.post;

import com.rti.service.auth.PrincipalService;
import com.rti.service.likes.PostLikesService;
import com.rti.usecase.post.PostLikePostUseCase;
import helper.TestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PostLikePostUseCaseTest {

    @InjectMocks
    private PostLikePostUseCase postLikePostUseCase;

    @Mock
    private PostLikesService postLikesService;

    @Mock
    private PrincipalService principalService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute() {
        var postId = TestHelper.mockPostId();
        var principal = TestHelper.mockPrincipal();
        var userId = TestHelper.mockUserId();

        when(principalService.getPrincipalId(principal)).thenReturn(userId);

        postLikePostUseCase.execute(postId, principal);

        verify(principalService, times(1)).getPrincipalId(principal);
        verify(postLikesService, times(1)).likePost(userId, postId);
    }
}
