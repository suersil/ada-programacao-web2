package tech.ada.java.todolist.domain;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.PutExchange;

import java.util.List;
import java.util.Optional;

//Todo controller precisa de um repositorio (repository)

//@RequiredArgsConstructor // se usar esse nao precisa do construtor

@RestController("/todo")  // "RestController" traz o @controller e ainda traz o @responseBody //"todo" - é o caminho - apelido
public class TodoController {

    //Apenas uma variavel - Um atributo que nao foi instanciado: (nossa depedencia do repositorio)
    private TodoItemRepository todoItemRepository;  //(removemos o final) final - constante, porque ele só é instanciado no construtor, e só acontece 1x.
    private final ModelMapper modelMapper; // nova dependencia modelmapper

    @Autowired // Injetamos as dependencia vira construtor com padrao inversao de dependencia
    //Construtor
    public TodoController(TodoItemRepository todoItemRepository, ModelMapper modelMapper) {
        this.todoItemRepository = todoItemRepository;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/todo-item")  //() - caminho de acesso
    public ResponseEntity<TodoItem> cadastrarItem(@RequestBody TodoItemRequest request) {
        //Vamos converter a request "TodoItemRequest" que chegou no body para uma entidade "TodoItem" atraves a funcao que criamos toEntity
        //TodoItem todoItemConvertido = request.toEntity();
//        System.out.println("");
//        System.out.println("######## Debug Suelen");
//        System.out.println("titulo:" + request.getTitulo());
//        System.out.println("descricao:" + request.getDescricao());
//        System.out.println("prazoFinal:" + request.getPrazoFinal());

        //Vamos converter a request "TodoItemRequest" que chegou no body para uma entidade "TodoItem" atraves do componente model mapper
        TodoItem todoItemConvertido = modelMapper.map(request, TodoItem.class);

        //Vamos salvar a entidade criada no repositorio
        TodoItem novoTodoItem = todoItemRepository.save(todoItemConvertido);

        //Retornamos o status 201 com o body "corpo da resposta" o novoTodoItem que foi criado pelo repositorio
        return ResponseEntity.status(HttpStatus.CREATED).body(novoTodoItem);
    }

    @GetMapping("/todo-item")
    public List<TodoItem> buscarTodos() {
        List<TodoItem> listaComTodos = todoItemRepository.findAll();
        return listaComTodos;
    }

    //Fazendo uma requisição pelo Query usando @RequestParam
    @GetMapping(value= "/todo-item", params = {"titulo"})
    public List<TodoItem> buscarPorFiltro(@RequestParam String titulo){
        String tituloSemEspacos = titulo.replaceAll("\\s", ""); //para ignorar espaços entre as palavras

        return todoItemRepository.findByTitulo(titulo);
    }

    //Criamos uma nova rota para atualizar partes especificas do nosso recurso /todo-item
    // identificando pelo path variable {id}
    @PatchMapping("/todo-item/{id}")
    public ResponseEntity<TodoItem> alteraStatus(
            @PathVariable Long id,
            @RequestBody AlteraStatusRequest request) throws Exception {
        // Buscamos pelo metodo findById que retorna um Optional<TodoItem> pois o mesmo pode nao existir no banco
        Optional<TodoItem> optionalTodoItem = todoItemRepository.findById(id);

        // Verificamos se existe valor dentro do Optional
        if (optionalTodoItem.isPresent()) {
            // Se existir vamos fazer o get() para tirar o valor de dentro do optional
            TodoItem todoItemModificado = optionalTodoItem.get();
            // verificamos se um das tres variaveis que esperamos foi passada para ser atualizada
            if (request.status() != null) todoItemModificado.setConcluida(request.status());
            if (request.titulo() != null) todoItemModificado.setTitulo(request.titulo());
            if (request.descricao() != null) todoItemModificado.setDescricao(request.descricao());

            //Depois de atualizar o que precisamos, vamos salvar
            TodoItem todoItemSalvo = todoItemRepository.save(todoItemModificado);
            return ResponseEntity.ok(todoItemSalvo);
        }

        // Caso nao encontramos na valor no Optional retornamos o codigo 404 - nao encontrado
        return ResponseEntity.notFound().build();
    }

    //Nesse caso não queremos criar, apenas atualizar.
    @PutMapping("/todo-item/{id}")
    public ResponseEntity<TodoItem> atualizarTodos
            (@PathVariable Long id, @RequestBody AtualizarTodosRequest request) throws Exception {
        System.out.println("PUT /todo-item/{id}");
        //Todos usa o @RequestBody menos o GET
        // Buscamos pelo metodo findById que retorna um Optional<TodoItem> pois o mesmo pode nao existir no banco
        Optional<TodoItem> optionalTodoItem = todoItemRepository.findById(id);

        //Primeiro checar se NAO esta vazio - se existe os valores dentro do banco podemos atualizar/editar em baixo:
        if (optionalTodoItem.isEmpty()) {
            return ResponseEntity.notFound().build(); //Eu não quis implementar o "Else"
        }

        // Se existir vamos fazer o get()-ID para tirar o valor de dentro do optional
        TodoItem todoItemExistente = optionalTodoItem.get();

        todoItemExistente.setConcluida(request.concluida());
        todoItemExistente.setTitulo(request.titulo());
        todoItemExistente.setDescricao(request.descricao());
        todoItemExistente.setPrazoFinal(request.prazoFinal());
       // todoItemExistente.setDataHora(request.dataHora());   // ou LocalDateTimeNow() = Now

        TodoItem todoItemSalvo = todoItemRepository.save(todoItemExistente);

        return ResponseEntity.ok(todoItemSalvo);


//     else {
//        return ResponseEntity.notFound().build();
//    }

    }

}



