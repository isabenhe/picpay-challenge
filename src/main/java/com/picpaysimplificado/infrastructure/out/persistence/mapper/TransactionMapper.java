//package com.picpaysimplificado.infrastructure.out.persistence.mapper;
//
//import com.picpaysimplificado.domain.transaction.Transaction;
//import com.picpaysimplificado.infrastructure.out.persistence.entity.TransactionEntity;
//
//public class TransactionMapper {
//    public static TransactionEntity toEntity(Transaction transaction) {
//        TransactionEntity entity = new TransactionEntity();
//        entity.setId(transaction.getId());
//        entity.setAmount(transaction.getAmount());
//        entity.setSender(UserMapper.toEntity(transaction.getSender()));
//        entity.setReceiver(UserMapper.toEntity(transaction.getReceiver()));
//        entity.setTimestamp(transaction.getTimestamp());
//        return entity;
//    }
//
//    public static Transaction toDomain(TransactionEntity entity) {
//        Transaction transaction = new Transaction();
//        transaction.setId(entity.getId());
//        transaction.setAmount(entity.getAmount());
//        transaction.setSender(UserMapper.toDomain(entity.getSender()));
//        transaction.setReceiver(UserMapper.toDomain(entity.getReceiver()));
//        transaction.setTimestamp(entity.getTimestamp());
//        return transaction;
//    }
//}
