package com.picpaysimplificado.application.service;

import com.picpaysimplificado.application.port.in.CreateUserUseCase;
import com.picpaysimplificado.application.port.in.FindUserByIdUseCase;
import com.picpaysimplificado.application.port.in.GetAllUsersUseCase;
import com.picpaysimplificado.application.port.out.UserRepository;
import com.picpaysimplificado.domain.exception.UserNotFoundException;
import com.picpaysimplificado.domain.user.User;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService
        implements CreateUserUseCase, FindUserByIdUseCase, GetAllUsersUseCase {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User createUser(User user) {
        return repository.save(user);
    }

    @Override
    public User findUserById(Long id) {
        return repository.findById(id).orElseThrow(()
                -> new UserNotFoundException("Usuário não encontrado"));
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }
}