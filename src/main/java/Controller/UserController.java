package Controller;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import Service.UserService;
import domain.Users;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Getter
@Setter
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/user/{id}")
    public ResponseEntity<Users> getUser(@PathVariable Long id){
        Users getUserById=this.userService.getUserById(id);
        return ResponseEntity.ok().body(getUserById);
    }
    @GetMapping("/user")
    public ResponseEntity<List<Users>> getAllUser(){
        List<Users>getAllUser=this.userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(getAllUser);
    }
    @PostMapping("/user")
    public ResponseEntity<Users> createUser(@RequestBody Users input){
        String hashPassword=this.passwordEncoder.encode(input.getPassword());
        input.setPassword(hashPassword);
        Users createUser=this.userService.handleCreateUser(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(createUser);
    }
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Users> deleteUser(@PathVariable Long id)throws Exception{
        if(this.userService.getUserById(id)==null){
            throw new Exception("User not found");
        }else{
            this.userService.deleteUser(id);
        }
        return ResponseEntity.ok().body(null);
    }
    @PutMapping("/user/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable Long id, @RequestBody Users input){
        Users updateUser=this.userService.handleUpdateUser(id, input);
        return ResponseEntity.status(HttpStatus.OK).body(updateUser);
    }
}
