package int221.YuuuHooo.project.services;

import int221.YuuuHooo.project.dtos.UserDTO;
import int221.YuuuHooo.project.entities.User;
import int221.YuuuHooo.project.repositories.userRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class userService {

    @Autowired
    private int221.YuuuHooo.project.repositories.userRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<User> getUser() {
        return userRepository.findAll(Sort.by("role", "username").ascending());
    }

    public User getUserByID(int id) {
        return userRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatusCode.valueOf(404),
                        "User id " + id + " does not exist"
                ));
    }


    public void deleteUser(int id) {
        userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatusCode.valueOf(404),
                        "User id " + id + " does not exist"
                ));
        userRepository.deleteById(id);
    }

    public User updateUser(int userId, UserDTO update) {
        User userUpdate = userRepository.findById(userId).orElseThrow(
                () -> new ResponseStatusException(HttpStatusCode.valueOf(404), "User id " + userId + " does not exist"));
        userUpdate.setUsername(update.getUsername().trim());
        userUpdate.setName(update.getName().trim());
        userUpdate.setEmail(update.getEmail());
        userUpdate.setRole(update.getRole().trim());
        return userRepository.saveAndFlush(userUpdate);
    }

    public UserDTO addUser(UserDTO user) {
        User newUser = new User() ;
        newUser.setUsername(user.getUsername().trim());
        newUser.setName(user.getName().trim());
        newUser.setEmail(user.getEmail());
        newUser.setRole(user.getRole().trim());
        return modelMapper.map(userRepository.saveAndFlush(newUser),UserDTO.class);
    }



}
