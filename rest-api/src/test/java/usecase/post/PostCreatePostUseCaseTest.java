package usecase.post;

import com.rti.service.auth.PrincipalService;
import com.rti.service.post.PostService;
import com.rti.usecase.post.PostCreatePostUseCase;
import com.rti.usecase.post.mapper.PostCreatePostUseCaseMapper;
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

public class PostCreatePostUseCaseTest {

    @InjectMocks
    private PostCreatePostUseCase postCreatePostUseCase;

    @Mock
    private PrincipalService principalService;

    @Mock
    private PostService postService;

    @Mock
    private PostCreatePostUseCaseMapper mapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute() {
        var request = TestHelper.mockPostCreateRequest();
        var principal = TestHelper.mockPrincipal();
        var userId = TestHelper.mockUserId();
        var postCreateDto = TestHelper.mockPostCreateDto();
        var createdPost = TestHelper.mockPostDto();
        var response = TestHelper.mockPostTinyResponse();

        when(principalService.getPrincipalId(principal)).thenReturn(userId);
        when(mapper.mappingPostCreateDtoBy(request, userId)).thenReturn(postCreateDto);
        when(postService.createPost(postCreateDto)).thenReturn(createdPost);
        when(mapper.mappingPostTinyResponseBy(createdPost)).thenReturn(response);

        var result = postCreatePostUseCase.execute(request, principal);

        Assertions.assertNotNull(result);
        verify(principalService, times(1)).getPrincipalId(principal);
        verify(mapper, times(1)).mappingPostCreateDtoBy(request, userId);
        verify(postService, times(1)).createPost(postCreateDto);
        verify(mapper, times(1)).mappingPostTinyResponseBy(createdPost);
    }
}
