package com.picpaysimplificado.application.port.in;

import com.picpaysimplificado.domain.user.User;

public interface FindUserByIdUseCase {
    User findUserById(Long id);

}
