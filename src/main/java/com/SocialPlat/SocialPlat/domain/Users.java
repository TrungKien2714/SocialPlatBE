package com.SocialPlat.SocialPlat.domain;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@Entity
@Table(name= "users")
public class Users {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;
    private String email;
    @Column(name="password_hash")
    private String password;
    private String role;
    private String status;
    private String created_at;

}
