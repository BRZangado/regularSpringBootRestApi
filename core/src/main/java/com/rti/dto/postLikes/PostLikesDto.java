package com.rti.dto.postLikes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostLikesDto {
    private String id;
    private String postId;
    private String userId;
}
