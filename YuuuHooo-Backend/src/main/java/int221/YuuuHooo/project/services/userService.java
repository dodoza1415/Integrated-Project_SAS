package int221.YuuuHooo.project.services;

import int221.YuuuHooo.project.dtos.UserDTO;
import int221.YuuuHooo.project.dtos.UserHidePasswordDTO;
import int221.YuuuHooo.project.dtos.UserMatchPasswordDTO;
import int221.YuuuHooo.project.entities.User;
import int221.YuuuHooo.project.repositories.userRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class userService {

    @Autowired
    private userRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    private Argon2PasswordEncoder encoder = new Argon2PasswordEncoder(16, 32, 1, 60000, 10);

    public List<UserHidePasswordDTO> getUser() {
        List<User> userList = userRepository.findAll(Sort.by("role", "username").ascending());
        List<UserHidePasswordDTO> userHidePasswordDTOList = userList.stream().map(u
                -> modelMapper.map(u, UserHidePasswordDTO.class)).collect(Collectors.toList());
        return userHidePasswordDTOList;
    }

    public UserHidePasswordDTO getUserByID(int id) {
         User existUser =  userRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatusCode.valueOf(404),
                        "User id " + id + " does not exist"
                ));
         return modelMapper.map(existUser, UserHidePasswordDTO.class);
    }


    public void deleteUser(int id) {
        userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatusCode.valueOf(404),
                        "User id " + id + " does not exist"
                ));
        userRepository.deleteById(id);
    }

    @Transactional
    public UserHidePasswordDTO updateUser(Integer userId,UserDTO userInfo) {
        User userUpdate = userRepository.findById(userId).orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatusCode.valueOf(404),
                                "User id " + userId + " does not exist"
                        ));
            userUpdate.setUsername(userInfo.getUsername().trim());
            userUpdate.setName(userInfo.getName().trim());
            userUpdate.setEmail(userInfo.getEmail());
            userUpdate.setRole(userInfo.getRole().trim());

        User update = userRepository.saveAndFlush(userUpdate);
        userRepository.refresh(update);
        return modelMapper.map(update, UserHidePasswordDTO.class);
    }

    @Transactional
    public UserHidePasswordDTO addUser(UserDTO user) {
        User newUser = new User();

        newUser.setUsername(user.getUsername().trim());
        newUser.setName(user.getName().trim());
        newUser.setEmail(user.getEmail().trim());
        newUser.setRole(user.getRole().trim());

        String rawPassword = user.getPassword().trim();
        String password = encoder.encode(rawPassword);
        newUser.setPassword(password);
//        System.out.println(rawPassword);
        User createUser = userRepository.saveAndFlush(newUser);
        userRepository.refresh(createUser);

        UserHidePasswordDTO userHidePasswordDTO = modelMapper.map(createUser, UserHidePasswordDTO.class);

        return userHidePasswordDTO;
    }

    public ResponseEntity<String> matchPassword(UserMatchPasswordDTO matchInfo) {
        User userForCheck = userRepository.findByUsername(matchInfo.getUsername());
//        System.out.println(encoder.matches(matchInfo.getPassword(), userForCheck.getPassword()));
        if (userForCheck != null) {
            if (encoder.matches(matchInfo.getPassword(), userForCheck.getPassword())) {
                return ResponseEntity.ok("Password Matched");
            } else {
                throw new ResponseStatusException(HttpStatusCode.valueOf(401), "Password NOT Matched");
            }
        }else{
            throw new ResponseStatusException(HttpStatusCode.valueOf(404), "username DOES NOT exists");
        }
    }



}
