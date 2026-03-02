package com.SocialPlat.SocialPlat.Service;

import com.SocialPlat.SocialPlat.Repository.UserRepositoy;
import com.SocialPlat.SocialPlat.constant.UserRole;
import com.SocialPlat.SocialPlat.constant.UserStatus;
import com.SocialPlat.SocialPlat.domain.Users;
import com.SocialPlat.SocialPlat.exception.ResourceNotFoundException;
import com.SocialPlat.SocialPlat.security.dto.AdminUpdateUserRequest;
import com.SocialPlat.SocialPlat.security.dto.UserProfileResponse;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepositoy userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Users handleCreateUser(Users input){
        Users creeateUSer=this.userRepository.save(input);
        return creeateUSer;
    }
    @Transactional(readOnly = true)
    public Users getUserById(Long id){
        Optional<Users> getUserById=this.userRepository.findById(id);
        return  getUserById.isPresent()? getUserById.get():null;
    }
    @Transactional(readOnly = true)
    public UserProfileResponse getUserProfile(Long id){
        Users user = userRepository.findByIdAndStatus(id, UserStatus.ACTIVE)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id = " + id));
        return convertToProfileResponse(user);
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

    public Users handleFindByEmail(String email){
        return this.userRepository.findByEmail(email);
    }
    @Transactional
    public UserProfileResponse adminUpdateUser(Long id, AdminUpdateUserRequest request){
        Users currentUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id = " + id));
        if (request.role() != null) {
            currentUser.setRole(request.role());
        }
        if (request.status() != null) {
            currentUser.setStatus(request.status());
        }
        Users updatedUser = userRepository.save(currentUser);
        return convertToProfileResponse(updatedUser);
    }
    private UserProfileResponse convertToProfileResponse(Users user) {
        return UserProfileResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .fullName(user.getFullName())
                .avatarUrl(user.getAvatarUrl())
                .role(user.getRole())
                .status(user.getStatus())
                .build();
    }
}
