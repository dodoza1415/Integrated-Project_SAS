package int221.YuuuHooo.project.controllers;

import int221.YuuuHooo.project.dtos.UserDTO;
import int221.YuuuHooo.project.entities.User;
import int221.YuuuHooo.project.repositories.userRepository;
import int221.YuuuHooo.project.services.userService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:5173", "http://intproj22.sit.kmutt.ac.th"})
public class userController {

    @Autowired
    private userService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private userRepository userRepository;

    @GetMapping("/users")
    public List<User> getUser() {
        return userService.getUser();
    }

    @GetMapping("/users/{id}")
    public User getUserByID(@PathVariable int id) {
        return userService.getUserByID(id);
    }

    @PostMapping("/users")
    public User addUser(@RequestBody UserDTO user) {
        return userService.addUser(user);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable int id, @RequestBody UserDTO updateUser) {
        return userService.updateUser(id, updateUser);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }

}
