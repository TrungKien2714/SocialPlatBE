package com.SocialPlat.SocialPlat.Repository;

import com.SocialPlat.SocialPlat.constant.UserStatus;
import com.SocialPlat.SocialPlat.domain.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {
    Users findByEmail(String email);

    boolean existsByEmail(String email);

    //find by Id and Status
    Optional<Users> findByIdAndStatus(Long id, UserStatus status);

    Optional<Users> findByUsernameAndStatus(String username, UserStatus status);

    //Search User
    @Query("SELECT u FROM Users u WHERE " +
            "(LOWER(u.username) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(u.fullName) LIKE LOWER(CONCAT('%', :keyword, '%'))) AND " +
            "u.status = :status")
    Page<Users> searchUsers(@Param("keyword") String keyword,
                            @Param("status") UserStatus status
            , Pageable pageable);


}