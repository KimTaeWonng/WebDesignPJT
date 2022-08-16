package com.sharespot.controller;

import com.sharespot.entity.PostImage;
import com.sharespot.repo.PostImageRepository;
import com.sharespot.service.FileService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;
    private final PostImageRepository postImageRepository;

    @GetMapping(
            produces = MediaType.IMAGE_JPEG_VALUE // content-type
    )
    @ApiOperation(value = "이미지 조회", notes = "imagePath에 해당하는 서버의 이미지 파일을 조회한다.")
    public ResponseEntity<byte[]> getFile(@RequestParam("imagePath") String imagePath){

        byte[] targetArray = null;
        try{
            targetArray = fileService.getImage(imagePath);
        }catch (Exception e){
            log.debug("이미지 조회 중 에러 : {}", e.getMessage());
        }

        return new ResponseEntity<>(targetArray, HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "이미지 업로드", notes = "선택된 이미지 파일들을 서버에 업로드한다.")
    public ResponseEntity<List<String>> uploadFile(@RequestParam MultipartFile[] files) throws IllegalStateException, IOException {
        List<String> result = null;
        result = fileService.uploadImage(files);
//        try {
//        }catch (Exception e){
//            log.debug("이미지 업로드 중 에러 : {}", e.getMessage());
//        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

//    @PostMapping("/post")
//    @ApiOperation(value = "게시판 이미지 업로드", notes = "선택된 이미지 파일들을 서버에 업로드하고 post_image 테이블에 추가한다.")
//    public ResponseEntity<List<String>> uploadPostFile(@RequestParam MultipartFile[] files, @RequestParam int postId) throws IllegalStateException {
//        List<String> result = null;
//        try {
//            result = fileService.uploadImage(files);
//
//            for (String r : result){
//                PostImage postImage = PostImage.builder()
//                        .postId(postId)
//                        .filePath(r)
//                        .build();
//                postImageRepository.save(postImage);
//            }
//
//        }catch (Exception e){
//            log.debug("이미지 업로드 중 에러 : {}", e.getMessage());
//        }
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }

}
