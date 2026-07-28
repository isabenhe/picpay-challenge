# PicPay Simplificado

Implementação do desafio técnico do PicPay utilizando **Java 21** e **Spring Boot**, com foco em boas práticas de desenvolvimento, arquitetura em camadas, testes unitários e integração com serviço externo de autorização.

## Objetivo

Desenvolver uma API REST para simular transferências financeiras entre usuários, aplicando conceitos de desenvolvimento backend com Spring Boot, persistência de dados, validações de regras de negócio e testes unitários.

---

## Tecnologias

- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- H2 Database
- Maven
- Lombok
- JUnit 5
- Mockito
- Postman

---

## Conceitos aplicados

- API REST
- Arquitetura em Camadas
- Injeção de Dependência
- DTO Pattern
- Spring Data JPA
- Tratamento Global de Exceções
- Validações de Regras de Negócio
- Consumo de API Externa
- Testes Unitários com JUnit 5 e Mockito

---

## Funcionalidades

- Cadastro de usuários
- Consulta de usuários
- Atualização de usuários
- Exclusão de usuários
- Transferências entre usuários
- Validação de saldo
- Validação do tipo de usuário (Comum e Lojista)
- Integração com serviço externo de autorização
- Tratamento global de exceções
- Utilização de DTOs para Request e Response
- Testes unitários com JUnit e Mockito

---

## Arquitetura

O projeto foi desenvolvido utilizando arquitetura em camadas, separando responsabilidades para facilitar manutenção e evolução da aplicação.

```
src
├── controller
├── service
├── repository
├── entity
├── dto
├── exception
├── config
└── client
```

---

## Endpoints

### Users

| Método | Endpoint | Descrição |
|---------|----------|-----------|
| POST | `/users` | Cadastra um usuário |
| GET | `/users` | Lista todos os usuários |
| GET | `/users/{id}` | Busca usuário por ID |
| PUT | `/users/{id}` | Atualiza um usuário |
| DELETE | `/users/{id}` | Remove um usuário |

### Transactions

| Método | Endpoint | Descrição |
|---------|----------|-----------|
| POST | `/transactions` | Realiza uma transferência |

---

## Banco de Dados

O projeto utiliza o **H2 Database** em memória para facilitar o desenvolvimento e os testes.

### Console H2

```
http://localhost:8080/h2-console
```

Exemplo de configuração:

```
JDBC URL: jdbc:h2:mem:testdb
User: sa
Password:
```

---

## Como executar
Clone o repositório:

```bash
git clone https://github.com/SEU-USUARIO/picpay-simplificado.git
```

Entre na pasta do projeto:

```bash
cd picpay-simplificado
```

Execute a aplicação:

```bash
mvn spring-boot:run
```

A aplicação ficará disponível em:

```
http://localhost:8080
```

---

## Testes
Os testes unitários foram desenvolvidos utilizando:

- JUnit 5
- Mockito

Para executar os testes:

```bash
mvn test
```

---

## Próximos passos

- [ ] Spring Security
- [ ] JWT
- [ ] PostgreSQL
- [ ] Swagger/OpenAPI
- [ ] Docker
- [ ] Deploy
- [ ] Angular

---

## Melhorias Futuras

- [ ] Paginação de usuários
- [ ] MapStruct
- [ ] JavaDoc
- [ ] Cache com Redis
- [ ] Mensageria com RabbitMQ ou Kafka
- [ ] Testes de Integração
- [ ] Testcontainers

---

## Autor

**Isabelle F. **
Desenvolvido para fins de estudo e aprimoramento.
