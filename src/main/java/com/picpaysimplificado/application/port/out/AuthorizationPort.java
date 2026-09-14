package com.picpaysimplificado.application.port.out;

import com.picpaysimplificado.domain.user.User;

import java.math.BigDecimal;

public interface AuthorizationPort {
    boolean authorizeTransaction(User sender, BigDecimal amount);
}