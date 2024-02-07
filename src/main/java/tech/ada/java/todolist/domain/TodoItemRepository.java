package tech.ada.java.todolist.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Uma camada que vai se comunicar com o nosso banco de dados, ou outra forma de dados

//JPA Repository - outra interface do Spring - Gerenciado pelo Spring, que herda de outras interfaces.
// Só precisamos passar no parametro qual a tabela e o ID = nome da classe e o tipo do dado da primary key

@Repository //só para deixar claro que é um repositório
public interface TodoItemRepository extends JpaRepository <TodoItem, Long> {
}
