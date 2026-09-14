package com.picpaysimplificado.infrastructure.in.web;

import com.picpaysimplificado.application.port.in.CreateTransactionUseCase;
import com.picpaysimplificado.domain.transaction.Transaction;
import com.picpaysimplificado.infrastructure.in.dto.TransactionDTO;
import com.picpaysimplificado.infrastructure.in.dto.TransactionResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final CreateTransactionUseCase createTransactionUseCase;

    public TransactionController(CreateTransactionUseCase createTransactionUseCase) {
        this.createTransactionUseCase = createTransactionUseCase;
    }

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> createTransaction(
            @RequestBody TransactionDTO transactionDTO) {
        Transaction transaction = createTransactionUseCase.createTransaction(
                transactionDTO.senderId(),
                transactionDTO.receiverId(),
                transactionDTO.value());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new TransactionResponseDTO(transaction));
    }
}