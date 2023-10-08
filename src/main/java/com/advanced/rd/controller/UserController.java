package com.advanced.rd.controller;

import com.advanced.rd.dto.UserDTO;
import com.advanced.rd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    //Test connection
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/info")
    public String getUser() {
        return "Hello user";
    }

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    // Створити користувача
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/requestBody")
    public ResponseEntity<UserDTO> postUser(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(service.createUser(userDTO));
    }

    // Отримати користувача по id
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/request/")
    public ResponseEntity<Optional<UserDTO>> getByIdParam(@RequestParam("userId") Long userId) {
        return ResponseEntity.ok(service.getUser(userId));
    }

    // Отримати користувача на ім'я
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/request/")
    public ResponseEntity<Optional<UserDTO>> getByNameParam(@RequestParam("name") String name) {
        return ResponseEntity.ok(service.getUserName(name));
    }

    // Отримати користувача за електронною адресою
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/request/")
    public ResponseEntity<Optional<UserDTO>> getByEmailParam(@RequestParam("email") String email) {
        return ResponseEntity.ok(service.getUserEmail(email));
    }

    // Оновити користувача
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/update")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(service.updateUser(userDTO.getId(), userDTO));
    }

    // Видалити користувача (не розумію, як реалізувати)

 /*   @PreAuthorize("hasRole('USER')")
    @PostMapping("/delete")
    public ResponseEntity<Long> deleteUser(@RequestBody Long userId){
        return ResponseEntity.ok(service.deleteUser(userId));
    }*/
}
