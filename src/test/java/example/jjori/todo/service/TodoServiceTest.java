package example.jjori.todo.service;

import example.jjori.todo.dto.TodoDto;
import example.jjori.todo.model.Todo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TodoServiceTest {

    @Autowired
    private TodoService todoService;

    @Test
    public void createTodoTest() {
        TodoDto todoDto = new TodoDto("아침에 감기약 먹기");
        Todo todo = todoService.createTodo(todoDto);

        assertThat(todo.getContent(), is(todoDto.toTodo().getContent()));
    }

    @Test
    public void getTodoTest() {
        TodoDto todoDto = new TodoDto("아침에 감기약 먹기");
        Todo todo = todoService.createTodo(todoDto);

        Todo savedTodo = todoService.getTodo(todo.getId());

        assertThat(savedTodo, is(todo));
    }

    @Test
    public void removeTodoTest() {
        TodoDto todoDto = new TodoDto("아침에 감기약 먹기");
        Todo todo = todoService.createTodo(todoDto);

        Todo savedTodo = todoService.getTodo(todo.getId());
        assertThat(savedTodo, is(todo));

        todoService.removeTodo(savedTodo.getId());

        Todo deletedTodo = todoService.getTodo(savedTodo.getId());
        assertThat(deletedTodo, nullValue());
    }
}
