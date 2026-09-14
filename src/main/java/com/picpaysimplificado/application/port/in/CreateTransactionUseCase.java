package com.picpaysimplificado.application.port.in;

import com.picpaysimplificado.domain.transaction.Transaction;

import java.math.BigDecimal;

public interface CreateTransactionUseCase {
    Transaction createTransaction(Long senderId, Long receiverId, BigDecimal amount);
}
