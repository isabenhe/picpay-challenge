package com.picpaysimplificado.application.port.out;

import com.picpaysimplificado.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository {
    Transaction save(Transaction transaction);
}
