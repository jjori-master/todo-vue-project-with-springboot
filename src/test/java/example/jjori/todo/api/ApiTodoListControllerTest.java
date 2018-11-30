package example.jjori.todo.api;


import example.jjori.todo.dto.TodoDto;
import example.jjori.todo.model.Todo;
import example.jjori.todo.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class ApiTodoListControllerTest {

    @Autowired
    protected TestRestTemplate template;

    @Autowired
    private TodoService todoService;

    @Test
    public void createTodoTest() {
        String path = "/api/todos";

        TodoDto todoDto = new TodoDto("약 먹기1234");

        ResponseEntity<Todo> response
                = template.postForEntity(path, todoDto, Todo.class);

        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));

        Todo savedTodo = todoService.getTodo(response.getBody().getId());
        assertThat(savedTodo, is(response.getBody()));
    }

    @Test
    public void removeTodoTest() {
        Todo savedTodo = todoService.createTodo(new TodoDto("약 먹기 1231"));

        String path = "/api/todos/" + savedTodo.getId();

        template.delete(path);

        Todo deletedTodo = todoService.getTodo(savedTodo.getId());
        assertThat(deletedTodo, nullValue());
    }

    @Test
    public void getTodoListTest() {
        AtomicInteger incrementNumber = new AtomicInteger(1);

        Arrays.stream(new int[11]).forEach(n -> {
            TodoDto todoDto = new TodoDto("할일" + incrementNumber.getAndIncrement());
            todoService.createTodo(todoDto);
        });

        String path = "/api/todos";

        ResponseEntity<Todo[]> response
                = template.getForEntity(path, Todo[].class);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        Todo[] todos = response.getBody();

        assertThat(todos, notNullValue());
        assertThat(todos.length, is(11));
    }
}
