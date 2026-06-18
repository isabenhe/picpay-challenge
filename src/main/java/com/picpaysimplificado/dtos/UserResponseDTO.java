package com.picpaysimplificado.dtos;

import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.domain.user.UserType;

import java.math.BigDecimal;

public record UserResponseDTO(
        Long id,
        String firstName,
        String lastName,
        String document,
        BigDecimal balance,
        String email,
        UserType userType ) {

    public UserResponseDTO(User user){
        this(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getDocument(),
                user.getBalance(),
                user.getEmail(),
                user.getUserType());
    }
}