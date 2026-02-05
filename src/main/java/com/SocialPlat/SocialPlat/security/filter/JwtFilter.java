package com.SocialPlat.SocialPlat.security.filter;

import com.SocialPlat.SocialPlat.security.service.CustomerUserDetailService;
import com.SocialPlat.SocialPlat.security.user.UserDetailimp;
import com.SocialPlat.SocialPlat.security.util.JWTutil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private  final JWTutil jwtUtil;
    private final CustomerUserDetailService userDetailService;
    public JwtFilter(JWTutil jwtUtil, CustomerUserDetailService userDetailService) {
        this.jwtUtil = jwtUtil;
        this.userDetailService = userDetailService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header=request.getHeader("Authorization");
        if(header != null&& header.startsWith("Bearer ")){
            String token = header.substring(7);
            String username= jwtUtil.extractUsername(token);
            UserDetailimp user=(UserDetailimp) userDetailService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(
                            user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
    }
}
