package com.sharespot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@SuperBuilder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="badge")
public class Badge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT UNSIGNED")
    private Integer userId;

    private Integer badgeFood;
    private Integer badgeCafe;
    private Integer badgeCulture;
    private Integer badgeTrip;
    private Integer badgeLife;

    private Integer badgeGroup;
    private Integer badgeFeed;
    private Integer badgeComment;
    private Integer badgeMeet;
    private Integer badgeFollow;

    private Integer badgeFollower;
    private Integer mainFood;
    private Integer mainCafe;
    private Integer mainCulture;
    private Integer mainTrip;

    private Integer mainLife;
    private Integer mainGroup;
    private Integer mainFeed;
    private Integer mainComment;
    private Integer mainMeet;
    private Integer mainFollow;
    private Integer mainFollower;

}
