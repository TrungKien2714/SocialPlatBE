package com.SocialPlat.SocialPlat.Service;

import com.SocialPlat.SocialPlat.Repository.UserRepository;
import com.SocialPlat.SocialPlat.constant.UserStatus;
import com.SocialPlat.SocialPlat.Entity.Users;
import com.SocialPlat.SocialPlat.exception.ResourceNotFoundException;
import com.SocialPlat.SocialPlat.dto.UpdateProfileRequest;
import com.SocialPlat.SocialPlat.dto.UserProfileResponse;
import com.SocialPlat.SocialPlat.dto.UserSummaryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Users handleCreateUser(Users input){
        Users creeateUSer=this.userRepository.save(input);
        return creeateUSer;
    }

    @Transactional(readOnly = true)
    public Users getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public UserProfileResponse getUserProfile(Long id){
        Users user = userRepository.findByIdAndStatus(id, UserStatus.ACTIVE)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id = " + id));
        return convertToProfileResponse(user);
    }

    @Transactional
    public UserProfileResponse getUserProfileByUserName(String username){
        Users users = userRepository.findByUsernameAndStatus(username,UserStatus.ACTIVE)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username = " + username));
        return convertToProfileResponse(users);
    }

    @Transactional(readOnly = true)
    public List<Users> getAllUsers(){
        return this.userRepository.findAll();
    }

    public void deleteUser(Long id){
        this.userRepository.deleteById(id);
    }

    public Users handleChangePassword(String email, String newPassword){
        Users currentUser = userRepository.findByEmail(email);
        if (currentUser == null) {
            throw new RuntimeException("User not found");
        }
        currentUser.setPassword(passwordEncoder.encode(newPassword));
        return userRepository.save(currentUser);
    }

    @Transactional
    public Users handleFindByEmail(String email){
        return this.userRepository.findByEmail(email);
    }

    @Transactional
    public UserProfileResponse updateProfile(String email, UpdateProfileRequest request) {
        Users user = userRepository.findByEmail(email);
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }
        // Update fields
        if (request.getFullName() != null) {
            user.setFullName(request.getFullName());
        }
        if (request.getBio() != null) {
            user.setBio(request.getBio());
        }
        if (request.getLocation() != null) {
            user.setLocation(request.getLocation());
        }
        if (request.getWebsite() != null) {
            user.setWebsite(request.getWebsite());
        }
        if (request.getPhone() != null) {
            user.setPhone(request.getPhone());
        }
        if (request.getDateOfBirth() != null) {
            user.setDateOfBirth(request.getDateOfBirth());
        }

        Users updated = userRepository.save(user);
        return convertToProfileResponse(updated);
    }

    @Transactional(readOnly = true)
    public Page<UserSummaryResponse> searchUsers(String keyword, Pageable pageable) {
        return userRepository.searchUsers(keyword, UserStatus.ACTIVE, pageable)
                .map(this::convertToSummary);
    }

    @Transactional
    public UserProfileResponse updateAvatar(String email,String avatarUrl){
        Users user= userRepository.findByEmail(email);
        if(user == null){
            throw new ResourceNotFoundException("User not found with email: " + email);
        }
        user.setAvatarUrl(avatarUrl);
        Users updated = userRepository.save(user);
        return convertToProfileResponse(updated);
    }

    private UserProfileResponse convertToProfileResponse(Users user) {
        return UserProfileResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .fullName(user.getFullName())
                .bio(user.getBio())
                .avatarUrl(user.getAvatarUrl())
                .coverPhotoUrl(user.getCoverPhotoUrl())
                .dateOfBirth(user.getDateOfBirth())
                .location(user.getLocation())
                .website(user.getWebsite())
                .phone(user.getPhone())
                .role(user.getRole())
                .status(user.getStatus())
                .isVerified(user.getIsVerified())
                .emailVerified(user.getEmailVerified())
                .createdAt(user.getCreatedAt())
                .lastLoginAt(user.getLastLoginAt())
                .build();
    }
    private UserSummaryResponse convertToSummary(Users user){
        return UserSummaryResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .fullName(user.getFullName())
                .avatarUrl(user.getAvatarUrl())
                .bio(user.getBio())
                .isVerified(user.getIsVerified())
                .build();
    }
}
