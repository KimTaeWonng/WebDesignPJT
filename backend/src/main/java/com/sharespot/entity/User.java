package com.sharespot.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="user_table")
public class User {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer user_id;
	
	
	@NotBlank(message = "이메일을 입력해주세요")
	@Email(message = "이메일 주소가 올바르지 않습니다.")
	private String email;
	
	@NotBlank(message = "비밀번호를 입력해주세요")
	@Size(min = 8, max = 20, message = "비밀번호는 8~20자로 입력해주세요")
	private String password;
	
	@Pattern(regexp = "(01[016789])(\\d{3,4})(\\d{4})", message ="올바른 휴대폰 번호를 입력해주세요.")
	@NotBlank(message = "전화번호를 입력해주세요")
	private String phone;
	private Integer gender;
	
	@NotNull(message = "생년월일을 입력해주세요")
	private Date birth;
	
	@Size(min = 2, max = 6, message = "올바른 이름을 입력해주세요")
	@NotBlank(message = "이름을 입력해주세요")
	private String name;
	
	@Size(min = 2, max = 10, message = "별명은 10자 이내로 입력해주세요")
	@NotBlank(message = "공백은 제거해주세요")
	private String nickname;
	
	private String introduce;
	private String profile_image;
	@Column(name = "BD")
	private Integer BD;
	@Column(name = "AL")
	private Integer AL;
	@Column(name = "GD")
	private Integer GD;
	@Column(name = "BR")
	private Integer BR;
	@Column(name = "PB")
	private Integer PB;
	private Integer user_grade;
	
	@OneToOne
	@JoinColumn(name ="user_id")
	private Alarm alarm;
	
}
