package com.picpaysimplificado.application.service;

import com.picpaysimplificado.application.port.out.AuthorizationPort;
import com.picpaysimplificado.application.port.out.NotificationPort;
import com.picpaysimplificado.application.port.out.TransactionRepository;
import com.picpaysimplificado.application.port.out.UserRepository;
import com.picpaysimplificado.domain.exception.TransactionNotAllowedException;
import com.picpaysimplificado.domain.transaction.Transaction;
import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.domain.user.UserType;
import com.picpaysimplificado.infrastructure.in.dto.TransactionDTO;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AuthorizationPort authorizationPort;

    @Mock
    private NotificationPort notificationPort;

    @InjectMocks
    private TransactionService transactionService;

    @Test
    @DisplayName("Deve criar a transação com sucesso quando todos os dados forem válidos")
    void createTransactionCase1() {
        User sender = new User(1L, "Isadora", "Maria", "9999999992", "IsaM@email.com", "12343M", new BigDecimal("100"), UserType.COMMON);
        User receiver = new User(2L, "Gab", "Ferreb", "9999999993", "GabFerr@email.com", "12343G", new BigDecimal("100"), UserType.COMMON);

        when(userRepository.findById(1L)).thenReturn(Optional.of(sender));
        when(userRepository.findById(2L)).thenReturn(Optional.of(receiver));
        //   when(userRepository.findById(1L)).thenReturn(Optional.empty());

        when(authorizationPort.authorizeTransaction(any(), any())).thenReturn(true);

        TransactionDTO request = new TransactionDTO(1L, 2L, new BigDecimal("10"));
        Transaction transaction = transactionService.createTransaction(request.senderId(), request.receiverId(), request.value());

        assertNotNull(transaction);

        assertEquals(new BigDecimal("90"), sender.getBalance());
        assertEquals(new BigDecimal("110"), receiver.getBalance());

        verify(transactionRepository).save(transaction);
        verify(userRepository).save(sender);
        verify(userRepository).save(receiver);

        verify(notificationPort).sendNotification(sender, "Transação realizada com sucesso");
        verify(notificationPort).sendNotification(receiver, "Transação recebida com sucesso");
    }


    @Test
    @DisplayName("Deve lançar uma exceção quando a transação não tiver permitida")
    void createTransactionCase2() {
        User sender = new User(1L, "Isadora", "Maria", "9999999992", "IsaM@email.com", "12343M", new BigDecimal("100"), UserType.COMMON);
        User receiver = new User(2L, "Gab", "Ferreb", "9999999993", "GabFerr@email.com", "12343G", new BigDecimal("100"), UserType.COMMON);

        when(userRepository.findById(1L)).thenReturn(Optional.of(sender));
        when(userRepository.findById(2L)).thenReturn(Optional.of(receiver));

        when(authorizationPort.authorizeTransaction(any(), any())).thenReturn(false);

        Exception thrown = Assertions.assertThrows(TransactionNotAllowedException.class, () -> {
            TransactionDTO request = new TransactionDTO(1L, 2L, new BigDecimal("10"));
            Transaction transaction = transactionService.createTransaction(request.senderId(), request.receiverId(), request.value());
        });

        Assertions.assertEquals("Transação não autorizada", thrown.getMessage());

    }
}