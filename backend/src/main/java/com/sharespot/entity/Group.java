package com.sharespot.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@SuperBuilder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="group_table")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT UNSIGNED")
    private Integer group_id;

    private Integer group_manager;

    @NotBlank(message = "그룹 이름을 입력해주세요")
    private String group_name;

    @NotBlank(message = "그룹 소개글을 입력해주세요")
    private String group_content;

    @NotBlank(message = "그룹 정원을 입력해주세요")
    private Integer group_limit;
    private Integer group_gender;
    private Integer group_age_min;
    private Integer group_age_max;
    private String group_image;


}
