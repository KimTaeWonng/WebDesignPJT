package com.sharespot.controller;

import com.sharespot.dto.FileDto;
import com.sharespot.service.FileService;
import io.swagger.annotations.ApiOperation;
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

    private final FileService fileService;


    @GetMapping(
            produces = MediaType.IMAGE_JPEG_VALUE // content-type
    )
    @ApiOperation(value = "이미지 조회", notes = "imagePath에 해당하는 서버의 이미지 파일을 조회한다.")
    public ResponseEntity<byte[]> getFile(@RequestParam("imagePath") String imagePath) throws IOException {

        byte[] targetArray = null;
        try{
            targetArray = fileService.getImage(imagePath);
        }catch (Exception e){
            log.debug("이미지 조회 중 에러 : {}", e.getMessage());
        }

        return new ResponseEntity<byte[]>(targetArray, HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "이미지 업로드", notes = "선택된 이미지 파일들을 서버에 업로드한다.")
    public ResponseEntity<ArrayList<String>> uploadFile(@RequestParam MultipartFile[] files) throws IllegalStateException, IOException {
        ArrayList result = null;
        try {
            result = fileService.uploadImage(files);
        }catch (Exception e){
            log.debug("이미지 업로드 중 에러 : {}", e.getMessage());
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
