package com.sharespot.entity;

import java.util.Date;

import javax.persistence.*;


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
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String email;
	private String password;
	private String phone;
	private Integer gender;
	private Date birth;
	private String name;
	private String nickname;
	private String introduce;
	private String profile_image;
	private boolean isBadge;
	private boolean alarm;
	private boolean isGender;
	private boolean isBirth;
	private boolean isPublic;
	private Integer user_grade;
	
	public boolean isGender() {
		return isGender;
	}
	public void setGender(boolean isGender) {
		this.isGender = isGender;
	}
	public boolean isBirth() {
		return isBirth;
	}
	public void setBirth(boolean isBirth) {
		this.isBirth = isBirth;
	}
	
	
	
	

}
