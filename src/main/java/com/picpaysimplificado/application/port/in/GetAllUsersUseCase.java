package com.picpaysimplificado.application.port.in;

import com.picpaysimplificado.domain.user.User;

import java.util.List;

public interface GetAllUsersUseCase {
        List<User> getAllUsers();

}
