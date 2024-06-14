package com.koeyh.spring02.controller;

import java.util.List;


import com.koeyh.spring02.domain.Todo;
import com.koeyh.spring02.service.TodoService;

import jakarta.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TodoController {
    
    @Resource
    TodoService todoService;

    // localhost:8091/todos 요청하면 실행되는 메서드
    @GetMapping("/todos")
    public List<Todo> getMethodName() throws Exception {
        List<Todo> allTodos = todoService.getTodos();
        return allTodos;
    }
    
}
