package com.SocialPlat.SocialPlat.Repository;

import com.SocialPlat.SocialPlat.constant.UserStatus;
import com.SocialPlat.SocialPlat.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositoy extends JpaRepository<Users,Long> {
    Users findByEmail(String email);
    boolean existsByEmail(String email);
    //find by Id and Status
    Optional<Users> findByIdAndStatus(Long id, UserStatus status);}
