package com.picpaysimplificado.controllers;

import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.dtos.UserDTO;
import com.picpaysimplificado.dtos.UserResponseDTO;
import com.picpaysimplificado.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserDTO user) {
        User newUser = userService.createUser(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new UserResponseDTO(newUser));
    }

//    @GetMapping
//    public ResponseEntity<List<User>> getAllUsers(){
//        List<User>users = this.userService.getAllUsers();
//        return new ResponseEntity<>(users, HttpStatus.OK);
//    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<User> users = this.userService.getAllUsers();
        List<UserResponseDTO> response =
                users.stream()//percorrer a lista sem usar o antigo (tempos de legado) (user.getName());
                        .map(UserResponseDTO::new) //transforma um elemento em outro response.add(new UserResponseDTO(user));
                        .toList();
        return ResponseEntity.ok(response);
    }
}
