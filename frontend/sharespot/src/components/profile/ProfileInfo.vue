<template>
  <v-container>
    <div class="mr-5 ml-5 mt-3">
      <v-row no-gutters>
        <!-- 이거 해야됨 -->
        <span class="font-weight-black text-center">{{ user.nickname }}</span>
        <!-- 변경: 소유한 뱃지에 따라 등급 부여해야 됨! -->
        <v-img
          class="ml-1"
          style="margin-top: 0.7px"
          :src="this.levelImg"
          max-height="20"
          max-width="20"
        ></v-img>
      </v-row>
      <v-row align="center">
        <v-col cols="4" align="center">
          <v-avatar size="80px">
            <v-img v-if="user.image == null"> <v-icon size="50">mdi-account-circle</v-icon></v-img>
            <v-img v-else :src="user.image"></v-img>
          </v-avatar>
        </v-col>
        <v-col cols="8">
          <v-row>
            <v-col cols="4" align="center">
              <!-- {{ user.postCnt }} -->
              <div style="font-weight: 800">3</div>
              <div style="font-size: 13px">게시글</div>
            </v-col>
            <v-col cols="4" align="center" @click="moveFollower()">
              <div style="font-weight: 800">{{ this.followerCnt }}</div>
              <div style="font-size: 13px">팔로워</div>
            </v-col>
            <v-col cols="4" align="center" @click="moveFollowing()">
              <div style="font-weight: 800">{{ this.followingCnt }}</div>
              <div style="font-size: 13px">팔로잉</div>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="6">
              <!-- 변경: 내 프로필이면 프로필 편집 버튼, 타인 프로필이면 팔로우 버튼 보임 (팔로우가 되어있는 상태면 팔로잉버튼) -->
              <!-- 변경: 팔로우버튼 누르면 팔로우, 팔로잉버튼 누르면 팔로잉 취소하기 -->

              <v-btn
                v-if="this.userInfo.user_id == this.$route.params.userid"
                class="profile-btn"
                style="height: 25px; font-size: 12px"
                color="#289672"
                @click="moveProfileModify()"
                >프로필 편집</v-btn
              >
              <v-btn
                v-else-if="this.isfollow == false"
                class="profile-btn"
                style="height: 25px; font-size: 12px"
                color="#289672"
                @click="clickFollow()"
                >팔로우</v-btn
              >
              <v-btn
                v-else
                class="profile-btn"
                style="height: 25px; font-size: 12px"
                color="#289672"
                @click="clickUnFollow()"
                >언팔로우</v-btn
              >
            </v-col>
            <v-col cols="6">
              <v-btn
                class="profile-btn"
                style="height: 25px; font-size: 12px"
                color="#289672"
                @click="moveProfileTaste()"
                >취향 분석</v-btn
              >
            </v-col>
          </v-row>
        </v-col>
      </v-row>
      <v-row no-gutters>
        <div class="mt-3" style="font-size: 13px">{{ user.introduce }}</div>
      </v-row>
    </div>
  </v-container>
</template>

<script>
import { http } from "@/js/http.js";

import { mapState, mapActions } from "vuex";

const userStore = "userStore";
const userLogStore = "userLogStore";

export default {
  name: "SharespotProfileInfo",
  // props: {
  //   followerCnt: Number,
  //   followingCnt: Number,
  //   follower: Array
  // },
  data() {
    return {
      // 변경: userStore 받아오기!
      user: {
        id: "",
        email: "",
        nickname: "",
        image: "",
        postCnt: "",
        introduce:
          "서울프로맛집러에요~~~ 분식, 일식 위주로 글 올립니다!! 가끔 카페도 추천해드려요 >_<",
      },
      isfollow: false,
      followerCnt: "",
      followingCnt: "",

      levelImg: "",
    };
  },
  async created() {
    // 유저 등급 받아오기
    const getUserLevel = await http.get(`/users/grade/${this.$route.params.userid}`);
    console.log(getUserLevel.data);

    if (getUserLevel.data == 1) {
      this.levelImg = require("@/assets/level/level_red.png");
    } else if (getUserLevel.data == 2) {
      this.levelImg = require("@/assets/level/level_yellow.png");
    } else if (getUserLevel.data == 3) {
      this.levelImg = require("@/assets/level/level_green.png");
    } else if (getUserLevel.data == 4) {
      this.levelImg = require("@/assets/level/level_purple.png");
    } else if (getUserLevel.data == 5) {
      this.levelImg = require("@/assets/level/level_rainbow.png");
    }

    // 현재 프로필페이지의 유저아이디와 로그인 유저아이디가 같고,
    // 받아온 유저 등급과 유저인포의 등급이 다를 때, 등급 승급
    if (
      this.$route.params.userid == this.userInfo.user_id &&
      this.userInfo.userGrade != getUserLevel.data
    ) {
      this.userInfo.userGrade = getUserLevel.data;
      const modifyUserLevel = await http.put(`/users/${this.userInfo.user_id}`, this.userInfo);
      if (modifyUserLevel.data.message == "success") {
        console.log("유저 등급 승급 성공");
      }
    }

    // 방문중이었던 유저의 id를 내 로그에 저장하기

    const profileUser = await http.get(`users/info/user/${this.$route.params.userid}`);
    console.log("프로필유저", profileUser);
    console.log("profileUser.data[0]", profileUser.data);
    const followerList = await http.get(`users/${this.$route.params.userid}/follower`);

    // 만약 내 페이지면 팔로잉리스트를 겟할 필요가 없음
    if (this.userInfo.user_id == this.$route.params.userid) {
      this.followingCnt = this.followingUserList.length;
    } else {
      const followingList = await http.get(`users/${this.$route.params.userid}/following`);
      this.followingCnt = followingList.data.length;
    }

    console.log(this.userInfo.user_id);
    if (followerList.data.length !== 0) {
      for (var i = 0; i < followerList.data.length; i++) {
        if (followerList.data[i].user_id == this.userInfo.user_id) {
          this.isfollow = true;
          break;
        }
      }
    }

    this.user.nickname = profileUser.data[0][1];
    console.log("profileUser.data[0]", profileUser.data);
    this.user.introduce = profileUser.data[0][2];
    this.user.image = profileUser.data[0][3];
    this.followerCnt = followerList.data.length;
  },
  updated() {
    console.log("updated");
    this.$nextTick(function () {
      // 모든 화면이 렌더링된 후 실행합니다.
    });
  },
  mounted() {},

  computed: {
    ...mapState(userStore, ["userInfo"]),
    ...mapState(userLogStore, ["followingUserList"]),
  },

  methods: {
    ...mapActions(userLogStore, ["follow", "unfollow"]),

    moveProfileModify() {
      this.$router.push({ name: "profileModify" });
    },
    moveProfileTaste() {
      const profileid = this.$route.params.userid;
      console.log(profileid);
      this.$router.push({
        path: `/profile/taste/${profileid}`,
      });
      // this.$router.push({ name: "profileTaste" });
    },
    moveFollower() {
      // this.$router.push({ name: "profileFollower" });

      // 변경: 해당 프로필 유저의 팔로워 화면으로 넘어감
      this.$router.push({
        path: `/profile/follower/${this.$route.params.userid}`,
      });
    },
    moveFollowing() {
      // this.$router.push({ name: "profileFollowing" });

      //   // 변경: 해당 프로필 유저의 팔로잉 화면으로 넘어감
      this.$router.push({
        path: `/profile/following/${this.$route.params.userid}`,
      });
    },

    async clickFollow() {
      const res = {
        followerId: this.userInfo.user_id,
        userId: this.$route.params.userid,
      };
      const user = {
        // 팔로우할 유저
        user_id: this.$route.params.userid,
        nickname: this.user.nickname,
        profileImage: this.user.image,
        introduce: this.user.introduce,
      };

      const followInfo = {
        user,
        res,
      };

      this.follow(followInfo);

      this.isfollow = true;
      this.followerCnt++;
      // const temp = await http.get(`users/info/otherUser/${this.$route.params.userid}`);
      // console.log(temp)
      // console.log('팔로잉유저리스트', this.followingUserList[0])
      // console.log(this.followingUserList)
    },

    async clickUnFollow() {
      // console.log(this.followingUserList)
      // for (var i = 0; i < this.followingUserList.length; i++) {
      //     console.log('언팔로우 인덱싱중')
      //     console.log(this.followingUserList.length)
      //     if (this.followingUserList[i].user_id == this.$route.params.userid) {
      //         console.log(this.followingUserList[i])

      //         console.log(this.unfollowindex)
      //         break
      //   }
      // }
      //   console.log('팔로워 위치', i)

      const followInfo = {
        followerId: this.userInfo.user_id,
        userId: this.$route.params.userid,
      };

      console.log("팔로팔로");
      console.log(followInfo);
      this.unfollow(followInfo);

      console.log("팔로잉유저리스트", this.followingUserList);
      this.isfollow = false;
      this.followerCnt--;
    },

    //     async followUnfollow() {
    //       const temt = await http.get(`/users/${this.$route.params.userid}/follower`);
    //       // console.log('temt.data')
    //       // console.log(temt.data)
    //       const followerList = []

    //       for (const fid of temt.data) {
    //         // console.log('fid')
    //         // console.log(fid.user_id)

    //         followerList.push(fid.user_id)
    //       }

    //       // console.log(this.userInfo.user_id)
    //       // console.log('위는 나고')
    //       // console.log('아래는 페이지 주인')
    //       // console.log(this.$route.params.userid)

    //       const loginid = this.userInfo.user_id
    //       const rst = followerList.indexOf(loginid)
    //       console.log(followerList)
    //       console.log(loginid)
    //       console.log(rst)
    //       if (rst === -1) {

    //         // console.log('팔로우 안함')
    //         const res = {
    //   "followerId": loginid,
    //   "userId": this.$route.params.userid
    // }
    //         http.post(`/users/${this.$route.params.userid}/follow`, res);
    //         console.log('팔로우')
    //       }
    //       else {
    //         http.delete(`/users/${this.$route.params.userid}/${this.userInfo.user_id}`);
    //         console.log('팔로우취소')
    //       }

    //     }
  },
};
</script>

<style scoped>
.profile-btn {
  color: white;
}
.row + .row {
  margin-top: 3px !important;
}
</style>
