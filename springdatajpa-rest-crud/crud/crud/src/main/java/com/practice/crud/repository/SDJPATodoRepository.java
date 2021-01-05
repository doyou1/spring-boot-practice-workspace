package com.practice.crud.repository;

import com.practice.crud.domain.Member;
import com.practice.crud.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SDJPATodoRepository extends JpaRepository<Todo, Long>, TodoRepository{
    @Override
    List<Todo> findByWriter(String userid);

    @Override
    @Modifying
    @Query("update Todo set text = :#{#todo.text} where id = :#{#todo.id}")
    Integer todoUpdate(@Param("todo")Todo todo);

    @Override
    @Modifying
    @Query("delete from Todo where id = :#{#todo.id} and writer = :#{#todo.writer}")
    Integer todoDelete(@Param("todo")Todo todo);

}
