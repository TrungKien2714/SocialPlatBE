package com.SocialPlat.SocialPlat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSummaryResponse {
    private Long id;
    private String username;
    private String fullName;
    private String avatarUrl;
    private String bio;
    private Boolean isVerified;
    private Long followersCount;
    private Long followingCount;
    private Boolean isFollowing;
}
