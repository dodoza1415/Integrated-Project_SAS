package int221.nw2.project.yuuuhooo.controllers;

import int221.nw2.project.yuuuhooo.dtos.UserDTO;
import int221.nw2.project.yuuuhooo.dtos.UserHidePasswordDTO;
import int221.nw2.project.yuuuhooo.dtos.UserMatchPasswordDTO;
import int221.nw2.project.yuuuhooo.dtos.UserUpdateDTO;
import int221.nw2.project.yuuuhooo.services.userService;
import jakarta.validation.Valid;
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

    @GetMapping("/users/{id}")
    public UserHidePasswordDTO getUserByID(@PathVariable int id) {
        return userService.getUserByID(id);
    }

    @PostMapping("/users")
    public UserHidePasswordDTO addUser(@Valid @RequestBody UserDTO user) {
        return userService.addUser(user);
    }

    @PutMapping("/users/{id}")
    public UserHidePasswordDTO updateUser(@PathVariable int id, @Valid @RequestBody UserUpdateDTO updateUser, WebRequest request) {
        return userService.updateUser(id, updateUser, request);
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
