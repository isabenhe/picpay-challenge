package com.picpaysimplificado.infrastructure.in.dto;

import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.domain.user.UserType;

import java.math.BigDecimal;

public record UserDTO(
        String firstName,
        String lastName,
        String document,
        String email,
        String password,
        BigDecimal balance,
        UserType userType
) {

    public User toDomain() {
        return new User(
                null,
                firstName,
                lastName,
                document,
                email,
                password,
                balance,
                userType
        );
    }
}