package com.rti.service.post;

import com.rti.dto.post.PostCreateDto;
import com.rti.dto.post.PostDto;
import com.rti.entity.Post;
import com.rti.exception.EntityPersistException;
import com.rti.exception.ResourceNotFoundException;
import com.rti.repository.PostRepository;
import com.rti.service.post.mapper.PostServiceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final PostServiceMapper mapper;

    public PostDto createPost(final PostCreateDto postCreateDto) {

        try {
            final Post post = mapper.mappingPostEntityBy(postCreateDto);
            final Post savedEntity = postRepository.save(post);
            return mapper.mappingPostDtoBy(savedEntity);
        } catch (Exception e) {
            log.error("POST SAVING ERROR -> \n {}", e.getMessage());
            throw new EntityPersistException("ERROR WHILE SAVING POST, CHECK INPUT DATA");
        }
    }

    public List<PostDto> getAllPostsByUserId(final String userId) {
        final List<Post> posts = postRepository.findByUserIdOrderByCreatedAtDesc(userId);
        return posts.stream()
                .map(mapper::mappingPostDtoBy)
                .toList();
    }

    public PostDto getPostById(final String postId) {
        final Post post = getPostEntityById(postId);
        return mapper.mappingPostDtoBy(post);
    }

    private Post getPostEntityById(final String postId) {
        final Optional<Post> post = postRepository.findPostById(postId);
        if (post.isEmpty()) {
            throw new ResourceNotFoundException("POST NOT FOUND FOR GIVEN ID");
        }
        return post.get();
    }
}
