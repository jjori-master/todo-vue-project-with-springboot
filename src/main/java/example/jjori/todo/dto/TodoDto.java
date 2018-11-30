package example.jjori.todo.dto;

import example.jjori.todo.model.Todo;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class TodoDto {

    private long id;

    @Size(min = 3, max = 100)
    private String content;

    public TodoDto(String content) {
        this(0, content);
    }

    private TodoDto(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public Todo toTodo() {
        return new Todo((this.content));
    }
}
