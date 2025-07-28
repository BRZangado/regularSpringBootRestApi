package com.rti.repository;

import com.rti.entity.PostLikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostLikesRepository extends JpaRepository<PostLikes, String> {

    @Query(value = "SELECT * FROM post_likes WHERE post_id = :postId ORDER BY created_at DESC",
            nativeQuery = true)
    List<PostLikes> findByPostIdOrderByCreatedAtDesc(@Param("postId") final String postId);

    @Query(value = "SELECT * FROM post_likes WHERE user_id = :userId ORDER BY created_at DESC",
            nativeQuery = true)
    List<PostLikes> findByUserIdOrderByCreatedAtDesc(@Param("userId") final String userId);

    @Query(value = "SELECT * FROM post_likes WHERE user_id = :userId AND post_id = :postId LIMIT 1",
            nativeQuery = true)
    Optional<PostLikes> findByUserIdAndPostId(@Param("userId") final String userId,
                                              @Param("postId") final String postId);

    @Query(value = "SELECT COUNT(*) FROM post_likes WHERE post_id = :postId", nativeQuery = true)
    int likesCountByPostId(@Param("postId") String postId);
}
