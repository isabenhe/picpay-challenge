package com.picpaysimplificado.infrastructure.out.adapter;

import com.picpaysimplificado.application.port.out.UserRepository;
import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.infrastructure.out.persistence.entity.UserEntity;
import com.picpaysimplificado.infrastructure.out.persistence.repository.UserJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class UserPersistenceAdapter implements UserRepository {
    private final UserJpaRepository userJpaRepository;

    public UserPersistenceAdapter(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public User save(User user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        entity.setDocument(user.getDocument());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        entity.setBalance(user.getBalance());
        entity.setUserType(user.getUserType());
        UserEntity savedEntity = userJpaRepository.save(entity);

        return toDomain(savedEntity);
    }

    @Override
    public List<User> findAll() {
        return userJpaRepository.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userJpaRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public Optional<User> findByDocument(String document) {
        return userJpaRepository.findUserByDocument(document)
                .map(this::toDomain);
    }

    private User toDomain(UserEntity entity) {
        User user = new User();
        user.setId(entity.getId());
        user.setFirstName(entity.getFirstName());
        user.setLastName(entity.getLastName());
        user.setDocument(entity.getDocument());
        user.setEmail(entity.getEmail());
        user.setPassword(entity.getPassword());
        user.setBalance(entity.getBalance());
        user.setUserType(entity.getUserType());

        return user;
    }
}