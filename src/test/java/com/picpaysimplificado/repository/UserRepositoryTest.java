package com.picpaysimplificado.repository;

import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.domain.user.UserType;
import com.picpaysimplificado.dtos.UserDTO;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

//utilizar H2
@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    @Test       //deve retornar o usuário do banco de dados com sucesso
    @DisplayName("should return get User successfully from DB")
    void findUserByDocumentCase1() {
        String document = "99999999901";
        UserDTO data = new UserDTO("Isabelle","Teste", document, new BigDecimal(45), "Test@email.com", "9999", UserType.COMMON);
        this.createUser(data);

        Optional<User> result = this.userRepository.findUserByDocument(document);

        // metodo do opcional isPresent
        assertThat(result.isPresent()).isTrue();

    }

    @Test       //não deve obter o usuário do banco de dados quando o usuário não existir
    @DisplayName("should not get User from DB when user not exists")
    void findUserByDocumentCase2() {
        String document = "99999999901";

        Optional<User> result = this.userRepository.findUserByDocument(document);

        assertThat(result.isEmpty()).isTrue();
    }

    private User createUser(UserDTO data){
        User newUser = new User(data);
        entityManager.persist(newUser);
        return newUser;
    }


}