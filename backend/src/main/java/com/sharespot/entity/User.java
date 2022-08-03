package com.sharespot.entity;

import java.util.Date;

import javax.persistence.*;

import com.sun.istack.NotNull;

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
@Table(name="user_table")
public class User {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer user_id;
	
	@NotNull
	private String email;
	private String password;
	private String phone;
	private Integer gender;
	private Date birth;
	private String name;
	private String nickname;
	private String introduce;
	private String profile_image;
	@Column(name = "isBadge")
	private boolean BD;
	@Column(name = "alarm")
	private boolean alarm;
	@Column(name = "isGender")
	private boolean GD;
	@Column(name = "isBirth")
	private boolean BR;
	@Column(name = "isPublic")
	private boolean PB;
	private Integer user_grade;
	
	
	
	

}
