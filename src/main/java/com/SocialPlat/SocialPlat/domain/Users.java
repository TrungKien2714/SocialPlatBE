package com.SocialPlat.SocialPlat.domain;
import com.SocialPlat.SocialPlat.constant.UserRole;
import com.SocialPlat.SocialPlat.constant.UserStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name= "users")
public class Users {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String email;
    @Column(name="password")
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    @Column(name = "created_at")
    private LocalDateTime created_at;
}
