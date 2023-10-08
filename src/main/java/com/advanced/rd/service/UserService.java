package com.advanced.rd.service;

import com.advanced.rd.dto.UserDTO;
import com.advanced.rd.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);

    Optional<UserDTO> getUser(Long id);

    Optional<UserDTO> getUserName(String name);

    Optional<UserDTO> getUserEmail(String email);

    UserDTO updateUser(Long id, UserDTO userDTO);

    void deleteUser(Long id);

    List<UserDTO> findAllUsers();
}
