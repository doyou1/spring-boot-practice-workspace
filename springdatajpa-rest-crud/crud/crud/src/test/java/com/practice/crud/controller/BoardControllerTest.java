package com.practice.crud.controller;

import com.practice.crud.domain.Board;
import com.practice.crud.domain.FileDomain;
import com.practice.crud.repository.BoardRepository;
import com.practice.crud.service.BoardService;
import com.practice.crud.service.FileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;

@SpringBootTest
@Transactional
public class BoardControllerTest {

    @Autowired
    private BoardService boardService;
    @Autowired
    private BoardRepository boardRepository;

    private final String uploadPath = "src/main/resources/uploadFolder/";

    @Test
    void writeTest(){
        Board board = new Board();
        board.setWriter("test");
        board.setTitle("title");
        board.setText("text");
        board.setDate(LocalDate.now());
        try{
            MultipartFile file = new MockMultipartFile("test.png", new FileInputStream(new File("src/main/resources/하하3.png")));
            FileDomain fileDomain = FileService.save(file);
            board.setOriginal(fileDomain.getOriginal());
            board.setSaved(fileDomain.getSaved());
        }catch (IOException e){
            e.printStackTrace();
        }

        board = boardService.save(board);

        Board dbBoard = boardService.findById(board.getId());
        System.out.println(dbBoard.toString());

        File f = new File(uploadPath+dbBoard.getSaved());
        System.out.println(f.isFile());

    }
}
