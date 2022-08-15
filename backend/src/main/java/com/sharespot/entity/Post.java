package com.sharespot.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="post_table")
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "INT UNSIGNED")
	private Integer postId;

	private Integer userId;
	private String nickname;

	@NotBlank(message = "게시글 내용을 입력해주세요")
	private String content;
	private BigDecimal postLat;
	private BigDecimal postLng;
	private String image;
	private String postGpsName;
	private String classBig;
	private String classSmall;
	private String classWho;
	private String classWhere;
	private Date uploadTime;
	
	private Integer likeCnt;
	private Integer commentCnt;
	private String userImage;

}
