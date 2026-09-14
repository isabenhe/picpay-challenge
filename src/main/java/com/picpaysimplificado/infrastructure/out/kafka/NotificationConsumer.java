package com.picpaysimplificado.infrastructure.out.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {

    @KafkaListener(
            topics = "notifications",
            groupId = "notification-service"
    )
    public void consume(String message) {
        System.out.println("Mensagem recebida: " + message);
    }
}
