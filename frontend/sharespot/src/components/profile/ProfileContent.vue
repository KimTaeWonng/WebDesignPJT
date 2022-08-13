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
        <v-container fluid>
          <v-row>
            <v-col v-for="n in 12" :key="n" class="d-flex child-flex" style="padding: 4px" cols="4">
              <v-img :src="require('@/assets/badge/badge_red_restaurant.png')" aspect-ratio="1">
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
export default {
  name: "SharespotProfileContent",

  data() {
    return {
      tabs: null,
      postList: [],
      badgeList: [],
      scrapList: [],
    };
  },
  async created() {
    // 유저의 게시글 불러오기
    const getPost = await http.get(`/main/posts/user/${this.$route.params.userid}`);

    this.postList = getPost.data;
    console.log("포스트리스트");
    // console.log(this.postList);

    // 유저의 뱃지 컬렉션 불러오기


    // 유저의 스크랩게시글 불러오기
    const getScrapPost = await http.get(`/LikeScrap/listS/${this.$route.params.userid}`);
    this.scrapList = getScrapPost.data;

    console.log("스크랩게시글");
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
