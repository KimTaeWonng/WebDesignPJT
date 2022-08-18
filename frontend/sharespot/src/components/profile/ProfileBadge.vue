<template>
  <div>
    <div v-if="!this.hasMainBadge" class="text-center" style="font-size: 4vw">
      대표뱃지를 설정해주세요😋
    </div>
    <v-row no-gutters align="center" v-if="!this.hasMainBadge">
      <v-col cols="1.5"></v-col>
      <v-col cols="3" align="center">
        <v-img class="mt-1" :src="firstMainBadge" @click="changeMainBadge1()"></v-img>
      </v-col>
      <v-col cols="3" align="center">
        <v-img class="mt-1" :src="secondMainBadge" @click="changeMainBadge2()"></v-img>
      </v-col>
      <v-col cols="3" align="center">
        <v-img class="mt-1" :src="thirdMainBadge" @click="changeMainBadge3()"></v-img>
      </v-col>
      <v-col cols="1.5"></v-col>
    </v-row>

    <v-row no-gutters align="center" v-if="this.hasMainBadge">
      <v-col cols="1.5"></v-col>
      <v-col cols="3" align="center">
        <v-img class="mt-1" :src="mainbadge1[0]" @click="changeMainBadge1()"></v-img>
      </v-col>
      <v-col cols="3" align="center">
        <v-img class="mt-1" :src="mainbadge2[0]" @click="changeMainBadge2()"></v-img>
      </v-col>
      <v-col cols="3" align="center">
        <v-img class="mt-1" :src="mainbadge3[0]" @click="changeMainBadge3()"></v-img>
      </v-col>
      <v-col cols="1.5"></v-col>
    </v-row>

    <v-dialog
      v-if="this.userInfo.user_id == this.$route.params.userid"
      v-model="dialog1"
      max-width="600px"
    >
      <v-card>
        <v-card-title class="justify-center" style="font-weight: bolder; font-size: 5vw"
          >첫번째 대표뱃지 설정</v-card-title
        >
        <v-container class="pa-1">
          <v-item-group v-model="selected">
            <v-row>
              <v-col v-for="(b, i) in badgeList" :key="i" cols="4">
                <v-item v-slot="{ active, toggle }">
                  <v-img :src="b" height="70" class="text-right" @click="toggle">
                    <v-btn icon dark>
                      <v-icon color="red">
                        {{ active ? "mdi-heart" : "mdi-heart-outline" }}
                      </v-icon>
                    </v-btn>
                  </v-img>
                </v-item>
              </v-col>
            </v-row>
          </v-item-group>
        </v-container>
        <v-divider class="mt-4"></v-divider>
        <v-card-actions class="mt-2 d-flex justify-end" style="background-color: #ffffff">
          <!-- 초기화 버튼 회색 배경 넣어주기  -->
          <!-- 버튼 색깔 회색 좀 옅은거로 바꿔야 될듯 -->
          <v-btn style="background-color: #289672" text dark @click="confirm1()"> 확인 </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <v-dialog
      v-if="this.userInfo.user_id == this.$route.params.userid"
      v-model="dialog2"
      max-width="600px"
    >
      <v-card>
        <v-card-title class="justify-center" style="font-weight: bolder; font-size: 5vw"
          >두번째 대표뱃지 설정</v-card-title
        >
        <v-container class="pa-1">
          <v-item-group v-model="selected">
            <v-row>
              <v-col v-for="(b, i) in badgeList" :key="i" cols="4">
                <v-item v-slot="{ active, toggle }">
                  <v-img :src="b" height="70" class="text-right" @click="toggle">
                    <v-btn icon dark>
                      <v-icon color="red">
                        {{ active ? "mdi-heart" : "mdi-heart-outline" }}
                      </v-icon>
                    </v-btn>
                  </v-img>
                </v-item>
              </v-col>
            </v-row>
          </v-item-group>
        </v-container>
        <v-divider class="mt-4"></v-divider>
        <v-card-actions class="mt-2 d-flex justify-end" style="background-color: #ffffff">
          <!-- 초기화 버튼 회색 배경 넣어주기  -->
          <!-- 버튼 색깔 회색 좀 옅은거로 바꿔야 될듯 -->
          <v-btn style="background-color: #289672" text dark @click="confirm2()"> 확인 </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <v-dialog
      v-if="this.userInfo.user_id == this.$route.params.userid"
      v-model="dialog3"
      max-width="600px"
    >
      <v-card>
        <v-card-title class="justify-center" style="font-weight: bolder; font-size: 5vw"
          >세번째 대표뱃지 설정</v-card-title
        >
        <v-container class="pa-1">
          <v-item-group v-model="selected">
            <v-row>
              <v-col v-for="(b, i) in badgeList" :key="i" cols="4">
                <v-item v-slot="{ active, toggle }">
                  <v-img :src="b" height="70" class="text-right" @click="toggle">
                    <v-btn icon dark>
                      <v-icon color="red">
                        {{ active ? "mdi-heart" : "mdi-heart-outline" }}
                      </v-icon>
                    </v-btn>
                  </v-img>
                </v-item>
              </v-col>
            </v-row>
          </v-item-group>
        </v-container>
        <v-divider class="mt-4"></v-divider>
        <v-card-actions class="mt-2 d-flex justify-end" style="background-color: #ffffff">
          <!-- 초기화 버튼 회색 배경 넣어주기  -->
          <!-- 버튼 색깔 회색 좀 옅은거로 바꿔야 될듯 -->
          <v-btn style="background-color: #289672" text dark @click="confirm3()"> 확인 </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import { http } from "@/js/http.js";
import { mapState } from "vuex";

const userStore = "userStore";

import {
  getBadgeFood,
  getBadgeCafe,
  getBadgeCulture,
  getBadgeTrip,
  getBadgeLife,
  getBadgeFirstFeed,
  getBadgeFirstComment,
  getBadgeFirstMeeting,
  getBadgeFirstFollowing,
  getBadgeFirstFollower,
  modifyMainBadge,
  mainBadgeImage,
} from "@/js/badge.js";

export default {
  name: "SharespotProfileBadge",

  data() {
    return {
      hasMainBadge: false, // 대표뱃지를 아무것도 설정 안한 상태

      badges: {
        badgeCafe: 0,
        badgeComment: 0,
        badgeCulture: 0,
        badgeFeed: 0,
        badgeFollow: 0,
        badgeFollower: 0,
        badgeFood: 0,
        badgeGroup: 0,
        badgeLife: 0,
        badgeMeet: 0,
        badgeTrip: 0,
        mainCafe: 0,
        mainComment: 0,
        mainCulture: 0,
        mainFeed: 0,
        mainFollow: 0,
        mainFollower: 0,
        mainFood: 0,
        mainGroup: 0,
        mainLife: 0,
        mainMeet: 0,
        mainTrip: 0,
        userId: "",
      },
      badgeList: [],
      selected: "",

      dialog1: false,
      dialog2: false,
      dialog3: false,

      firstMainBadge: require("@/assets/badge/mainbadge_1.png"),
      secondMainBadge: require("@/assets/badge/mainbadge_2.png"),
      thirdMainBadge: require("@/assets/badge/mainbadge_3.png"),

      mainbadge1: [],
      mainbadge2: [],
      mainbadge3: [],
    };
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  async created() {
    //유저의 뱃지 정보 불러오기
    const getBadgeList = await http.get(`/users/badge/${this.$route.params.userid}`);
    this.badges = getBadgeList.data;

    // console.log(this.badges);
    // console.log("객체 인덱스로 접근하기");
    // getBadgeList.data의 12번째 부터 22번째 인덱스까지 메인뱃지
    for (var i = 12; i < 23; i++) {
      // console.log(Object.keys(this.badges)[i]);
      if (this.badges[Object.keys(this.badges)[i]] != 0) {
        this.hasMainBadge = true;
        break;
      }
    }

    // console.log(this.hasMainBadge);

    // 유저의 뱃지 리스트 불러오기
    const getBadge = await http.get(`/users/badge/${this.$route.params.userid}`);
    // console.log(getBadge.data);
    // 맛집 뱃지
    getBadgeFood(getBadge.data.badgeFood, this.badgeList);
    // 카페 뱃지
    getBadgeCafe(getBadge.data.badgeCafe, this.badgeList);
    // 문화 뱃지
    getBadgeCulture(getBadge.data.badgeCulture, this.badgeList);
    // 여행 뱃지
    getBadgeTrip(getBadge.data.badgeTrip, this.badgeList);
    // 생활 뱃지
    getBadgeLife(getBadge.data.badgeLife, this.badgeList);
    // 첫 피드시작 뱃지
    getBadgeFirstFeed(getBadge.data.badgeFeed, this.badgeList);
    // 첫 댓글달기 뱃지
    getBadgeFirstComment(getBadge.data.badgeComment, this.badgeList);
    // 첫 모임시작 뱃지
    getBadgeFirstMeeting(getBadge.data.badgeMeet, this.badgeList);
    // 최초 팔로우 뱃지
    getBadgeFirstFollowing(getBadge.data.badgeFollow, this.badgeList);
    // 최초 팔로워 뱃지
    getBadgeFirstFollower(getBadge.data.badgeFollower, this.badgeList);

    // console.log(this.badgeList);

    for (let a = 12; a < 23; a++) {
      if (this.badges[Object.keys(this.badges)[a]] == 1) {
        mainBadgeImage(Object.keys(this.badges)[a], getBadge.data, this.mainbadge1);
        // console.log(this.mainbadge1);
      } else if (this.badges[Object.keys(this.badges)[a]] == 2) {
        mainBadgeImage(Object.keys(this.badges)[a], getBadge.data, this.mainbadge2);
        console.log("sldkfsj");
        // console.log(this.mainbadge2);
      } else if (this.badges[Object.keys(this.badges)[a]] == 3) {
        mainBadgeImage(Object.keys(this.badges)[a], getBadge.data, this.mainbadge3);
        // console.log(this.mainbadge3);
      }
    }
    // console.log(this.mainbadge1[0]);
    // console.log(this.mainbadge2[0]);
    // console.log(this.mainbadge3[0]);
  },

  mounted() {},

  methods: {
    changeMainBadge1() {
      this.dialog1 = true;
    },
    changeMainBadge2() {
      this.dialog2 = true;
    },
    changeMainBadge3() {
      this.dialog3 = true;
    },
    async confirm1() {
      this.dialog1 = false;
      this.hasMainBadge = true;
      modifyMainBadge(this.firstMainBadge, this.badges, 0);
      // this.firstMainBadge = this.badgeList[this.selected];
      this.mainbadge1 = [];
      this.mainbadge1.push(this.badgeList[this.selected]);
      modifyMainBadge(this.firstMainBadge, this.badges, 1);
      const modifyBadge = await http.put(`/users/badge`, this.badges);
      console.log(modifyBadge);
    },
    async confirm2() {
      this.dialog2 = false;
      this.hasMainBadge = true;
      modifyMainBadge(this.secondMainBadge, this.badges, 0);
      // this.secondMainBadge = this.badgeList[this.selected];
      this.mainbadge2 = [];
      this.mainbadge2.push(this.badgeList[this.selected]);
      modifyMainBadge(this.secondMainBadge, this.badges, 2);
      const modifyBadge = await http.put(`/users/badge`, this.badges);
      console.log(modifyBadge);
    },
    async confirm3() {
      this.dialog3 = false;
      this.hasMainBadge = true;
      modifyMainBadge(this.thirdMainBadge, this.badges, 0);
      // this.thirdMainBadge = this.badgeList[this.selected];
      this.mainbadge3 = [];
      this.mainbadge3.push(this.badgeList[this.selected]);
      modifyMainBadge(this.thirdMainBadge, this.badges, 3);
      const modifyBadge = await http.put(`/users/badge`, this.badges);
      console.log(modifyBadge);
    },
  },
};
</script>

<style scoped></style>
