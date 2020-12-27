package com.practice.crud.repository;

import com.practice.crud.domain.Board;
import com.practice.crud.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository {

    Board save(Board board);
    List<Board> findByWriter(String userid);
    List<Board> findAll();
}
