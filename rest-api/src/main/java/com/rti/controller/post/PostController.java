package com.rti.controller.post;

import com.rti.controller.post.request.PostCreateRequest;
import com.rti.controller.post.response.PostCompleteResponse;
import com.rti.controller.post.response.PostTinyResponse;
import com.rti.controller.user.response.UserPostsResponse;
import com.rti.usecase.post.GetLikedPostsUseCase;
import com.rti.usecase.post.GetPostByIdUseCase;
import com.rti.usecase.post.GetUserPostListUseCase;
import com.rti.usecase.post.PostCreatePostUseCase;
import com.rti.usecase.post.PostLikePostUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/post")
@Validated
public class PostController {

    private final GetUserPostListUseCase getUserPostListUseCase;
    private final PostCreatePostUseCase postCreatePostUseCase;
    private final PostLikePostUseCase postLikePostUseCase;
    private final GetPostByIdUseCase getPostByIdUseCase;
    private final GetLikedPostsUseCase getLikedPostsUseCase;

    @PostMapping()
    public ResponseEntity<PostTinyResponse> createPost(@RequestBody @Valid final PostCreateRequest postCreateRequest,
                                                        final Principal principal){
        return ResponseEntity.ok(postCreatePostUseCase.execute(postCreateRequest, principal));
    }

    @GetMapping("/list")
    public ResponseEntity<UserPostsResponse> getMyPostsList(final Principal principal){
        return ResponseEntity.ok(getUserPostListUseCase.execute(principal));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostCompleteResponse> getPostById(@PathVariable final String postId){
        return ResponseEntity.ok(getPostByIdUseCase.execute(postId));
    }

    @PutMapping("/{postId}/like")
    public ResponseEntity<Void> likePost(@PathVariable final String postId, final Principal principal){
        postLikePostUseCase.execute(postId, principal);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/liked")
    public ResponseEntity<List<PostCompleteResponse>> getMyLikedPosts(final Principal principal){
        return ResponseEntity.ok(getLikedPostsUseCase.execute(principal));
    }
}
