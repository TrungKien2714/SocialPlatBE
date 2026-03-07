package com.SocialPlat.SocialPlat.dto;
import com.SocialPlat.SocialPlat.constant.UserRole;
import com.SocialPlat.SocialPlat.constant.UserStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserProfileResponse {
    private Long id;
    private String email;
    private String username;
    private String fullName;
    private String bio;
    private String avatarUrl;
    private String coverPhotoUrl;
    private LocalDateTime dateOfBirth;
    private String location;
    private String website;
    private String phone;
    private UserRole role;
    private UserStatus status;
    private Boolean isVerified;
    private Boolean emailVerified;
    private LocalDateTime createdAt;
    private LocalDateTime lastLoginAt;

    // Stats (will be added later)
    private Long postsCount;
    private Long followersCount;
    private Long followingCount;

    // For current user viewing
    private Boolean isFollowing;
    private Boolean isOwnProfile;
}