package tech.ada.java.todolist.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Setter
@Getter
@EqualsAndHashCode
public class TodoItem {

    //private = para protecao com Encapsulamento
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //para se auto-encrementar como valor único
    private Long id;
    private String titulo;
    private String descricao;
    private boolean concluida;
    private LocalDateTime dataHora;
    private LocalDateTime dataHoraAtualizacao;
    private LocalDate prazoFinal;

    public TodoItem(){
        this.concluida = false;
        this.dataHora = LocalDateTime.now();
        this.dataHoraAtualizacao = LocalDateTime.now();
    }

    public TodoItem (String titulo, String descricao, LocalDate prazoFinal){
        this.titulo = titulo;
        this.descricao = descricao;
        this.prazoFinal = prazoFinal;
        this.concluida = false;
        this.dataHora = LocalDateTime.now();
        this.dataHoraAtualizacao = LocalDateTime.now();
    }

}
