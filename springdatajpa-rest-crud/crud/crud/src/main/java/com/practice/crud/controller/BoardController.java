package com.practice.crud.controller;

import com.practice.crud.domain.Board;
import com.practice.crud.domain.FileDomain;
import com.practice.crud.service.BoardService;
import com.practice.crud.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpHeaders;
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
    public String writeBoard(Board board, MultipartFile file, HttpSession session) {
        board.setDate(LocalDate.now());
        board.setWriter((String) session.getAttribute("userid"));

        if(!file.isEmpty()) {
            FileDomain fileDomain = FileService.save(file);
            board.setOriginal(fileDomain.getOriginal());
            board.setSaved(fileDomain.getSaved());
        }

        boardService.save(board);

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
        board.setText(board.getText().replace("\r\n","<br>"));
        return "board/viewBoard";
    }

    @DeleteMapping("board/{id}")
    @ResponseBody
    public Integer deleteBoard(@PathVariable("id") Long id) {
        return boardService.delete(id);
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
    public String updateBoard(Board board,MultipartFile file,HttpSession session){
        board.setWriter((String)session.getAttribute("userid"));

        Board prevBoard = boardService.findById(board.getId());
        board.setOriginal(prevBoard.getOriginal());
        board.setSaved(prevBoard.getSaved());
        if(!file.isEmpty()) {
            System.out.println(file.getOriginalFilename());
            FileDomain fileDomain = FileService.save(file);
            board.setOriginal(fileDomain.getOriginal());
            board.setSaved(fileDomain.getSaved());

            if(prevBoard.getSaved() != null) {
                FileService.remove(prevBoard.getSaved());
            }
        }
        Integer check = boardService.update(board);

        if(check != 0)
            System.out.println("UPDATE SUCCESS");
        return "redirect:/board/"+board.getId();
    }

    @GetMapping("downloadFile/{fileName}")
    @ResponseBody
    public Resource downloadFile(@PathVariable String fileName) {
        InputStream is = null;
        try{
             is = FileService.download(fileName);
        } catch(IOException e) {
            e.printStackTrace();
        }

        return new InputStreamResource(is);
    }
}
