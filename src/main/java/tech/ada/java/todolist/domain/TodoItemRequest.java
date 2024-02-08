package tech.ada.java.todolist.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;

//Informacoes que eu preciso receber de fora
record TodoItemRequest (String titulo,String descricao, LocalDate prazoFinal ){

}
