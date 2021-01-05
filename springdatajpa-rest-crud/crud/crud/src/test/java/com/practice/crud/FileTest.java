package com.practice.crud;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

public class FileTest {

    private static final String uploadPath = "src/main/resources/static/uploadFolder/";

    /**
     * MultipartFile save method
     */
    @Test
    void fileSave() {
        try{
            MultipartFile file = new MockMultipartFile("test.png", new FileInputStream(new File("src/main/resources/하하3.png")));
            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/uploadFolder/",file.getName())));
            outputStream.write(file.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Upload Test
     * 업로드 디렉터리 파일명 확인(코드 최적화 필요)
     * original, ext(확장자), saved 등 String format 이용해 추출(코드 최적화 필요)
     */
    @Test
    void uploadTest() {
        String original = "";
        String saved = "";
        String ext = "";
        boolean check = false;
        try{
            // 업로드된 file
            MultipartFile file = new MockMultipartFile("test.png", new FileInputStream(new File("src/main/resources/하하3.png")));
            System.out.println(file.getName()); // 원래는 file.getOriginalFilename()
            original = file.getName();
            ext = original.substring(original.lastIndexOf(".")+1);

            File dirs = new File("src/main/resources/uploadFolder/");
            File[] files = dirs.listFiles();
            while(!check) {
                check = true;
                saved += System.currentTimeMillis();    // 유닉스 시간으로 중복 없앰, 원래 파일명은 중복 가능성 때문에 파일명으로 쓰면 안됨
                for(File f : files)
                {
                    if(f.getName().equals(saved+"."+ext)) {
                        check = false;
                    }
                }
            }
            saved += "."+ext;

            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/uploadFolder/",saved)));
            outputStream.write(file.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch(IOException e) {
            e.printStackTrace();
        }

        System.out.println("original : " + original);
        System.out.println("saved : " + saved);
    }

    @Test
    void pathTest() {
        Path path = Paths.get(uploadPath);

        System.out.println(path.toFile());
    }

}
