package com.rti.service.likes;

import com.rti.dto.postLikes.PostLikesDto;
import com.rti.entity.PostLikes;
import com.rti.exception.EntityPersistException;
import com.rti.repository.PostLikesRepository;
import com.rti.service.likes.mapper.PostLikesServiceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostLikesService {

    private final PostLikesRepository postLikesRepository;
    private final PostLikesServiceMapper mapper;

    public void likePost(final String userId, final String postId) {

        final Optional<PostLikes> existingLike = postLikesRepository.findByUserIdAndPostId(userId, postId);
        if (existingLike.isPresent()) {
            log.warn("User {} already liked post {}", userId, postId);
            return;
        }

        try {
            final PostLikes postLike = mapper.mappingPostLikesBy(userId, postId);
            postLikesRepository.save(postLike);
            log.info("Post {} liked by user {}", postId, userId);
        } catch (Exception e) {
            log.error("POST LIKE SAVING ERROR -> \n {}", e.getMessage());
            throw new EntityPersistException("ERROR WHILE SAVING POST LIKE, CHECK INPUT DATA");
        }
    }

    public List<PostLikesDto> getUserLikedPosts(final String userId) {
        final List<PostLikes> userLikedPosts = postLikesRepository.findByUserIdOrderByCreatedAtDesc(userId);

        if (userLikedPosts.isEmpty()) {
            log.info("No liked posts found for user {}", userId);
            return Collections.emptyList();
        }

        return userLikedPosts.stream().map(mapper::mappingPostLikesDtoBy).toList();
    }

    public int getPostLikesCount(final String postId){
        return postLikesRepository.likesCountByPostId(postId);
    }
}
