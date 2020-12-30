package com.practice.crud.repository;

import com.practice.crud.domain.Todo;

import java.util.List;

public interface TodoRepository {

    Todo save(Todo todo);
    List<Todo> findByWriter(String userid);
    Integer todoUpdate(Todo todo);
    Integer todoDelete(Todo todo);
}
