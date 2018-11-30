package example.jjori.todo.api;

import example.jjori.todo.dto.TodoDto;
import example.jjori.todo.model.Todo;
import example.jjori.todo.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SuppressWarnings("SpellCheckingInspection")
@Slf4j
@RestController
@RequestMapping("/api/todos")
public class ApiTodoListController {

    private final TodoService todoService;

    @Autowired
    public ApiTodoListController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("")
    public ResponseEntity<Todo[]> todoList() {
        return new ResponseEntity<>(todoService.getTodos(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Todo> addTodo(@RequestBody TodoDto todoDto) {

        Todo savedTodo = todoService.createTodo(todoDto);

        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void removeTodo(@PathVariable(name = "id") long todoId) {
        todoService.removeTodo(todoId);
    }
}
