package com.practice.crud.controller;

import com.practice.crud.domain.Board;
import com.practice.crud.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 게시판 이동 Request
     * @param model
     * @return
     */
    @GetMapping("boardList")
    public String boardList(Model model) {
        System.out.println("boardList!");
        List<Board> list = boardService.findAll();
        model.addAttribute("list", list);
        return "board/boardList";
    }

    /**
     * 게시물 작성 폼 이동
     * @return
     */
    @GetMapping("board/write")
    public String writeForm() {

        return "board/writeForm";
    }

    /**
     * 게시판 등록 Request
     * @param board
     * @param session
     * @return 게시판 이동
     */
    @PostMapping("board/write")
    public String writeBoard(Board board, HttpSession session) {
        board.setDate(LocalDate.now());
        board.setWriter((String) session.getAttribute("userid"));
        System.out.println(board.toString());
        board = boardService.save(board);
        System.out.println(board.toString());
        return "redirect:/boardList";
    }

    /**
     * 특정 게시판 이동
     * @param id
     * @param model
     * @return
     */
    @GetMapping("board/{id}")
    public String viewBoard(@PathVariable("id") Long id, Model model){
        Board board = boardService.findById(id);
        model.addAttribute("board", board);
        return "board/viewBoard";
    }

    @DeleteMapping("board/{id}")
    @ResponseBody
    public Long deleteBoard(@PathVariable("id") Long id) {
        System.out.println("id = " + id);
        return id;
    }
    /**
     * 특정 게시물 수정 폼 이동 Request
     * @param id
     * @param model
     * @return
     */
    @GetMapping("board/update/{id}")
    public String updateBoardForm(@PathVariable("id") Long id, Model model) {
        Board board = boardService.findById(id);
        model.addAttribute("board", board);

        return "board/updateForm";
    }

    /**
     * 특정 게시물 수정 Request
     * @param board
     * @param session
     * @return 수정한 특정 게시물 이동
     */
    @PostMapping(value = "board/update/{id}")
    public String updateBoard(Board board, HttpSession session){
        board.setWriter((String)session.getAttribute("userid"));
        boardService.update(board);
        return "redirect:/board/"+board.getId();
    }


}
