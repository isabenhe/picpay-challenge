package com.picpaysimplificado.services;

import com.picpaysimplificado.domain.transaction.Transaction;
import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.dtos.TransactionDTO;
import com.picpaysimplificado.exception.TransactionNotAllowedException;
import com.picpaysimplificado.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {
    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository repository;

    @Autowired //comunicaçoes http entre serviços (get, post,put)
    private RestTemplate restTemplate;

    @Autowired
    private NotificationService notificationService;

    //pegar o user pra fazer a validação sobre ele
    public Transaction createTransaction(TransactionDTO transaction) throws Exception {
        User sender = this.userService.findUserById(transaction.senderId());
        User receiver = this.userService.findUserById(transaction.receiverId());

        userService.validateTransaction(sender, transaction.value());

       boolean isAuthorized = this.authorizeTransaction(sender, transaction.value());
        if(!isAuthorized){
            throw new TransactionNotAllowedException ("Transação não autorizada");
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transaction.value());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        receiver.setBalance(receiver.getBalance().add(transaction.value()));

        this.repository.save(newTransaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);

        this.notificationService.sendNotification(sender, "transação realizada com sucesso");
        this.notificationService.sendNotification(receiver, "transação recebida com sucesso");

        return newTransaction;
    }


//    public boolean authorizeTransaction (User sender, BigDecimal value){
//        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", Map.class);
//
//        if (authorizationResponse.getStatusCode() == HttpStatus.OK) {
//            String message = (String) authorizationResponse.getBody().get("message");
//            return "Autorizado".equalsIgnoreCase(message);
//        } else return false;
//    }

//    public boolean authorizeTransaction (User sender, BigDecimal value){
//     try {ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", Map.class);
//     System.out.println(authorizationResponse.getBody());
//         if (authorizationResponse.getStatusCode() == HttpStatus.OK) {
//            String message = (String) authorizationResponse.getBody().get("message");
//            return "Autorizado".equalsIgnoreCase(message);
//            }
//        } catch (HttpClientErrorException e) {
//         return false;
//        }   return false;
//    }
    
    public boolean authorizeTransaction(User sender, BigDecimal value) {
     try {ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", Map.class);
    	if (authorizationResponse.getStatusCode() == HttpStatus.OK) {
                Map<String, Object> body = authorizationResponse.getBody();
                Map<String, Object> data = (Map<String, Object>) body.get("data");
                return (Boolean) data.get("authorization");
            }
        		} catch (HttpClientErrorException e) {
            return false;
        }  return false;
    }
}
