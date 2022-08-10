<template>
  <v-container>
    <div class="mr-5 ml-5 mt-3">
    <v-row no-gutters>
      <!-- 이거 해야됨 -->
      <span class="font-weight-black text-center">{{ userInfo.nickname }}</span>
      <!-- 변경: 소유한 뱃지에 따라 등급 부여해야 됨! -->
      <v-img
        class="ml-1"
        style="margin-top: 0.7px"
        src="@/assets/level/level_red.png"
        max-height="20"
        max-width="20"
      ></v-img>
    </v-row>
    <v-row align="center">
      <v-col cols="4" align="center">
        <v-avatar size="80px">
          <v-img :src="user.image"></v-img>
        </v-avatar>
      </v-col>
      <v-col cols="8">
        <v-row>
          <v-col cols="4" align="center">
            <div style="font-weight: 800">{{ user.postCnt }}</div>
            <div style="font-size: 13px">게시글</div>
          </v-col>
          <v-col cols="4" align="center" @click="moveFollower()">
            <div style="font-weight: 800">{{ followerCnt }}</div>
            <div style="font-size: 13px">팔로워</div>
          </v-col>
          <v-col cols="4" align="center" @click="moveFollowing()">
            <div style="font-weight: 800">{{ followingCnt }}</div>
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
              v-else
              class="profile-btn"
              style="height: 25px; font-size: 12px"
              color="#289672"
              @click="followUnfollow()"
              >팔로우</v-btn
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
import { mapState } from "vuex";

const userStore = "userStore";

export default {
  name: "SharespotProfileInfo",
  props: {
    followerCnt: Number,
    followingCnt: Number,
    follower: Array
  },
  data() {
    return {
      // 변경: userStore 받아오기!
      user: {
        id: "",
        email: "",
        nickname: "",
        image: "https://cdn.vuetifyjs.com/images/lists/1.jpg",
        postCnt: "",
        followerCnt: "",
        followingCnt: "",
        introduce:
          "서울프로맛집러에요~~~ 분식, 일식 위주로 글 올립니다!! 가끔 카페도 추천해드려요 >_<",
      },
    };
  },
  async created() {
    this.user.email = this.userInfo.email;
    this.user.id = this.userInfo.user_id;
  },

  mounted() {},
  
  computed: {
    
    ...mapState(userStore, ["userInfo"]),

},
  
  methods: {
    moveProfileModify() {
      this.$router.push({ name: "profileModify" });
    },
    moveProfileTaste() {
      const profileid = this.$route.params.userid
      console.log(profileid)
      this.$router.push({
        path: `/profile/taste/${profileid}`
      });
      // this.$router.push({ name: "profileTaste" });
    },
    moveFollower() {
      // this.$router.push({ name: "profileFollower" });


      // 변경: 해당 프로필 유저의 팔로워 화면으로 넘어감
      this.$router.push({

        path: `/profile/follower/${this.$route.params.userid}`
      });
    },
    moveFollowing() {
      // this.$router.push({ name: "profileFollowing" });

      //   // 변경: 해당 프로필 유저의 팔로잉 화면으로 넘어감
      this.$router.push({
        path: `/profile/following/${this.$route.params.userid}`
      });
        },
    async followUnfollow() {
      const temt = await http.get(`/users/${this.$route.params.userid}/follower`);
      // console.log('temt.data')
      // console.log(temt.data)
      const followerList = []

      for (const fid of temt.data) {
        // console.log('fid')
        // console.log(fid.user_id)

        followerList.push(fid.user_id)
      }

      // console.log(this.userInfo.user_id)
      // console.log('위는 나고')
      // console.log('아래는 페이지 주인')
      // console.log(this.$route.params.userid)

      const loginid = this.userInfo.user_id
      const rst = followerList.indexOf(loginid)
      console.log(followerList)
      console.log(loginid)
      console.log(rst)
      if (rst === -1) {
      
        // console.log('팔로우 안함')
        const res = {
  "followerId": loginid,
  "userId": this.$route.params.userid
}
        http.post(`/users/${this.$route.params.userid}/follow`, res);
        console.log('팔로우')
      }
      else {
        http.delete(`/users/${this.$route.params.userid}/${this.userInfo.user_id}`);
        console.log('팔로우취소')
      }


    }
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
