package com.picpaysimplificado.application.port.in;

import com.picpaysimplificado.domain.user.User;

public interface CreateUserUseCase {
    User createUser(User user);

}
