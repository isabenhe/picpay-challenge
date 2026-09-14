# PicPay Simplificado

Implementação do desafio técnico do PicPay utilizando **Java 17** e **Spring Boot**, com foco em boas práticas de desenvolvimento, arquitetura hexagonal, testes automatizados, persistência de dados e integração com serviços externos.

## Objetivo

Desenvolver uma API REST para simular transferências financeiras entre usuários, aplicando conceitos de desenvolvimento backend com Spring Boot, persistência de dados, validações de regras de negócio e testes automatizados.

O projeto também é utilizado como estudo prático de **arquitetura hexagonal, testes unitários, testes de integração e mensageria**.

---

## Tecnologias

* Java 17
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL
* Flyway
* Maven
* Lombok
* JUnit 5
* Mockito
* Kafka
* Postman

---

## Conceitos aplicados

* API REST
* Arquitetura Hexagonal (Ports and Adapters)
* Injeção de Dependência
* DTO Pattern
* Spring Data JPA
* Hibernate
* Migrations com Flyway
* Tratamento Global de Exceções
* Validação de Regras de Negócio
* Consumo de API Externa
* Mensageria com Kafka
* Testes Unitários com JUnit 5 e Mockito
* Testes de Integração

---

## Funcionalidades

* Cadastro de usuários
* Consulta de usuários
* Atualização de usuários
* Exclusão de usuários
* Transferências entre usuários
* Validação de saldo
* Validação do tipo de usuário (Comum e Lojista)
* Integração com serviço externo de autorização
* Registro de transferências
* Tratamento global de exceções
* Utilização de DTOs para Request e Response
* Notificações através de mensageria
* Testes automatizados

---

## Arquitetura

O projeto utiliza **Arquitetura Hexagonal (Ports and Adapters)**, separando o domínio e as regras de negócio das tecnologias externas.

A estrutura principal é organizada em:

```text
src/main/java/com/picpaysimplificado

├── domain
│   ├── user
│   ├── transaction
│   └── exception
│
├── application
│   ├── port
│   │   ├── in
│   │   └── out
│   │
│   └── service
│
└── infrastructure
    ├── in
    │   └── controller
    │
    └── out
        ├── persistence
        │   ├── entity
        │   └── repository
        │
        └── kafka
```

### Domain

Contém as entidades e regras relacionadas ao domínio da aplicação.

Exemplos:

* `User`
* `Transaction`
* `UserType`
* Exceções de domínio

O domínio não depende diretamente de tecnologias de infraestrutura.

### Application

Contém os casos de uso da aplicação, seus serviços e suas portas.

```text
application
├── port
│   ├── in
│   └── out
└── service
```

As portas definem os contratos utilizados pela aplicação.

Exemplos:

* `CreateUserUseCase`
* `CreateTransactionUseCase`
* `UserRepository`
* `TransactionRepository`
* `AuthorizationPort`
* `NotificationPort`

### Infrastructure

Contém as implementações das portas e as integrações com tecnologias externas.

Exemplos:

* Controllers REST
* JPA Entities
* Repositories
* Adapter de autorização
* Adapter Kafka
* Consumers Kafka

---

## Kafka

O projeto utiliza **Kafka** para comunicação assíncrona entre componentes.

O fluxo de notificação é estruturado da seguinte forma:

```text
TransactionService
        │
        ▼
NotificationPort
        │
        ▼
KafkaNotificationAdapter
        │
        ▼
KafkaTemplate
        │
        ▼
      Kafka
        │
        ▼
  notifications
```

A aplicação depende da abstração `NotificationPort`, enquanto a implementação utilizando Kafka permanece na camada de infraestrutura.

---

## Endpoints

### Users

| Método | Endpoint      | Descrição               |
| ------ | ------------- | ----------------------- |
| POST   | `/users`      | Cadastra um usuário     |
| GET    | `/users`      | Lista todos os usuários |
| GET    | `/users/{id}` | Busca usuário por ID    |
| PUT    | `/users/{id}` | Atualiza um usuário     |
| DELETE | `/users/{id}` | Remove um usuário       |

### Transactions

| Método | Endpoint        | Descrição                 |
| ------ | --------------- | ------------------------- |
| POST   | `/transactions` | Realiza uma transferência |

---

## Banco de Dados

O projeto utiliza **MySQL** para persistência dos dados.

A evolução do banco de dados é controlada pelo **Flyway**, utilizando migrations versionadas.

Exemplo:

```text
src/main/resources
└── db
    └── migration
        └── V1__create_users_and_transactions.sql
```

O Flyway é responsável por controlar a evolução da estrutura do banco de dados.

Para os testes de integração, é utilizado um banco separado, evitando alterações no banco utilizado pela aplicação.

---

## Como executar

Clone o repositório:

```bash
git clone https://github.com/isabenhe/picpay-challenge.git
```

Entre na pasta do projeto:

```bash
cd picpay-challenge
```

Execute a aplicação:

```bash
mvn spring-boot:run
```

A aplicação ficará disponível em:

```text
http://localhost:8080
```

### Pré-requisitos

Para executar o projeto localmente, é necessário ter:

* Java 17
* Maven
* MySQL
* Kafka

As configurações da aplicação estão em:

```text
src/main/resources/application.properties
```

As configurações específicas para os testes estão em:

```text
src/test/resources/application-test.properties
```

---

## Testes

Os testes unitários utilizam:

* JUnit 5
* Mockito

Os testes unitários isolam as classes em teste utilizando mocks para suas dependências.

O projeto também possui testes de integração para componentes que dependem de infraestrutura, como persistência de dados.

Para executar os testes:

```bash
mvn test
```

---

## Próximos passos

* [ ] Spring Security
* [ ] JWT
* [ ] Swagger/OpenAPI
* [ ] Docker
* [ ] Testcontainers
* [ ] Testes de integração com Kafka
* [ ] Histórico de transferências
* [ ] Paginação
* [ ] Deploy
* [ ] Angular

---

## Melhorias Futuras

* [ ] MapStruct
* [ ] JavaDoc
* [ ] Cache com Redis
* [ ] Observabilidade
* [ ] CI/CD
* [ ] Kubernetes
* [ ] Idempotência de transações
* [ ] Melhorias no tratamento de falhas
* [ ] Paginação e filtros no histórico de transferências

---

## Autor

**Isabelle F.**

Desenvolvido para fins de estudo e aprimoramento em **Java, Spring Boot, arquitetura de software, testes automatizados e sistemas distribuídos**.

```
```
