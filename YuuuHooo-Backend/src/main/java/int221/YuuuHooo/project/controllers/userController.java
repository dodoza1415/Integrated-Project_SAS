package int221.YuuuHooo.project.controllers;

import int221.YuuuHooo.project.Validation.UniqueValidator;
import int221.YuuuHooo.project.dtos.UserDTO;
import int221.YuuuHooo.project.dtos.UserHidePasswordDTO;
import int221.YuuuHooo.project.dtos.UserMatchPasswordDTO;
import int221.YuuuHooo.project.entities.User;
import int221.YuuuHooo.project.repositories.userRepository;
import int221.YuuuHooo.project.services.userService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
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
    public List<UserHidePasswordDTO> getUser() {
        return userService.getUser();
    }

    @GetMapping("/users/{id}")
    public UserHidePasswordDTO getUserByID(@PathVariable int id) {
        return userService.getUserByID(id);
    }

    @PostMapping("/users")
    public UserHidePasswordDTO addUser(@Valid @RequestBody UserDTO user) {
        return userService.addUser(user);
    }

    @PutMapping("/users/{id}")
    public UserHidePasswordDTO updateUser(@PathVariable int id,@Valid @RequestBody UserDTO updateUser) {
        return userService.updateUser(id, updateUser);
    }

    @PostMapping("/users/match")
    public ResponseEntity<String> matchPassword(@RequestBody UserMatchPasswordDTO matchInfo){
        return userService.matchPassword(matchInfo);
    }


    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }


}
