package example.jjori.todo.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Todo {
    @Id
    @GeneratedValue
    private long id;

    private String content;

    public Todo() {}

    public Todo(String content) {
        this.content = content;
    }
}
