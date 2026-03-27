package com.SocialPlat.SocialPlat.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostRequest {

    @NotBlank(message = "Content must not be blank")
    private String content;

    /**
     * Visibility of the post: PUBLIC, FRIENDS, PRIVATE
     */
    @Pattern(
            regexp = "PUBLIC|FRIENDS|PRIVATE",
            message = "Visibility must be one of: PUBLIC, FRIENDS, PRIVATE"
    )
    @Builder.Default
    private String visibility = "PUBLIC";

    /**
     * List of media URLs attached to this post (images, videos, etc.)
     */
    private List<MediaRequest> mediaList;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MediaRequest {

        @NotBlank(message = "Media URL must not be blank")
        private String mediaUrl;

        /**
         * Media type: IMAGE, VIDEO, etc.
         */
        private String mediaType;
    }
}
