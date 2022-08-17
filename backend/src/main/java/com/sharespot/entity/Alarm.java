package com.sharespot.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
@Table(name="alarm")
public class Alarm {
	
	@Id
	private Integer user_id;	
	private Integer is_heart;
	private Integer is_comment;
	private Integer is_follow;
	private Integer is_group;
	private Integer is_feed;
	private Integer is_chat;

}
