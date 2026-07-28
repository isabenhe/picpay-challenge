package com.picpaysimplificado.services;

import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.domain.user.UserType;
import com.picpaysimplificado.dtos.TransactionDTO;
import com.picpaysimplificado.exception.TransactionNotAllowedException;
import com.picpaysimplificado.repository.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class TransactionServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private TransactionRepository repository;

    @Mock
    private AuthorizationService authService;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    @Autowired
    private TransactionService transactionService;

    //Executar antes dos testes, iniciar os mocks
    @BeforeEach
    void setup(){       // obsoleto?
        MockitoAnnotations.initMocks(this);
    }

    @Test       //Deve criar a transação com sucesso quando todos os dados forem válidos
    @DisplayName("Should create transaction successfully when everything is OK")
    void createTransactionCase1() throws Exception {
        User sender = new User(1L, "Isadora", "Maria", "9999999992", "IsaM@email.com", "12343M", new BigDecimal(20), UserType.COMMON);
        User receiver = new User(2L, "Gab", "Ferreb", "9999999993", "GabFerr@email.com", "12343G", new BigDecimal(30), UserType.COMMON);

        when(userService.findUserById(1L)).thenReturn(sender);
        when(userService.findUserById(2L)).thenReturn(receiver);

        when(authService.authorizeTransaction(any(), any())).thenReturn(true);

        TransactionDTO request = new TransactionDTO(new BigDecimal(10), 1L, 2L);
        transactionService.createTransaction(request);

        verify(repository, times(1)).save(any());

        sender.setBalance(new BigDecimal(10));
        verify(userService, times(1)).saveUser(sender);

        receiver.setBalance(new BigDecimal(40));
        verify(userService, times(1)).saveUser(sender);

        verify(notificationService, times(1)).sendNotification(sender, "transação realizada com sucesso");
        verify(notificationService, times(1)).sendNotification(receiver, "transação recebida com sucesso");


    }

    @Test       //Deve lançar uma exceção quando a transação não tiver permitida
    @DisplayName("Should throw Exception when Transaction is not allowed")
    void createTransactionCase2() throws Exception {
        User sender = new User(1L, "Isadora", "Maria", "9999999992", "IsaM@email.com", "12343M", new BigDecimal(20), UserType.COMMON);
        User receiver = new User(2L, "Gab", "Ferreb", "9999999993", "GabFerr@email.com", "12343G", new BigDecimal(30), UserType.COMMON);

        when(userService.findUserById(1L)).thenReturn(sender);
        when(userService.findUserById(2L)).thenReturn(receiver);

        when(authService.authorizeTransaction(any(), any())).thenReturn(false);

        Exception thrown = Assertions.assertThrows(TransactionNotAllowedException.class,() -> {
            TransactionDTO request = new TransactionDTO(new BigDecimal(10), 1L, 2L);
            transactionService.createTransaction(request);
        });

        Assertions.assertEquals("Transação não autorizada", thrown.getMessage());
    }
}