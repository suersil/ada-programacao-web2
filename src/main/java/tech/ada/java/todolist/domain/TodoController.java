package tech.ada.java.todolist.domain;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//Todo controller precisa de um repositorio (repository)

//@Controller //porque é gerenciado pelo Spring
@RestController("/todo")        // "RestController" traz o controller e ainda traz o responseBody //"todo" - é o caminho - apelido

public class TodoController {

    //Apenas uma variavel - Um atributo que nao foi instanciado:
    private final TodoItemRepository todoItemRepository;  //final - constante, porque ele só é instanciado no construtor, e só acontece 1x.

    //Construtor
    public TodoController (TodoItemRepository todoItemRepository){
        this.todoItemRepository = todoItemRepository;  //this - da classe e nao do parametro (mesmo nome)
    }

    @GetMapping("/todo-item")  //() - caminho de acesso
    //Metodo to sett o Titulo
    public void inserirTodoItem() {
        TodoItem todoItem = new TodoItem();
        todoItem.setTitulo("Fazer Cafe");
        todoItem.setConcluida(true);

        todoItemRepository.save(todoItem);
    }
}
