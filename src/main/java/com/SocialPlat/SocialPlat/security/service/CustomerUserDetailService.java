package com.SocialPlat.SocialPlat.security.service;

import com.SocialPlat.SocialPlat.Repository.UserRepositoy;
import com.SocialPlat.SocialPlat.domain.Users;
import com.SocialPlat.SocialPlat.security.user.UserDetailimp;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
@Service
public class CustomerUserDetailService implements UserDetailsService {
    private final UserRepositoy userRepository;
    public CustomerUserDetailService(UserRepositoy userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return new UserDetailimp(user.getEmail(), user.getPassword(), user.getRole(), user.getStatus());
    }
}
