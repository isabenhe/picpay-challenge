package com.picpaysimplificado.domain.transaction;

import com.picpaysimplificado.domain.user.User;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    private Long id;
    private BigDecimal amount;
    private User sender;
    private User receiver;
    private LocalDateTime timestamp;
}
