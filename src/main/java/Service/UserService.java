package Service;

import Repository.UserRepositoy;
import domain.Users;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepositoy userRepository;
    private UserService(UserRepositoy userRepositoy){
        this.userRepository=userRepositoy;
    }
    public Users handleCreateUser(Users input){
        Users creeateUSer=this.userRepository.save(input);
        return creeateUSer;
    }
    public Users getUserById(Long id){
        Optional<Users> getUserById=this.userRepository.findById(id);
        return  getUserById.isPresent()? getUserById.get():null;
    }
    public List<Users> getAllUsers(){
        return this.userRepository.findAll();
    }
    public void deleteUser(Long id){
        this.userRepository.deleteById(id);
    }
    public Users handleUpdateUser(Long id,Users input){
        Optional<Users> getUserById=this.userRepository.findById(id);
        if(getUserById.isPresent()){
            Users currentUser=getUserById.get();
            currentUser.setEmail(input.getEmail());
            currentUser.setPassword(input.getPassword());
            this.userRepository.save(currentUser);
            return currentUser;
        }
            return null;
    }
    public Users handleFindByEmail(String email){
        return this.userRepository.findByEmail(email);
    }
}
