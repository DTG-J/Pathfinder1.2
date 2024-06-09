package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.data.UserRepository;
import bg.softuni.pathfinder.model.User;
import bg.softuni.pathfinder.web.UserLoginDTO;
import bg.softuni.pathfinder.web.dto.UserRegisterDTO;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    public void register (UserRegisterDTO userRegisterDTO){
        User user = this.modelMapper.map (userRegisterDTO, User.class);
        user.setPassword (passwordEncoder.encode (userRegisterDTO.getPassword ()));
        userRepository.save (user);
    }

    public void login(UserLoginDTO loginData) {
        User user = userRepository.findByUsername(loginData.getUsername ());
        if (user == null){
            return;
            //TODO throw error
        }
        if (passwordEncoder.matches (loginData.getPassword (), user.getPassword ()) && !currentUser.isLoggedIn ()){
        currentUser.setUser (user);
        }
    }
}
