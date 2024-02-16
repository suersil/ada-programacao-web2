package tech.ada.java.todolist.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AtualizarTodosRequest(Boolean status, String titulo, String descricao, LocalDateTime dataHora, LocalDate prazoFinal) {
}
