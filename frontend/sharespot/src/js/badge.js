// 맛집 뱃지
function getBadgeFood(data, badgeList) {
    if (data == 1) {
        badgeList.push(require("@/assets/badge/badge_red_restaurant.png"));
      } else if (data == 2) {
        badgeList.push(require("@/assets/badge/badge_yellow_restaurant.png"));
      } else if (data == 3) {
        badgeList.push(require("@/assets/badge/badge_green_restaurant.png"));
      } else if (data == 4) {
        badgeList.push(require("@/assets/badge/badge_purple_restaurant.png"));
      } else if (data == 5) {
        badgeList.push(require("@/assets/badge/badge_rainbow_restaurant.png"));
      }
}

// 카페 뱃지
function getBadgeCafe(data, badgeList) {
    if (data == 1) {
        badgeList.push(require("@/assets/badge/badge_red_cafe.png"));
      } else if (data == 2) {
        badgeList.push(require("@/assets/badge/badge_yellow_cafe.png"));
      } else if (data == 3) {
        badgeList.push(require("@/assets/badge/badge_green_cafe.png"));
      } else if (data == 4) {
        badgeList.push(require("@/assets/badge/badge_purple_cafe.png"));
      } else if (data == 5) {
        badgeList.push(require("@/assets/badge/badge_rainbow_cafe.png"));
      }
}

// 문화 뱃지
function getBadgeCulture(data, badgeList) {
    if (data == 1) {
        badgeList.push(require("@/assets/badge/badge_red_culture.png"));
      } else if (data == 2) {
        badgeList.push(require("@/assets/badge/badge_yellow_culture.png"));
      } else if (data == 3) {
        badgeList.push(require("@/assets/badge/badge_green_culture.png"));
      } else if (data == 4) {
        badgeList.push(require("@/assets/badge/badge_purple_culture.png"));
      } else if (data == 5) {
        badgeList.push(require("@/assets/badge/badge_rainbow_culture.png"));
      }
}

// 여행 뱃지
function getBadgeTrip(data, badgeList) {
    if (data == 1) {
        badgeList.push(require("@/assets/badge/badge_red_trip.png"));
      } else if (data == 2) {
        badgeList.push(require("@/assets/badge/badge_yellow_trip.png"));
      } else if (data == 3) {
        badgeList.push(require("@/assets/badge/badge_green_trip.png"));
      } else if (data == 4) {
        badgeList.push(require("@/assets/badge/badge_purple_trip.png"));
      } else if (data == 5) {
        badgeList.push(require("@/assets/badge/badge_rainbow_trip.png"));
      }
}

// 생활 뱃지
function getBadgeLife(data, badgeList) {
    if (data == 1) {
        badgeList.push(require("@/assets/badge/badge_red_life.png"));
      } else if (data == 2) {
        badgeList.push(require("@/assets/badge/badge_yellow_life.png"));
      } else if (data == 3) {
        badgeList.push(require("@/assets/badge/badge_green_life.png"));
      } else if (data == 4) {
        badgeList.push(require("@/assets/badge/badge_purple_life.png"));
      } else if (data == 5) {
        badgeList.push(require("@/assets/badge/badge_rainbow_life.png"));
      }
}

// 첫 피드시작 뱃지
function getBadgeFirstFeed(data, badgeList) {
  if (data == 1) {
    badgeList.push(require("@/assets/badge/badge_first_feed.png"));
  }
}

// 첫 댓글달기 뱃지
function getBadgeFirstComment(data, badgeList) {
  if (data == 1) {
    badgeList.push(require("@/assets/badge/badge_first_comment.png"));
  }
}

// 첫 모임시작 뱃지
function getBadgeFirstMeeting(data, badgeList) {
  if (data == 1) {
    badgeList.push(require("@/assets/badge/badge_first_meeting.png"));
  }
}

// 최초 팔로우 뱃지
function getBadgeFirstFollowing(data, badgeList) {
  if (data == 1) {
    badgeList.push(require("@/assets/badge/badge_first_following.png"));
  }
}

// 최초 팔로워 뱃지
function getBadgeFirstFollower(data, badgeList) {
  if (data == 1) {
    badgeList.push(require("@/assets/badge/badge_first_follower.png"));
  }
}

export { getBadgeFood, getBadgeCafe, getBadgeCulture, getBadgeTrip, getBadgeLife, getBadgeFirstFeed, getBadgeFirstComment, getBadgeFirstMeeting, getBadgeFirstFollowing, getBadgeFirstFollower }

