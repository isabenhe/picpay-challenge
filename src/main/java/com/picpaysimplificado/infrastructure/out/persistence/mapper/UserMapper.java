//package com.picpaysimplificado.infrastructure.out.persistence.mapper;
//
//import com.picpaysimplificado.domain.user.User;
//import com.picpaysimplificado.infrastructure.in.dto.UserDTO;
//import com.picpaysimplificado.infrastructure.out.persistence.entity.UserEntity;
//
//public class UserMapper {
//
//    public static UserEntity toEntity(User user) {
//        UserEntity userEntity = new UserEntity();
//        userEntity.setId(user.getId());
//        userEntity.setFirstName(user.getFirstName());
//        userEntity.setLastName(user.getLastName());
//        userEntity.setDocument(user.getDocument());
//        userEntity.setEmail(user.getEmail());
//        userEntity.setPassword(user.getPassword());
//        userEntity.setBalance(user.getBalance());
//        userEntity.setUserType(user.getUserType());
//        return userEntity;
//    }
//
//    public static User toDomain(@org.checkerframework.checker.nullness.qual.MonotonicNonNull UserDTO userEntity) {
//        User user = new User();
//        user.setId(userEntity.getId());
//        user.setFirstName(userEntity.getFirstName());
//        user.setLastName(userEntity.getLastName());
//        user.setDocument(userEntity.getDocument());
//        user.setEmail(userEntity.getEmail());
//        user.setPassword(userEntity.getPassword());
//        user.setBalance(userEntity.getBalance());
//        user.setUserType(userEntity.getUserType());
//        return user;
//    }
//
//
//
//}
