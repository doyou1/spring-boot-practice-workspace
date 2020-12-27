package com.practice.crud.controller;

import com.practice.crud.domain.Board;
import com.practice.crud.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Controller
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("board")
    public String boardList(Model model) {
        System.out.println("boardList!");
        List<Board> list = boardService.findAll();
        model.addAttribute("list", list);
        return "board/boardList";
    }

    @GetMapping("board/write")
    public String writeForm() {

        return "board/writeForm";
    }

    @PostMapping("board/write")
    public String writeBoard(Board board, HttpSession session) {
        board.setDate(LocalDate.now());
        board.setWriter((String) session.getAttribute("userid"));
        System.out.println(board.toString());
        board = boardService.save(board);
        System.out.println(board.toString());
        return "redirect:/board";
    }
}
