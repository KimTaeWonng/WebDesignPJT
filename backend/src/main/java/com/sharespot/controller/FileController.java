package com.sharespot.controller;

import com.sharespot.dto.FileDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/file")
public class FileController {

    @GetMapping(
            produces = MediaType.IMAGE_JPEG_VALUE // content-type
    )
    public ResponseEntity<byte[]> test(@RequestParam("imagePath") String imagePath) throws IOException {

        InputStream imageStream = new FileInputStream(
                imagePath);
        //파일 이름으로 읽어올 파일의 경로 설정

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int read;
        byte[] imageByteArray = new byte[imageStream.available()];
        while ((read = imageStream.read(imageByteArray, 0, imageByteArray.length)) != -1) {
            buffer.write(imageByteArray, 0, read);
        }
        buffer.flush();
        byte[] targetArray = buffer.toByteArray();
        imageStream.close();

        return new ResponseEntity<byte[]>(targetArray, HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ArrayList<String> uploadFile(@RequestParam MultipartFile[] files) throws IllegalStateException, IOException {
        String UPLOAD_PATH = "/home/ubuntu/src/image/" + LocalDate.now(); // 업로드 할 위치 // 현재 날짜 값 폴더
//        String UPLOAD_PATH = "C:\\Users\\Administrator\\Desktop\\downtest\\" + LocalDate.now(); // 업로드 할 위치 // 현재 날짜 값 폴더

        log.debug("파일 업로드 위치: {}", UPLOAD_PATH);
        ArrayList<String> result = new ArrayList<>();

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
                result.add(UPLOAD_PATH + dto.getUuid() + "_" + dto.getFileName());

                File newFileName = new File(UPLOAD_PATH, dto.getUuid() + "_" + dto.getFileName());

                log.debug("dto파일이름: {}", dto.getFileName());

                f.transferTo(newFileName);

            }
        }

        return result;
    }

}
