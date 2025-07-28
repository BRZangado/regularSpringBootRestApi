package com.rti.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name="PostLikes")
@Table(name= "post_likes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostLikes {
    @Id
    @Column(length = 36)
    private String id;

    @Column(name = "user_id", nullable = false, columnDefinition = "CHAR(36)")
    private String userId;

    @Column(name = "post_id", nullable = false, columnDefinition = "CHAR(36)")
    private String postId;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreateEntity() {
        this.id = UUID.randomUUID().toString();
    }
}
