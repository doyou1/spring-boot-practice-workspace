package com.practice.crud.service;

import com.practice.crud.domain.Board;
import com.practice.crud.domain.Member;
import com.practice.crud.repository.BoardRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Board save(Board board) {
        return boardRepository.save(board);
    }
    public Board findById(Long id) {
        Optional<Board> board = boardRepository.findById(id);
        if(board.isPresent()) {
            return board.get();
        }else{
            throw new IllegalStateException("userid에 맞는 게시물이 없습니다.");
        }
    }

    public List<Board> findByUserid(String userid) {
        List<Board> list = boardRepository.findByWriter(userid);
        if(!list.isEmpty()) {
            return list;
        }else{
            throw new IllegalStateException("userid에 맞는 게시물이 없습니다.");
        }
    }

    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    public Integer update(Board board) {
        Integer check = boardRepository.update(board);
        return check;
    }

    public Integer delete(Long id) {
        Integer check = boardRepository.delete(id);
        return check;
    }
}
