package com.picpaysimplificado.infrastructure.in.dto;

import com.picpaysimplificado.domain.transaction.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionResponseDTO(
        Long id,
        BigDecimal amount,
        Long senderId,
        Long receiverId,
        LocalDateTime timestamp
) {

    public TransactionResponseDTO(Transaction transaction) {
        this(
                transaction.getId(),
                transaction.getAmount(),
                transaction.getSender().getId(),
                transaction.getReceiver().getId(),
                transaction.getTimestamp()
        );
    }
}
