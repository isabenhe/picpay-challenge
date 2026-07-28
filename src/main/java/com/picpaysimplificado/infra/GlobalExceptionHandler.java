//package com.picpaysimplificado.infra;
//
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import com.picpaysimplificado.dtos.ExceptionDTO;
//
//import jakarta.persistence.EntityNotFoundException;
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public ResponseEntity threatDuplicateEntity(DataIntegrityViolationException exception) {
//        ExceptionDTO exceptionDTO = new ExceptionDTO("Usuário já cadastrado", "400");
//        return ResponseEntity.badRequest().body(exceptionDTO);
//    }
//
//    @ExceptionHandler(EntityNotFoundException.class)
//    public ResponseEntity threat404(EntityNotFoundException exception) {
//        return ResponseEntity.badRequest().build();
//
//    }
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity threatGeneralExcpeiton(Exception exception) {
//        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), "500");
//        return ResponseEntity.internalServerError().body(exceptionDTO);
//    }
//
//}