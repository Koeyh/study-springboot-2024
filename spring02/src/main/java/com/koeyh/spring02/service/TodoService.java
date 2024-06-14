package com.koeyh.spring02.service;

import java.util.List;


import com.koeyh.spring02.domain.Todo;

// 소규모 프로젝트에서는 interface가 아닌 class로 @Service를 사용해서 바로 작성해도 무방함
public interface TodoService {

    public List<Todo> getTodos() throws Exception;

    public Todo getTodo(int tno) throws Exception;
}