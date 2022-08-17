package com.sharespot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="meeting")
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT UNSIGNED")
    private Integer meetingId;

    private Integer groupId;
    private String meetingTitle;
    private BigDecimal meetingLat;
    private BigDecimal meetingLng;
    private String meetingUrl;
    private Integer meetingMoney;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd-HH:mm", timezone="Asia/Seoul")
    private Date meetingDay;

//    @NotBlank(message = "정모 인원을 입력해주세요")
    private Integer meetingPeople;
    private String placeName;
}
