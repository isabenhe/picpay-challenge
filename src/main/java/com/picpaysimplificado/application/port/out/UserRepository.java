package com.picpaysimplificado.application.port.out;

import com.picpaysimplificado.domain.user.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User save(User user);
    List<User> findAll();
    Optional<User> findById(Long id);
    Optional<User> findByDocument(String document);
}