package com.advanced.rd.service;

import com.advanced.rd.dto.UserDTO;
import com.advanced.rd.entity.User;
import com.advanced.rd.repository.UserRepository;
import com.advanced.rd.util.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }
    @Transactional
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user = userRepository.save(user);
        return userMapper.toDTO(user);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<UserDTO> getUser(Long id) {
        return userRepository.findById(id).map(userMapper::toDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<UserDTO> getUserName(String name) {
        return userRepository.findByName(name).map(userMapper::toDTO);
    }
    @Transactional(readOnly = true)
    @Override
    public Optional<UserDTO> getUserEmail(String email) {
        return userRepository.findByEmail(email).map(userMapper::toDTO);
    }

    @Transactional
    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        if (userRepository.existsById(id)) {
            User user = userMapper.toEntity(userDTO);
            user.setId(id);
            user = userRepository.save(user);
            return userMapper.toDTO(user);
        }
        return null;
    }
    @Transactional
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ)
    @Override
    public List<UserDTO> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }
}
