package com.picpaysimplificado.infrastructure.out.kafka;

import com.picpaysimplificado.application.port.out.NotificationPort;
import com.picpaysimplificado.domain.user.User;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

//Pega uma solicitação da api e publicar uma mensagem no Kafka
@Component
public class KafkaNotificationAdapter implements NotificationPort {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaNotificationAdapter(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
}

    @Override
    public void sendNotification(User user, String message) {

        String notification = user.getEmail() + ": " + message;
        kafkaTemplate.send("notifications", notification);
    }
}
