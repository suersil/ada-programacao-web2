package tech.ada.java.todolist.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//Criando um Controlle de Exceção - camada interna do servidor

@ControllerAdvice //fala para o Spring que essa classe é o controllador de exceções
public class RestControllerAdvice {

    @ExceptionHandler(RuntimeException.class) // e esse trata as exceções
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex){

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @AllArgsConstructor //para criar o construtor
    @Getter
    @Setter
    public class ErrorResponse{
        private HttpStatus status; //codigo de erro
        private String message;

    }
}
