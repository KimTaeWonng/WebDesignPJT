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
    private Integer meetingId;

    @Column(columnDefinition = "group_id")
    private Integer groupId;

    @NotBlank(message = "정모 제목을 입력해주세요")
    private String meetingTitle;
    private BigDecimal meetingLat;
    private BigDecimal meetingLng;
    private String meetingUrl;
    private Integer meetingMoney;
    private Date meetingDay;

//    @NotBlank(message = "정모 인원을 입력해주세요")
    private Integer meetingPeople;
}
