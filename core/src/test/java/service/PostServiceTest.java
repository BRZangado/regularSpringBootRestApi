package service;

import com.rti.exception.EntityPersistException;
import com.rti.exception.ResourceNotFoundException;
import com.rti.repository.PostRepository;
import com.rti.service.post.PostService;
import com.rti.service.post.mapper.PostServiceMapper;
import helper.TestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

public class PostServiceTest {

    @InjectMocks
    private PostService postService;

    @Mock
    private PostRepository postRepository;

    @Mock
    private PostServiceMapper postServiceMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreatePost_Success() {
        var postCreateDto = TestHelper.mockPostCreateDto();
        var postEntity = TestHelper.mockPostEntity();
        var savedPostEntity = TestHelper.mockPostEntity();
        var expectedDto = TestHelper.mockPostDto();

        when(postServiceMapper.mappingPostEntityBy(postCreateDto)).thenReturn(postEntity);
        when(postRepository.save(postEntity)).thenReturn(savedPostEntity);
        when(postServiceMapper.mappingPostDtoBy(savedPostEntity)).thenReturn(expectedDto);

        var result = postService.createPost(postCreateDto);

        Assertions.assertNotNull(result);
        verify(postServiceMapper, times(1)).mappingPostEntityBy(postCreateDto);
        verify(postRepository, times(1)).save(postEntity);
        verify(postServiceMapper, times(1)).mappingPostDtoBy(savedPostEntity);
    }

    @Test
    public void testCreatePost_ThrowsEntityPersistException() {
        var postCreateDto = TestHelper.mockPostCreateDto();

        when(postServiceMapper.mappingPostEntityBy(postCreateDto))
                .thenThrow(TestHelper.mockRuntimeException());

        var exception = Assertions.assertThrows(
                EntityPersistException.class,
                () -> postService.createPost(postCreateDto)
        );

        Assertions.assertEquals("ERROR WHILE SAVING POST, CHECK INPUT DATA", exception.getMessage());
        verify(postServiceMapper, times(1)).mappingPostEntityBy(postCreateDto);
        verifyNoInteractions(postRepository);
    }

    @Test
    public void testGetAllPostsByUserId_Success() {
        var userId = TestHelper.mockUserId();
        var postEntities = TestHelper.mockPostEntityList();
        var postDto = TestHelper.mockPostDto();

        when(postRepository.findByUserIdOrderByCreatedAtDesc(userId)).thenReturn(postEntities);
        when(postServiceMapper.mappingPostDtoBy(postEntities.get(0))).thenReturn(postDto);

        var result = postService.getAllPostsByUserId(userId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        verify(postRepository, times(1)).findByUserIdOrderByCreatedAtDesc(userId);
        verify(postServiceMapper, times(1)).mappingPostDtoBy(postEntities.get(0));
    }

    @Test
    public void testGetPostById_Success() {
        var postId = TestHelper.mockPostId();
        var postEntity = TestHelper.mockOptionalPostEntity();
        var postDto = TestHelper.mockPostDto();

        when(postRepository.findPostById(postId)).thenReturn(postEntity);
        when(postServiceMapper.mappingPostDtoBy(postEntity.get())).thenReturn(postDto);

        var result = postService.getPostById(postId);

        Assertions.assertNotNull(result);
        verify(postRepository, times(1)).findPostById(postId);
        verify(postServiceMapper, times(1)).mappingPostDtoBy(postEntity.get());
    }

    @Test
    public void testGetPostById_ThrowsResourceNotFoundException() {
        var postId = "non-existent-id";

        when(postRepository.findPostById(postId)).thenReturn(TestHelper.mockEmptyOptionalPost());

        var exception = Assertions.assertThrows(
                ResourceNotFoundException.class,
                () -> postService.getPostById(postId)
        );

        Assertions.assertEquals("POST NOT FOUND FOR GIVEN ID", exception.getMessage());
        verify(postRepository, times(1)).findPostById(postId);
        verifyNoInteractions(postServiceMapper);
    }
}
