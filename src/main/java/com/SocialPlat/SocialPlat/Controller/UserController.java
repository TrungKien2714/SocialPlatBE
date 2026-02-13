package com.SocialPlat.SocialPlat.Controller;
import com.SocialPlat.SocialPlat.constant.UserRole;
import com.SocialPlat.SocialPlat.constant.UserStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.SocialPlat.SocialPlat.Service.UserService;
import com.SocialPlat.SocialPlat.domain.Users;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Getter
@Setter
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUser(@PathVariable Long id){
        Users getUserById=this.userService.getUserById(id);
        return ResponseEntity.ok().body(getUserById);
    }
    @GetMapping("/user")
    public ResponseEntity<List<Users>> getAllUser(){
        List<Users>getAllUser=this.userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(getAllUser);
    }
//    @PostMapping("/user")
//    public ResponseEntity<Users> createUser(@RequestBody Users input){
//        String hashPassword=this.passwordEncoder.encode(input.getPassword());
//        input.setPassword(hashPassword);
//        input.setStatus(UserStatus.ACTIVE);
//        input.setRole(UserRole.USER);
//        input.setCreated_at(LocalDateTime.now().withNano(0));
//        Users createUser=this.userService.handleCreateUser(input);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createUser);
//    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Users> deleteUser(@PathVariable Long id)throws Exception{
        if(this.userService.getUserById(id)==null){
            throw new Exception("User not found");
        }else{
            this.userService.deleteUser(id);
        }
        return ResponseEntity.ok().body(null);
    }

    @PutMapping("/profile/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable Long id, @RequestBody Users input){
        Users updateUser=this.userService.handleUpdateUser(id, input);
        return ResponseEntity.status(HttpStatus.OK).body(updateUser);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/user/{id}")
    public ResponseEntity<Users> updateRole (@PathVariable Long id, @RequestBody Users input){
        Users update= this.userService.handleUpdateRole(id,input.getRole());
        return ResponseEntity.ok(update);
    }
}
