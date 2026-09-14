package com.picpaysimplificado.application.port.out;

import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.domain.user.UserType;
import com.picpaysimplificado.infrastructure.in.dto.UserDTO;
import com.picpaysimplificado.infrastructure.in.dto.UserResponseDTO;
import com.picpaysimplificado.infrastructure.out.persistence.entity.UserEntity;
import com.picpaysimplificado.infrastructure.out.persistence.repository.UserJpaRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//integraçao
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    UserJpaRepository userRepository;

    @Test
    @DisplayName("deve retornar o usuário do banco de dados com sucesso")
    void findByDocumentCase1() {
        String document = "9999999001";
        UserDTO data = new UserDTO("Isabelle","Teste", document, "Test@email.com", "9999", new BigDecimal(45), UserType.COMMON);
        this.createUser(data);

        Optional<UserEntity> result = this.userRepository.findUserByDocument(document);
        assertThat(result.isPresent()).isTrue();
    }

    @Test
    @DisplayName("não deve obter o usuário do banco de dados quando o usuário não existir")
    void findByDocumentCase2() {
        String document = "9999999002";

        Optional<UserEntity> result = this.userRepository.findUserByDocument(document);
        assertThat(result.isEmpty()).isTrue();
    }

    private UserEntity createUser(UserDTO data) {
        UserEntity userEntity = new UserEntity(data);
        entityManager.persist(userEntity);
        return userEntity;
    }

}