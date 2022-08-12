package com.sharespot.controller;

import com.sharespot.dto.FileDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/upload")
public class FileController {

    @PostMapping("/file")
    public String uploadFile(@RequestParam MultipartFile[] files) throws IllegalStateException, IOException {
        String UPLOAD_PATH = "/home/ubuntu/src/image/" + LocalDate.now(); // 업로드 할 위치 // 현재 날짜 값 폴더
//        String UPLOAD_PATH = "C:\\Users\\Administrator\\Desktop\\downtest\\" + LocalDate.now(); // 업로드 할 위치 // 현재 날짜 값 폴더

        log.debug("파일 업로드 위치: {}", UPLOAD_PATH);

        File destdir = new File(UPLOAD_PATH);

        if(!destdir.exists()){
            destdir.mkdirs(); //디렉토리가 존재하지 않는다면 생성
        }

        List<FileDto> list = new ArrayList<>();
        for (MultipartFile f : files){
            if(!f.isEmpty()){
                FileDto dto = new FileDto().builder()
                        .uuid(UUID.randomUUID().toString())
                        .fileName(f.getOriginalFilename())
                        .contentType(f.getContentType())
                        .build();

                list.add(dto);

                File newFileName = new File(UPLOAD_PATH, dto.getUuid() + "_" + dto.getFileName());

                log.debug("dto파일이름: {}", dto.getFileName());

                f.transferTo(newFileName);

            }
        }

        return UPLOAD_PATH;
    }

}
