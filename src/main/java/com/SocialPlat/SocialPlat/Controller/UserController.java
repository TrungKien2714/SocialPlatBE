package com.SocialPlat.SocialPlat.Controller;

import com.SocialPlat.SocialPlat.dto.UpdateProfileRequest;
import com.SocialPlat.SocialPlat.dto.ChangePasswordRequest;
import com.SocialPlat.SocialPlat.dto.UserProfileResponse;
import com.SocialPlat.SocialPlat.dto.UserSummaryResponse;
import com.SocialPlat.SocialPlat.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.SocialPlat.SocialPlat.Service.UserService;
import com.SocialPlat.SocialPlat.Entity.Users;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserProfileResponse>> getUser(@PathVariable Long id) {
        UserProfileResponse user = this.userService.getUserProfile(id);
        return ResponseEntity.ok(ApiResponse.success(user));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<ApiResponse<UserProfileResponse>> getUserByUsername(@PathVariable String username) {
        UserProfileResponse user = this.userService.getUserProfileByUserName(username);
        return ResponseEntity.ok(ApiResponse.success(user));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Users>> getAllUser() {
        List<Users> getAllUser = this.userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(getAllUser);
    }

    //    @PostMapping("/user")
//    public ResponseEntity<Users> createUser(@RequestBody Users input){
//        String hashPassword=this.passwordEncoder.encode(input.getPassword());
//        input.setPassword(hashPassword);
//        input.setStatus(UserStatus.ACTIVE);
//        input.setRole(UserRole.USER);
//        input.setCreated_at(LocalDateTime.now().withNano(0));
//        Users createUser=this.userService.handleCreateUser(input);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createUser);
//    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Users> deleteUser(@PathVariable Long id) throws Exception {
        if (this.userService.getUserById(id) == null) {
            throw new Exception("User not found");
        } else {
            this.userService.deleteUser(id);
        }
        return ResponseEntity.ok().body(null);
    }

    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request) {
        String email = org.springframework.security.core.context.SecurityContextHolder
                .getContext().getAuthentication().getName();
        this.userService.handleChangePassword(email, request.newPassword());
        return ResponseEntity.ok("Password updated successfully");
    }

    @PutMapping("/profile")
    public ResponseEntity<ApiResponse<UserProfileResponse>> UserUpdateProfile(@Valid @RequestBody UpdateProfileRequest request, Authentication authentication) {
        String email = authentication.getName();
        UserProfileResponse updated = this.userService.updateProfile(email, request);
        return ResponseEntity.ok(ApiResponse.success("Profile updated successfully", updated));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<Page<UserSummaryResponse>>> searchUsers(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserSummaryResponse> users = this.userService.searchUsers(keyword, pageable);
        return ResponseEntity.ok(ApiResponse.success(users));
    }
}
