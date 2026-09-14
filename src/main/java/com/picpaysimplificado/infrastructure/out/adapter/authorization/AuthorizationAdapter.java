package com.picpaysimplificado.infrastructure.out.adapter.authorization;

import com.picpaysimplificado.application.port.out.AuthorizationPort;
import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.domain.user.UserType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public class AuthorizationAdapter implements AuthorizationPort {

    @Override
    public boolean authorizeTransaction(User sender, BigDecimal amount) {

        return amount.compareTo(new BigDecimal("1000")) <= 0;
    }
}


//    private final RestTemplate restTemplate;
//
//    public AuthorizationAdapter(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
//
//    @Override
//    public boolean authorizeTransaction(User sender, BigDecimal amount) {
//        try {
//            ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity(
//                    "https://util.devi.tools/api/v2/authorize", Map.class);
//
//            if (authorizationResponse.getStatusCode() == HttpStatus.OK) {
//                Map<String, Object> body = authorizationResponse.getBody();
//                Map<String, Object> data = (Map<String, Object>) body.get("data");
//                return (Boolean) data.get("authorization");
//            }
//
//        } catch (HttpClientErrorException e) {
//            return false;
//        }
//        return false;
//    }
//}