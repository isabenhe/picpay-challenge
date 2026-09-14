package com.picpaysimplificado.infrastructure.out.adapter;


import com.picpaysimplificado.application.port.out.TransactionRepository;
import com.picpaysimplificado.domain.transaction.Transaction;
import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.infrastructure.out.persistence.entity.TransactionEntity;
import com.picpaysimplificado.infrastructure.out.persistence.entity.UserEntity;
import com.picpaysimplificado.infrastructure.out.persistence.repository.TransactionJpaRepository;
import org.springframework.stereotype.Component;

@Component
public class TransactionPersistenceAdapter
        implements TransactionRepository {

    private final TransactionJpaRepository transactionJpaRepository;

    public TransactionPersistenceAdapter(
            TransactionJpaRepository transactionJpaRepository) {
        this.transactionJpaRepository = transactionJpaRepository;
    }

    @Override
    public Transaction save(Transaction transaction) {
        TransactionEntity entity = new TransactionEntity();

        entity.setId(transaction.getId());
        entity.setAmount(transaction.getAmount());
        entity.setTimestamp(transaction.getTimestamp());

        UserEntity sender = new UserEntity();
        sender.setId(transaction.getSender().getId());

        UserEntity receiver = new UserEntity();
        receiver.setId(transaction.getReceiver().getId());
        entity.setSender(sender);
        entity.setReceiver(receiver);

        TransactionEntity saved = transactionJpaRepository.save(entity);

        return toDomain(saved);
    }

    private Transaction toDomain(TransactionEntity entity) {
        User sender = new User();
        sender.setId(entity.getSender().getId());

        User receiver = new User();
        receiver.setId(entity.getReceiver().getId());
        return new Transaction(
                entity.getId(),
                entity.getAmount(),
                sender,
                receiver,
                entity.getTimestamp()
        );
    }
}