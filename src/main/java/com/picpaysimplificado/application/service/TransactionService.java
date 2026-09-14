package com.picpaysimplificado.application.service;

import com.picpaysimplificado.application.port.in.CreateTransactionUseCase;
import com.picpaysimplificado.application.port.out.AuthorizationPort;
import com.picpaysimplificado.application.port.out.NotificationPort;
import com.picpaysimplificado.application.port.out.TransactionRepository;
import com.picpaysimplificado.application.port.out.UserRepository;
import com.picpaysimplificado.domain.exception.TransactionNotAllowedException;
import com.picpaysimplificado.domain.exception.UserNotFoundException;
import com.picpaysimplificado.domain.transaction.Transaction;
import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.domain.user.UserType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransactionService implements CreateTransactionUseCase {

    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final AuthorizationPort authorizationPort;
    private final NotificationPort notificationPort;

    public TransactionService(
            UserRepository userRepository,
            TransactionRepository transactionRepository,
            AuthorizationPort authorizationPort,
            NotificationPort notificationPort) {

        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
        this.authorizationPort = authorizationPort;
        this.notificationPort = notificationPort;
    }

    @Override
    public Transaction createTransaction(Long senderId, Long receiverId, BigDecimal amount) {

        User sender = userRepository.findById(senderId).orElseThrow(()
                -> new UserNotFoundException("Usuário remetente não encontrado"));

        User receiver = userRepository.findById(receiverId).orElseThrow(()
                ->  new UserNotFoundException("Usuário receptor não encontrado"));

        validateTransaction(sender, amount);

        boolean authorized = authorizationPort.authorizeTransaction(sender, amount);

        if (!authorized) {
            throw new TransactionNotAllowedException("Transação não autorizada");
        }

        Transaction transaction = new Transaction(null, amount, sender, receiver, LocalDateTime.now());
        sender.setBalance(sender.getBalance().subtract(amount));
        receiver.setBalance(receiver.getBalance().add(amount));

        transactionRepository.save(transaction);
        userRepository.save(sender);
        userRepository.save(receiver);

        notificationPort.sendNotification(sender, "Transação realizada com sucesso");
        notificationPort.sendNotification(receiver, "Transação recebida com sucesso");

        return transaction;
    }

    private void validateTransaction(User sender, BigDecimal amount) {

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new TransactionNotAllowedException("O valor da transação deve ser maior que zero");
        }

        if (sender.getUserType() == UserType.MERCHANT) {
            throw new TransactionNotAllowedException("Usuário do tipo Lojista não está autorizado a realizar transação");
        }

        if (sender.getBalance().compareTo(amount) < 0) {
            throw new TransactionNotAllowedException("Saldo insuficiente");
        }
    }
}