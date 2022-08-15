<template>
  <v-container>
    <v-tabs color="#289672" v-model="tabs" fixed-tabs>
      <v-tab>게시물</v-tab>
      <v-tab>컬렉션</v-tab>
      <v-tab>저장됨</v-tab>
    </v-tabs>

    <!-- 게시글 탭 -->
    <!-- 변경: 게시글을 가져와서 띄워줘야 함!(사진 클릭하면 게시글 상세조회로 이동) -->
    <v-tabs-items v-model="tabs" class="mr-5 ml-5 mt-3">
      <v-tab-item>
        <v-container fluid>
          <v-row>
            <v-col
              v-for="(p, i) in postList"
              :key="i"
              class="d-flex child-flex"
              style="padding: 4px"
              cols="4"
            >
              <v-img
                :src="p[4]"
                :lazy-src="p[4]"
                aspect-ratio="1"
                class="grey lighten-2"
                @click="movePost(p[0])"
              >
                <template v-slot:placeholder>
                  <v-row class="fill-height ma-0" align="center" justify="center">
                    <v-progress-circular indeterminate color="grey lighten-5"></v-progress-circular>
                  </v-row>
                </template>
              </v-img>
            </v-col>
          </v-row>
        </v-container>
      </v-tab-item>
      <!-- 컬렉션 탭 -->
      <!-- 변경: 뱃지들의 소유여부를 가져와서 소유하고 있는 뱃지들을 띄워줘야 함!-->
      <v-tab-item>
        <!-- 뱃지보유여부가 true인 경우 -->
        <v-container fluid v-if="this.bd == 1">
          <v-row>
            <v-col
              v-for="(b, i) in badgeList"
              :key="i"
              class="d-flex child-flex"
              style="padding: 4px"
              cols="4"
            >
              <v-img :src="b" aspect-ratio="1">
                <template v-slot:placeholder>
                  <v-row class="fill-height ma-0" align="center" justify="center">
                    <v-progress-circular indeterminate color="grey lighten-5"></v-progress-circular>
                  </v-row>
                </template>
              </v-img>
            </v-col>
          </v-row>
        </v-container>
        <!-- 뱃지보유여부가 false인 경우 -->
        <v-container class="mt-5" fluid v-if="this.bd == 0">
          <v-img :src="require('@/assets/badge/badge_no.png')"></v-img>
          <div class="text-center mt-5">아직 보유하고 있는 뱃지가 없어요.😥</div>
          <div class="text-center mt-2">다양한 활동으로 뱃지를 모아보세요!</div>
        </v-container>
      </v-tab-item>
      <!-- 저장됨 탭 -->
      <!-- 변경: 스크랩한 게시글을 가져와서 띄워줘야 함!(사진 클릭하면 게시글 상세조회로 이동)-->
      <v-tab-item>
        <v-container fluid>
          <v-row>
            <v-col
              v-for="(s, i) in scrapList"
              :key="i"
              class="d-flex child-flex"
              style="padding: 4px"
              cols="4"
            >
              <v-img
                :src="s.postImage"
                :lazy-src="s.postImage"
                aspect-ratio="1"
                class="grey lighten-2"
              >
                <template v-slot:placeholder>
                  <v-row class="fill-height ma-0" align="center" justify="center">
                    <v-progress-circular indeterminate color="grey lighten-5"></v-progress-circular>
                  </v-row>
                </template>
              </v-img>
            </v-col>
          </v-row>
        </v-container>
      </v-tab-item>
    </v-tabs-items>
  </v-container>
</template>

<script>
import { http } from "@/js/http.js";
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
} from "@/js/badge.js";

export default {
  name: "SharespotProfileContent",

  data() {
    return {
      tabs: null,
      postList: [],
      badgeList: [],
      scrapList: [],

      bd: false,
    };
  },
  async created() {
    // 유저의 게시글 불러오기
    const getPost = await http.get(`/main/posts/user/${this.$route.params.userid}`);
    this.postList = getPost.data;
    // console.log(this.postList);

    // 유저의 뱃지 컬렉션 불러오기
    // 유저의 뱃지 보유여부 받아오기
    const getBD = await http.get(`/users/info/user/${this.$route.params.userid}`);
    // 유저의 뱃지 보유여부가 true인 경우
    if (getBD.data[0][4] == true) {
      this.bd = true;
      // console.log(this.bd);
      // 유저의 뱃지 리스트 불러오기
      const getBadge = await http.get(`/users/badge/${this.$route.params.userid}`);
      console.log(getBadge.data);
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
    }

    // 유저의 스크랩게시글 불러오기
    const getScrapPost = await http.get(`/LikeScrap/listS/${this.$route.params.userid}`);
    this.scrapList = getScrapPost.data;
    // console.log(this.scrapList);
  },

  mounted() {},

  methods: {
    movePost(postid) {
      // this.$router.push({ name: "profileFollowing" });

      //   // 변경: 해당 프로필 유저의 팔로잉 화면으로 넘어감
      this.$router.push({
        path: `/post/detail/${postid}`,
      });
    },
  },
};
</script>

<style scoped></style>
