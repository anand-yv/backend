package com.todobackend.todoapp.todo;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoResource {

    private final TodoService todoService;

    public TodoResource(TodoService todoService){
        this.todoService = todoService;
    }

    @GetMapping(path = "/users/{username}/todos")
    public List<Todo> retrieveTodos(@PathVariable String username){
        return todoService.findByUsername(username);
    }

    @GetMapping(path = "/users/{username}/todos/{id}")
    public Todo retrieveTodoById(@PathVariable String username ,@PathVariable Integer id){
        return todoService.findById(id);
    }

    @DeleteMapping(path = "/users/{username}/todos/{id}")
    public void deleteTodoById(@PathVariable String username, @PathVariable Integer id){
        todoService.deleteById(id);
    }

    @PutMapping(path ="/users/{username}/todos/{id}")
    public Todo updateTodoById(@PathVariable String username, @PathVariable Integer id, @RequestBody Todo todo){
        todoService.updateTodo(todo);
        return todo;
    }

    @PostMapping(path ="/users/{username}/todos")
    public Todo createTodo(@PathVariable String username, @RequestBody Todo todo){
        return todoService.addTodo(username, todo.getDescription(), todo.getTargetDate(), todo.isDone());
    }


}
