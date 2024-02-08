package tech.ada.java.todolist.domain;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//Todo controller precisa de um repositorio (repository)

//@Controller //porque é gerenciado pelo Spring
@RestController("/todo")        // "RestController" traz o controller e ainda traz o responseBody //"todo" - é o caminho - apelido

public class TodoController {

    //Apenas uma variavel - Um atributo que nao foi instanciado:
    private final TodoItemRepository todoItemRepository;  //final - constante, porque ele só é instanciado no construtor, e só acontece 1x.

    //Construtor
    public TodoController(TodoItemRepository todoItemRepository) {
        this.todoItemRepository = todoItemRepository;  //this - da classe e nao do parametro (mesmo nome)
    }

    @PostMapping("/todo-item")  //() - caminho de acesso
    public TodoItem cadastrarItem(@RequestBody TodoItemRequest request) {

        TodoItem todoItemConvertido = new TodoItem();   //convertendo minha Request em uma TodoItem (para poder ser usado dentro do save):
        todoItemConvertido.setTitulo(request.titulo());
        todoItemConvertido.setDescricao(request.descricao());
        todoItemConvertido.setPrazoFinal(request.prazoFinal());

        TodoItem novoTodoItem = todoItemRepository.save(todoItemConvertido);
        return novoTodoItem;
    }
    @GetMapping ("/todo-item")
    public List<TodoItem> buscarTodos() {
        return todoItemRepository.findAll();
    }
}



