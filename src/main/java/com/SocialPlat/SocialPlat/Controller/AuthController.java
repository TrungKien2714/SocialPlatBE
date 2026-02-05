package com.SocialPlat.SocialPlat.Controller;

import com.SocialPlat.SocialPlat.security.config.SecurityConfiguration;
import com.SocialPlat.SocialPlat.security.dto.LoginRequest;
import com.SocialPlat.SocialPlat.security.dto.LoginResponse;
import com.SocialPlat.SocialPlat.security.service.CustomerUserDetailService;
import com.SocialPlat.SocialPlat.security.user.UserDetailimp;
import com.SocialPlat.SocialPlat.security.util.JWTutil;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JWTutil jwtUtil;
    private final CustomerUserDetailService userDetailService;
    public AuthController(AuthenticationManager authenticationManager, JWTutil jwtUtil, CustomerUserDetailService userDetailService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailService = userDetailService;
    }
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        Authentication auth= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.email(),
                loginRequest.password()
        ));
        UserDetailimp user=(UserDetailimp) userDetailService.loadUserByUsername(loginRequest.email());
        String token = jwtUtil.generateToken(user);
        return  new LoginResponse(token);
    }
}
