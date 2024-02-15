package tech.ada.java.todolist.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDate;


//Informacoes que eu preciso receber de fora
@Getter
public class TodoItemRequest{
    public String titulo;
    public String descricao;
    public LocalDate prazoFinal;

    //criei uma funcao - virar entidade = transformar uma request em Entity
    public TodoItem toEntity(){
        return new TodoItem (titulo, descricao, prazoFinal);
    }
}
