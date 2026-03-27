package com.SocialPlat.SocialPlat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {

    private Long id;

    /** Author of the post */
    private UserSummaryResponse author;

    private String content;

    /** Visibility: PUBLIC, FRIENDS, PRIVATE */
    private String visibility;

    /** Number of likes on this post */
    private Long likeCount;

    /** Number of comments on this post */
    private Long commentCount;

    /** Media attachments (images, videos) */
    private List<MediaResponse> mediaList;

    private Instant createdAt;
    private Instant updatedAt;

    /**
     * Whether the currently authenticated user has liked this post.
     * Set to null if no user context is available.
     */
    private Boolean isLikedByCurrentUser;

    // -------------------------------------------------------

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MediaResponse {
        private Long id;
        private String mediaUrl;
        private String mediaType;
    }
}
