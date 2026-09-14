package com.picpaysimplificado.infrastructure.out.kafka;

import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.domain.user.UserType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class KafkaNotificationAdapterTest {

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @InjectMocks
    private KafkaNotificationAdapter adapter;

    @Test
    void shouldSendNotification() {
        User user = new User(1L, "Isadora", "Maria", "9999999992", "IsaM@email.com", "12343M", new BigDecimal("100"), UserType.COMMON);


        adapter.sendNotification(user,
                "Transação realizada com sucesso");

        verify(kafkaTemplate).send(
                "notifications",
                "IsaM@email.com: Transação realizada com sucesso");
    }
}