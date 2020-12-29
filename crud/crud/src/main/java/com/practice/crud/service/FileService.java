package com.practice.crud.service;

import com.practice.crud.domain.FileDomain;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.http.HttpHeaders;

public class FileService {

    private static final String uploadPath = "src/main/resources/static/uploadFolder/";

    public static FileDomain save(MultipartFile file) {

//        String original = file.getName(); 테스트용
        String original = file.getOriginalFilename();
        String ext = original.substring(original.lastIndexOf(".")+1);
        String saved = "";
        boolean check = false;
        System.out.println("ext : " + ext);
        File dir = new File(uploadPath);

        while(!check) {
            check = true;
            saved += System.currentTimeMillis(); // 중복제거를 위해 유닉스 시간
            for(File f : dir.listFiles()) {
                if(f.getName().equals(saved+"."+ext)) {
                    check = false;
                }
            }
        }
        saved += "." + ext;

        try{
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(uploadPath, saved)));
            bos.write(file.getBytes());
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileDomain fileDomain = new FileDomain(original, saved);
        return fileDomain;
    }

    public static InputStream download(String fileName) throws IOException {
        File file = new File(uploadPath + fileName);
        return FileUtils.openInputStream(file);
    }
}
