package int221.YuuuHooo.project.services;

import int221.YuuuHooo.project.dtos.UserDTO;
import int221.YuuuHooo.project.entities.User;
import int221.YuuuHooo.project.repositories.userRepository;
import jakarta.transaction.Transactional;
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
    private userRepository userRepository;

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

    @Transactional
    public User updateUser(Integer userId,UserDTO userInfo) {
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
        return update;
    }

    @Transactional
    public User addUser(UserDTO user) {
        User newUser = new User();

        newUser.setUsername(user.getUsername().trim());
        newUser.setName(user.getName().trim());
        newUser.setEmail(user.getEmail().trim());
        newUser.setRole(user.getRole().trim());
        User createUser = userRepository.saveAndFlush(newUser);
        userRepository.refresh(createUser);
        return createUser;
    }



}
