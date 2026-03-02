package com.SocialPlat.SocialPlat.security.dto;

import com.SocialPlat.SocialPlat.constant.UserRole;
import com.SocialPlat.SocialPlat.constant.UserStatus;

public record AdminUpdateUserRequest(
        UserRole role,
        UserStatus status) {
}
