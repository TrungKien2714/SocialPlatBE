package com.SocialPlat.SocialPlat.security.dto;

import com.SocialPlat.SocialPlat.constant.UserRole;
import com.SocialPlat.SocialPlat.constant.UserStatus;
import lombok.Builder;

@Builder
public record UserProfileResponse(
         Long id,
         String email,
         String username,
         String fullName,
         String avatarUrl,
        UserRole role,
        UserStatus status) {
}
