package com.picpaysimplificado.infrastructure.in.web;

import com.picpaysimplificado.application.port.in.CreateUserUseCase;
import com.picpaysimplificado.application.port.in.GetAllUsersUseCase;
import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.infrastructure.in.dto.UserDTO;
import com.picpaysimplificado.infrastructure.in.dto.UserResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final GetAllUsersUseCase getAllUsersUseCase;

    public UserController(
            CreateUserUseCase createUserUseCase,
            GetAllUsersUseCase getAllUsersUseCase) {
//        System.out.println("🔥 USER CONTROLLER FOI CRIADO");
        this.createUserUseCase = createUserUseCase;
        this.getAllUsersUseCase = getAllUsersUseCase;
    }


    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(
            @RequestBody UserDTO userDTO) {

        User user = userDTO.toDomain();
        User newUser = createUserUseCase.createUser(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new UserResponseDTO(newUser));
    }

//    @GetMapping
//    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
//
//        System.out.println("🔥🔥🔥 GET /users FOI CHAMADO 🔥🔥🔥");
//
//        List<User> users = getAllUsersUseCase.getAllUsers();
//
//        List<UserResponseDTO> response = users.stream()
//                .map(UserResponseDTO::new)
//                .toList();
//
//        return ResponseEntity.ok(response);
//    }
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {

        List<User> users = getAllUsersUseCase.getAllUsers();
        List<UserResponseDTO> response = users.stream()
                .map(UserResponseDTO::new)
                .toList();

        return ResponseEntity.ok(response);
    }
}
