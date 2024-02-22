package tech.ada.java.todolist.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//Uma camada que vai se comunicar com o nosso banco de dados, ou outra forma de dados

//JPA Repository - outra interface do Spring - Gerenciado pelo Spring, que herda de outras interfaces.
// Só precisamos passar no parametro qual a tabela e o ID = nome da classe e o tipo do dado da primary key

@Repository //só para deixar claro que é um repositório
public interface TodoItemRepository extends JpaRepository <TodoItem, Long> {
   List<TodoItem> findByTitulo(String titulo);

   @Query("SELECT t FROM TodoItem t WHERE t.titulo = ?1") //t - é o apelido que demos(forma generica), Diferente do "nativeQuery" (*)
   List<TodoItem> findByTituloQuery(String titulo);

}
