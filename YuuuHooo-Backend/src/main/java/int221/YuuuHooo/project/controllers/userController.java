package int221.YuuuHooo.project.controllers;

import int221.YuuuHooo.project.Validation.UniqueValidator;
import int221.YuuuHooo.project.dtos.UserDTO;
import int221.YuuuHooo.project.dtos.UserHidePasswordDTO;
import int221.YuuuHooo.project.dtos.UserMatchPasswordDTO;
import int221.YuuuHooo.project.dtos.UserUpdateDTO;
import int221.YuuuHooo.project.entities.User;
import int221.YuuuHooo.project.repositories.userRepository;
import int221.YuuuHooo.project.services.userService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:5173", "http://intproj22.sit.kmutt.ac.th"})
public class userController {

    @Autowired
    private userService userService;

    @GetMapping("/users")
    public List<UserHidePasswordDTO> getUser() {
        return userService.getUser();
    }

    @GetMapping("/{id}")
    public UserHidePasswordDTO getUserByID(@PathVariable int id) {
        return userService.getUserByID(id);
    }

    @PostMapping("/users")
    public UserHidePasswordDTO addUser(@Valid @RequestBody UserDTO user) {
        return userService.addUser(user);
    }

    @PutMapping("/{id}")
    public UserHidePasswordDTO updateUser(@PathVariable int id, @Valid @RequestBody UserUpdateDTO updateUser, WebRequest request) {
        return userService.updateUser(id, updateUser, request);
    }

    @PostMapping("/match")
    public ResponseEntity<String> matchPassword(@RequestBody UserMatchPasswordDTO matchInfo){
        return userService.matchPassword(matchInfo);
    }


    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }


}
