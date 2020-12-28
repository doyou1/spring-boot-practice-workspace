package com.practice.crud.repository;

import com.practice.crud.domain.Board;
import com.practice.crud.domain.Member;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository {

    Board save(Board board);
    Optional<Board> findById(Long id);
    List<Board> findByWriter(String userid);
    List<Board> findAll();
    Integer update(Board board);
    Integer delete(Long id);
}
