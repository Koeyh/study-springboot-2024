package com.koeyh.spring02.controller;

import java.util.List;


import com.koeyh.spring02.domain.Todo;
import com.koeyh.spring02.service.TodoService;

import jakarta.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@Controller
public class TodoController {
    
    @Resource
    TodoService todoService;

    // localhost:8091/todos 요청하면 실행되는 메서드
    @GetMapping("/todos")
    public String getTodos(Model model) throws Exception {
        List<Todo> allTodos = todoService.getTodos();
        model.addAttribute("todoList", allTodos); // 뷰(html파일)에 model로 todoList를 전달
        return "todolist";
    }

    // 과거 : http://localhost:8091/todo.do?tno=1
    // Restful URL : http://localhost:8091/todo/1
    @GetMapping("/todo/{tno}")
    public Todo getTodo(@PathVariable("tno") int tno) throws Exception {
        return todoService.getTodo(tno);
    }
    
}
