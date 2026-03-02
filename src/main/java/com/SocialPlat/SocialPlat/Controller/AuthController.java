package com.SocialPlat.SocialPlat.Controller;

import com.SocialPlat.SocialPlat.Repository.UserRepositoy;
import com.SocialPlat.SocialPlat.Service.UserService;
import com.SocialPlat.SocialPlat.constant.UserRole;
import com.SocialPlat.SocialPlat.constant.UserStatus;
import com.SocialPlat.SocialPlat.domain.Users;
import com.SocialPlat.SocialPlat.security.config.SecurityConfiguration;
import com.SocialPlat.SocialPlat.security.dto.*;
import com.SocialPlat.SocialPlat.security.service.CustomerUserDetailService;
import com.SocialPlat.SocialPlat.security.user.UserDetailimp;
import com.SocialPlat.SocialPlat.security.util.JWTutil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JWTutil jwtUtil;
    private final CustomerUserDetailService userDetailService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepositoy userRepositoy;
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        Authentication auth= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.email(),
                loginRequest.password()
        ));
        UserDetailimp user=(UserDetailimp) userDetailService.loadUserByUsername(loginRequest.email());
        String token = jwtUtil.generateToken(user);
        return new LoginResponse(token);
    }

    @PostMapping("register")
    public RegisterResponse register(@RequestBody RegisterRequest registerRequest){
        if (userRepositoy.existsByEmail(registerRequest.email())) {
            throw new RuntimeException("Email already in use");
        }
        Users newUser=new Users();
        newUser.setEmail(registerRequest.email());
        newUser.setPassword(this.passwordEncoder.encode(registerRequest.password()));
        newUser.setRole(UserRole.USER);
        newUser.setStatus(UserStatus.ACTIVE);
        newUser.setCreatedAt(LocalDateTime.now().withNano(0));
        userService.handleCreateUser(newUser);
        return new RegisterResponse("User registered successfully", newUser.getEmail());
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getMe(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Users user = userRepositoy.findByEmail(email);
        if(user==null){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        UserResponse userResponse= new UserResponse(user.getId(),user.getEmail(),user.getRole().name());
        return ResponseEntity.ok(userResponse);
    }
}
