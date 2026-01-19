package domain;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name= "users")
public class Users {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private long id;
    private String email;
    private String password;
    private String role;
    private String status;
    private String created_at;

}
