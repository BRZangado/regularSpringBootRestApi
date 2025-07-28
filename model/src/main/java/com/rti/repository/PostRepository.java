package com.rti.repository;

import com.rti.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, String> {

    @Query(value = "SELECT * FROM post WHERE user_id = :userId ORDER BY created_at DESC", nativeQuery = true)
    List<Post> findByUserIdOrderByCreatedAtDesc(@Param("userId") String userId);

    @Query(value = "SELECT * FROM post WHERE id = :postId", nativeQuery = true)
    Optional<Post> findPostById(@Param("postId") String postId);
}
