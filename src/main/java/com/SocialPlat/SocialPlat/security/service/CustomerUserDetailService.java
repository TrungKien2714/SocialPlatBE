package com.SocialPlat.SocialPlat.security.service;

import com.SocialPlat.SocialPlat.Repository.UserRepository;
import com.SocialPlat.SocialPlat.Entity.Users;
import com.SocialPlat.SocialPlat.security.user.UserDetailimp;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    public CustomerUserDetailService(UserRepository userRepository) {
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
