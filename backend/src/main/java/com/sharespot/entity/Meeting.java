package com.sharespot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;

@SuperBuilder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="meeting")
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT UNSIGNED")
    private Integer meeting_id;

    private Integer group_id;

    @NotBlank(message = "정모 제목을 입력해주세요")
    private String meeting_title;
    private BigDecimal post_lat;
    private BigDecimal post_lng;
    private String meeting_url;
    private Integer meeting_money;
    private Date meeting_day;

    @NotBlank(message = "정모 인원을 입력해주세요")
    private Integer meeting_people;
}
