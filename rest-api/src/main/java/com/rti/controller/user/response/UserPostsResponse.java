package com.rti.controller.user.response;

import com.rti.controller.post.response.PostTinyResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPostsResponse {
    private UserTinyResponse user;
    private List<PostTinyResponse> posts;
}
