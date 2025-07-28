package com.rti.controller.post.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostCompleteResponse {
    private String id;
    private String content;
    private String imageUrl;
    private LocalDateTime createdAt;
    private Integer likesCount;
}
