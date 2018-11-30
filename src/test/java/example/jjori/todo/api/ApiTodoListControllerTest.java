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

import static org.hamcrest.CoreMatchers.is;
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

        ResponseEntity<String> response
                = template.postForEntity(path, todoDto, String.class);

        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));

        Todo savedTodo = todoService.getTodo(1L);
        assertThat(savedTodo.getContent(), is(todoDto.getContent()));
    }
}
