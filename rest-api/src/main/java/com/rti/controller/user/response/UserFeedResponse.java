package com.rti.controller.user.response;

import com.rti.controller.post.response.PostCompleteResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserFeedResponse {
    private UserProfileResponse user;
    private List<PostCompleteResponse> posts;
}
