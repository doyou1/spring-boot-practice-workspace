package com.practice.crud.repository;

import com.practice.crud.domain.Board;
import com.practice.crud.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SDJPABoardRepository extends JpaRepository<Board, Long>, BoardRepository{
    @Override
    List<Board> findByWriter(String userid);
    @Override
    Optional<Board> findById(Long id);
    @Override
    @Modifying
    @Query("update Board set title = :#{#board.title}, text = :#{#board.text}, original = :#{#board.original}, saved = :#{#board.saved} WHERE id = :#{#board.id}")
    Integer update(@Param("board")Board board);

    @Override
    @Modifying
    @Query("delete from Board WHERE id = :id")
    Integer delete(@Param("id")Long id);

}
