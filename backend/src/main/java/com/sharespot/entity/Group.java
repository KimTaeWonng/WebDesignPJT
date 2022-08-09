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

    private String group_name;
    private String group_content;
    private Integer group_limit;
    private Integer group_gender;
    private Integer group_age_min;
    private Integer group_age_max;
    private String group_image;
}
