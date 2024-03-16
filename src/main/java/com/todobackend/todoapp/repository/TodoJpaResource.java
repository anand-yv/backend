package com.todobackend.todoapp.repository;

import com.todobackend.todoapp.todo.Todo;
import com.todobackend.todoapp.todo.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoJpaResource {

    private final TodoService todoService;

    private final TodoRepository todoRepository;

    public TodoJpaResource(TodoService todoService, TodoRepository todoRepository){
        this.todoService = todoService;
        this.todoRepository = todoRepository;
    }

    @GetMapping(path = "/users/{username}/todos")
    public List<Todo> retrieveTodos(@PathVariable String username){
        return todoRepository.findByUsername(username);
    }

    @GetMapping(path = "/users/{username}/todos/{id}")
    public Todo retrieveTodoById(@PathVariable String username ,@PathVariable Integer id){
        return todoRepository.findById(id).get();
    }

    @DeleteMapping(path = "/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodoById(@PathVariable String username, @PathVariable Integer id) {
        if (todoRepository.existsById(id)) {
            todoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(path ="/users/{username}/todos/{id}")
    public Todo updateTodoById(@PathVariable String username, @PathVariable Integer id, @RequestBody Todo todo){
        todoRepository.save(todo);
        return todo;
    }

    @PostMapping(path ="/users/{username}/todos")
    public Todo createTodo(@PathVariable String username, @RequestBody Todo todo){
        todo.setUsername(username);
        todo.setId(null);
        return todoRepository.save(todo);
    }


}
