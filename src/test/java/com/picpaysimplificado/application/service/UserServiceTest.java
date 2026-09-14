package com.picpaysimplificado.application.service;

import com.picpaysimplificado.application.port.out.UserRepository;
import com.picpaysimplificado.domain.exception.UserNotFoundException;
import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.domain.user.UserType;
import com.picpaysimplificado.infrastructure.in.dto.UserDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void createUserCase1() {
        User user = new User(1L, "Isadora", "Maria", "9999999992", "IsaM@email.com", "12343M", new BigDecimal("100"), UserType.COMMON);

        when(userRepository.save(user)).thenReturn(user);
        User result = userService.createUser(user);
        assertNotNull(result);
        verify(userRepository).save(user);

    }

    @Test
    void createUserCase2() {
        User user = new User();

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        User findUserById = userService.findUserById(user.getId());

        assertNotNull(findUserById);
        verify(userRepository).findById(user.getId());

    }

}