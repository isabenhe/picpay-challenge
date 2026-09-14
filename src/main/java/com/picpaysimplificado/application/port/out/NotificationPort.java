package com.picpaysimplificado.application.port.out;

import com.picpaysimplificado.domain.user.User;

public interface NotificationPort {
    void sendNotification(User user, String message);
}