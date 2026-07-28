package com.picpaysimplificado.services;

import com.picpaysimplificado.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class AuthorizationService {

    @Autowired // Cliente HTTP responsável pela comunicação com APIs externas
    private RestTemplate restTemplate;

    public boolean authorizeTransaction(User sender, BigDecimal value) {
        try {
            ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", Map.class);
            if (authorizationResponse.getStatusCode() == HttpStatus.OK) {
                Map<String, Object> body = authorizationResponse.getBody();
                Map<String, Object> data = (Map<String, Object>) body.get("data");
                return (Boolean) data.get("authorization");
            }
            // Trata respostas 403 da API externa como transações não autorizadas
        } catch (HttpClientErrorException e) {
            return false;
        }  return false;
    }


}


