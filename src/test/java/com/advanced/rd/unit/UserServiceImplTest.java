package com.advanced.rd.unit;

import com.advanced.rd.dto.UserDTO;
import com.advanced.rd.entity.User;
import com.advanced.rd.repository.UserRepository;
import com.advanced.rd.service.UserServiceImpl;
import com.advanced.rd.util.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Spy
    private UserMapper userMapper;
    @InjectMocks
    private UserServiceImpl userService;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testCreateUser() {
        User user = new User();
        user.setId(1L);
        user.setEmail("Test");
        user.setName("Alex");

        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setEmail("Test");
        userDTO.setName("Alex");

        when(userRepository.save(any())).thenReturn(user);

        UserDTO resultDTO = userService.createUser(userDTO);
        verify(userRepository, times(1)).save(any(User.class));
        assertEquals(1L, resultDTO.getId());
        verify(userMapper, times(1)).toEntity(userDTO);
        verify(userMapper, times(1)).toDTO(user);
    }

    @Test
    public void testGetUser() {
        User user = new User();
        user.setId(1L);
        user.setEmail("Test");
        user.setName("Alex");

        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setEmail("Test");
        userDTO.setName("Alex");

        given(userRepository.findById(1L)).willReturn(Optional.of(user));

        Optional<UserDTO> resultDTO = userService.getUser(1L);

        assertTrue(resultDTO.isPresent());
        assertEquals(1L, resultDTO.get().getId());

        verify(userMapper, times(1)).toDTO(user);
    }

    @Test
    public void testGetUserName() {
        User user = new User();
        user.setId(1L);
        user.setEmail("Test");
        user.setName("Alex");

        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setEmail("Test");
        userDTO.setName("Alex");

        given(userRepository.findByName(user.getName())).willReturn(Optional.of(user));

        Optional<UserDTO> resultDTO = userService.getUserName(user.getName());

        assertTrue(resultDTO.isPresent());
        assertEquals("Alex", resultDTO.get().getName());

        verify(userMapper, times(1)).toDTO(user);
    }

    @Test
    public void testGetUserEmail() {
        User user = new User();
        user.setId(1L);
        user.setEmail("Test");
        user.setName("Alex");

        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setEmail("Test");
        userDTO.setName("Alex");

        given(userRepository.findByEmail(user.getEmail())).willReturn(Optional.of(user));

        Optional<UserDTO> resultDTO = userService.getUserEmail(user.getEmail());

        assertTrue(resultDTO.isPresent());
        assertEquals("Test", resultDTO.get().getEmail());

        verify(userMapper, times(1)).toDTO(user);
    }

}
