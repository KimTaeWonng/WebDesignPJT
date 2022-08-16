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

import javax.websocket.server.PathParam;
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

    @PostMapping("/post/{postId}")
    @ApiOperation(value = "게시판 이미지 업로드", notes = "받은 이미지 경로들을 post_image 테이블에 추가한다.")
    public ResponseEntity<Integer> uploadPostFile(@RequestBody String[] list ,@PathVariable int postId) throws IllegalStateException {
        int result = 1;
        for (String l : list) {
            PostImage postImage = PostImage.builder()
                    .postId(postId)
                    .filePath(l)
                    .build();
            postImageRepository.save(postImage);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/post/{postId}")
    @ApiOperation(value = "게시판 이미지들 조회", notes = "postId의 이미지 목록을 반환한다.")
    public ResponseEntity<List<PostImage>> getPostFile(@PathVariable int postId){
        List<PostImage> postImages = postImageRepository.findAllByPostId(postId);
        return new ResponseEntity<>(postImages, HttpStatus.OK);
    }

}
