package int221.YuuuHooo.project.services;

import int221.YuuuHooo.project.dtos.UserDTO;
import int221.YuuuHooo.project.dtos.UserHidePasswordDTO;
import int221.YuuuHooo.project.dtos.UserMatchPasswordDTO;
import int221.YuuuHooo.project.dtos.UserUpdateDTO;
import int221.YuuuHooo.project.entities.User;
import int221.YuuuHooo.project.exceptions.ErrorResponse;
import int221.YuuuHooo.project.exceptions.UserUpdateException;
import int221.YuuuHooo.project.repositories.userRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class userService {

    @Autowired
    private userRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;


    private Argon2PasswordEncoder encoder = new Argon2PasswordEncoder(16, 32, 1, 2048, 10);

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
    public UserHidePasswordDTO updateUser(Integer userId, UserUpdateDTO userInfo, WebRequest request) {
        User userUpdate = userRepository.findById(userId).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatusCode.valueOf(404),
                        "User id " + userId + " does not exist"
                ));


        ErrorResponse er = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), request.getDescription(false).substring(4));
        if(!userUpdate.getUsername().equals(userInfo.getUsername()) && userRepository.findByUsername(userInfo.getUsername()) != null){
            er.addValidationError("username", "does not unique");
        }

        if (!userUpdate.getName().equals(userInfo.getName()) && userRepository.findByName(userInfo.getName()) != null) {
            er.addValidationError("name", "does not unique");
        }

        if (!userUpdate.getEmail().equals(userInfo.getEmail()) && userRepository.findByEmail(userInfo.getEmail()) != null) {
            er.addValidationError("email", "does not unique");
        }

        if(er.hasErrors()){
            throw new UserUpdateException(er);
        }

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
            throw new ResponseStatusException(HttpStatusCode.valueOf(404), "The specified username DOES NOT exist");
        }
    }




}
