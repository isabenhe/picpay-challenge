package com.picpaysimplificado.infrastructure.out.persistence.repository;

import com.picpaysimplificado.infrastructure.out.persistence.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionJpaRepository
        extends JpaRepository<TransactionEntity, Long> {
}
