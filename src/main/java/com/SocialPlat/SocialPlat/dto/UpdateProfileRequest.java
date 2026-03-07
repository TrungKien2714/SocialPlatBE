package com.SocialPlat.SocialPlat.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateProfileRequest {

    @Size(max = 100, message = "Full name must not exceed 100 characters")
    private String fullName;

    @Size(max = 500, message = "Bio must not exceed 500 characters")
    private String bio;

    @Size(max = 255, message = "Location must not exceed 255 characters")
    private String location;

    @Pattern(regexp = "^(https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})[/\\w .-]*/?$",
            message = "Invalid website URL")
    private String website;

    @Pattern(regexp = "^[+]?[0-9]{10,15}$", message = "Invalid phone number")
    private String phone;

    private LocalDateTime dateOfBirth;
}