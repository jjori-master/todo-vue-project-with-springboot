package example.jjori.todo.service;

import example.jjori.todo.dto.TodoDto;
import example.jjori.todo.model.Todo;
import example.jjori.todo.model.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class TodoService {

    @Resource(name="todoRepository")
    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo createTodo(TodoDto todoDto) {
        Todo todo = todoDto.toTodo();
        log.info("save todo is {}", todo);

        return todoRepository.save(todo);
    }

    public void removeTodo(long id) {
        todoRepository.deleteById(id);
    }

    public Todo getTodo(long id) {
        return todoRepository.findById(id).orElse(null);
    }
}
