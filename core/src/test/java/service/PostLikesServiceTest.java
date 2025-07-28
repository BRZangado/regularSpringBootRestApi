package service;

import com.rti.repository.PostLikesRepository;
import com.rti.service.likes.PostLikesService;
import com.rti.service.likes.mapper.PostLikesServiceMapper;
import helper.TestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

public class PostLikesServiceTest {

    @InjectMocks
    private PostLikesService postLikesService;

    @Mock
    private PostLikesRepository postLikesRepository;

    @Mock
    private PostLikesServiceMapper postLikesServiceMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLikePost_Success() {
        var userId = TestHelper.mockUserId();
        var postId = TestHelper.mockPostId();
        var postLikeEntity = TestHelper.mockPostLikesEntity();

        when(postLikesRepository.findByUserIdAndPostId(userId, postId))
                .thenReturn(TestHelper.mockEmptyOptionalPostLikes());
        when(postLikesServiceMapper.mappingPostLikesBy(userId, postId)).thenReturn(postLikeEntity);

        postLikesService.likePost(userId, postId);

        verify(postLikesRepository, times(1)).findByUserIdAndPostId(userId, postId);
        verify(postLikesServiceMapper, times(1)).mappingPostLikesBy(userId, postId);
        verify(postLikesRepository, times(1)).save(postLikeEntity);
    }

    @Test
    public void testLikePost_UserAlreadyLikedPost() {
        var userId = TestHelper.mockUserId();
        var postId = TestHelper.mockPostId();

        when(postLikesRepository.findByUserIdAndPostId(userId, postId))
                .thenReturn(TestHelper.mockOptionalPostLikesEntity());

        postLikesService.likePost(userId, postId);

        verify(postLikesRepository, times(1)).findByUserIdAndPostId(userId, postId);
        verifyNoInteractions(postLikesServiceMapper);
        verify(postLikesRepository, times(0)).save(TestHelper.mockPostLikesEntity());
    }

    @Test
    public void testGetUserLikedPosts_Success() {
        var userId = TestHelper.mockUserId();
        var postLikesEntities = TestHelper.mockPostLikesEntityList();
        var postLikesDto = TestHelper.mockPostLikesDto();

        when(postLikesRepository.findByUserIdOrderByCreatedAtDesc(userId)).thenReturn(postLikesEntities);
        when(postLikesServiceMapper.mappingPostLikesDtoBy(postLikesEntities.get(0))).thenReturn(postLikesDto);

        var result = postLikesService.getUserLikedPosts(userId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        verify(postLikesRepository, times(1)).findByUserIdOrderByCreatedAtDesc(userId);
        verify(postLikesServiceMapper, times(1)).mappingPostLikesDtoBy(postLikesEntities.get(0));
    }

    @Test
    public void testGetUserLikedPosts_EmptyList() {
        var userId = TestHelper.mockUserId();

        when(postLikesRepository.findByUserIdOrderByCreatedAtDesc(userId)).thenReturn(Collections.emptyList());

        var result = postLikesService.getUserLikedPosts(userId);

        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.isEmpty());
        verify(postLikesRepository, times(1)).findByUserIdOrderByCreatedAtDesc(userId);
        verifyNoInteractions(postLikesServiceMapper);
    }

    @Test
    public void testGetPostLikesCount_Success() {
        var postId = TestHelper.mockPostId();
        var likesCount = TestHelper.mockLikesCount();

        when(postLikesRepository.likesCountByPostId(postId)).thenReturn(likesCount);

        var result = postLikesService.getPostLikesCount(postId);

        Assertions.assertEquals(likesCount, result);
        verify(postLikesRepository, times(1)).likesCountByPostId(postId);
    }
}
