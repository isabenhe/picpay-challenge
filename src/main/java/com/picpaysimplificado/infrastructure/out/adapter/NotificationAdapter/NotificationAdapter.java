package com.picpaysimplificado.infrastructure.out.adapter.NotificationAdapter;

import com.picpaysimplificado.application.port.out.NotificationPort;
import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.infrastructure.in.dto.NotificationDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NotificationAdapter implements NotificationPort {

    private final RestTemplate restTemplate;

    public NotificationAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void sendNotification(User user, String message) {
        try {
            String email = user.getEmail();
            NotificationDTO notificationRequest = new NotificationDTO(email, message);
            ResponseEntity<String> notificationResponse = restTemplate.postForEntity(
                    "https://util.devi.tools/api/v1/notify", notificationRequest, String.class);

            if (notificationResponse.getStatusCode() != HttpStatus.OK) {
                System.out.println("Serviço de notificação indisponível");
            }

        } catch (Exception e) {
            System.out.println("Falha ao enviar notificação");
        }
    }
}
