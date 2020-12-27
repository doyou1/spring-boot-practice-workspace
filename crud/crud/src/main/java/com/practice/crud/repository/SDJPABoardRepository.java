package com.practice.crud.repository;

import com.practice.crud.domain.Board;
import com.practice.crud.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SDJPABoardRepository extends JpaRepository<Board, Long>, BoardRepository{
    @Override
    List<Board> findByWriter(String userid);

}
