package tech.ada.java.todolist.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public record AtualizarTodosRequest(
        String titulo,
        String descricao,
        Boolean concluida,
        LocalDate prazoFinal
) {

    //Validando valores passados no Patch:

    //Construtor
    public AtualizarTodosRequest
            (String titulo, String descricao, Boolean concluida, LocalDate prazoFinal) {

        this.titulo = Objects.requireNonNull(titulo, "Titulo é obrigatorio"); //Opcao com Mensagem
        this.descricao = Objects.requireNonNull(descricao, "Descrição obrigatoria");
        this.concluida = Objects.requireNonNull(concluida, "Concluida é obrigatoria");
        this.prazoFinal = Objects.requireNonNull(prazoFinal, "Necessario prazo final");
//        this.dataHora = Objects.requireNonNull(dataHora);

    }

    /*    public <T>T myNotNull(T objeto) {
        if(objeto == null)
            throw new NullPointerException();
        return objeto;
    }*/


}


/*O erro "Non-canonical record constructor must delegate to another constructor"
     Ao usar records no Java, se você definir um construtor diferente do padrão gerado pelo compilador, ele precisa delegar para o construtor padrão gerado pelo compilador.
     Ou seja, o construtor tem que conter todos os atributos passados, incluindo a mesma ordem.
*/