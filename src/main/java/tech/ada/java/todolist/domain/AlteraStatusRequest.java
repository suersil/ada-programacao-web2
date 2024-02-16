package tech.ada.java.todolist.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AlteraStatusRequest(Boolean status, String titulo, String descricao)
{
}
