package com.SocialPlat.SocialPlat.Service;

import com.SocialPlat.SocialPlat.Repository.UserRepositoy;
import com.SocialPlat.SocialPlat.constant.UserRole;
import com.SocialPlat.SocialPlat.domain.Users;
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

    public Users handleCreateUser(Users input){
        Users creeateUSer=this.userRepository.save(input);
        return creeateUSer;
    }
    public Users getUserById(Long id){
        Optional<Users> getUserById=this.userRepository.findById(id);
        return  getUserById.isPresent()? getUserById.get():null;
    }
    public List<Users> getAllUsers(){
        return this.userRepository.findAll();
    }
    public void deleteUser(Long id){
        this.userRepository.deleteById(id);
    }
    public Users handleUpdateUser(Long id, Users input){
        Users currentUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id = " + id));

        if (input.getEmail() != null) {
            currentUser.setEmail(input.getEmail());
        }
        if (input.getPassword() != null) {
            currentUser.setPassword(passwordEncoder.encode(((input.getPassword()))));
        }
        if (input.getRole() != null) {
            currentUser.setRole(input.getRole());
        }
        if (input.getStatus() != null) {
            currentUser.setStatus(input.getStatus());
        }

        return userRepository.save(currentUser);
    }

    public Users handleFindByEmail(String email){
        return this.userRepository.findByEmail(email);
    }
    public Users handleUpdateRole(Long id, UserRole role){
        Users user = this.userRepository.findById(id).orElseThrow(()-> new RuntimeException(("User not found")));
        user.setRole(role);
        return this.userRepository.save(user);
    }
}
