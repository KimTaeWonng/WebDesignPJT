package com.sharespot.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@SuperBuilder
@Getter @Setter
public class FileDto {
    private String uuid;
    private String fileName; //실제 파일이름
    private String contentType;

}
