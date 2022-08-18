<template>
  <div>
    <!-- 최신피드 -->
    <latest-post-list v-if="this.type == 'latest'"></latest-post-list>

    <!-- 큐레이션 상세검색 피드 -->
    <v-list v-if="this.type == 'curation'">
      <post-card
        class="post-card"
        v-for="(post, i) in curationPosts"
        :key="i"
        :detailPost="post"
      ></post-card>
    </v-list>
    <infinite-loading
      v-if="this.type == 'curation'"
      @infinite="infiniteHandler"
      spinner="wavedots"
    >
      <div
        slot="no-more"
        style="color: rgb(102, 102, 102); font-size: 14px; padding: 10px 0px"
      >
        게시글을 다 봤어요 :)
      </div>
    </infinite-loading>

    <!-- 상세 태그 검색 플로팅 버튼 -->
    <v-btn
      style="margin-bottom: 16%"
      color="#289672"
      right
      bottom
      fixed
      elevation="2"
      fab
      @click="detailSearch()"
    >
      <v-icon size="30px" color="white">search</v-icon>
    </v-btn>

    <v-dialog v-model="dialog" max-width="600px">
      <v-card>
        <v-card-title
          class="justify-center"
          style="font-weight: bolder; font-size: 5vw"
          >태그 상세 검색</v-card-title
        >

        <!-- 거리조절 -->

        <!-- <v-subheader>거리조절 : {{ slider }} km 이내</v-subheader>
          <v-row class="distance-bar">
            <v-col cols="12">
              <v-slider
                v-model="slider"
                :max="max"
                :min="min"
                :thumb-size="24"
                thumb-label="always"
              ></v-slider>
            </v-col>
          </v-row> -->

        <!-- 분류 제목 + 버튼 -->
        <!-- 대분류 -->
        <v-item-group mandatory align="center" v-model="selected_1">
          <v-subheader>대분류</v-subheader>
          <v-item v-for="n in 5" :key="n" v-slot="{ active, toggle }">
            <v-btn
              height="64px"
              elevation="0"
              style="margin: 5px"
              :color="active ? '#289672' : ''"
              active-class="white--text"
              :input-value="active"
              @click="toggle(), selectBig(n)"
            >
              {{ categorys.tag[n - 1].big_name }}
            </v-btn>
          </v-item>
        </v-item-group>

        <!-- 소분류 -->
        <v-item-group multiple align="center" v-model="selected_2">
          <v-subheader>소분류</v-subheader>
          <v-item
            v-for="(item, i) in this.small"
            :key="i"
            v-slot="{ active, toggle }"
          >
            <v-btn
              width="64px"
              height="64px"
              elevation="0"
              style="margin: 5px"
              :color="active ? '#289672' : ''"
              active-class="white--text"
              :input-value="active"
              @click="toggle"
            >
              {{ item.small_name }}
            </v-btn>
          </v-item>
        </v-item-group>

        <!-- 누구랑 -->
        <v-item-group multiple align="center" v-model="selected_3">
          <v-subheader>누구랑</v-subheader>
          <v-item v-for="(who, i) in whos" :key="i" v-slot="{ active, toggle }">
            <v-btn
              height="64px"
              elevation="0"
              style="margin: 5px"
              :color="active ? '#289672' : ''"
              active-class="white--text"
              :input-value="active"
              @click="toggle"
            >
              {{ who }}
            </v-btn>
          </v-item>
        </v-item-group>

        <!-- 어디서 -->
        <v-item-group multiple align="center" v-model="selected_4">
          <v-subheader>어디서</v-subheader>
          <v-item
            v-for="(where, i) in wheres"
            :key="i"
            v-slot="{ active, toggle }"
          >
            <v-btn
              width="64px"
              height="64px"
              elevation="0"
              style="margin: 5px"
              :color="active ? '#289672' : ''"
              active-class="white--text"
              :input-value="active"
              @click="toggle"
            >
              {{ where }}
            </v-btn>
          </v-item>
        </v-item-group>

        <!-- 분류 제목 + 버튼 여기까지  -->
        <v-divider class="mt-4"></v-divider>
        <v-card-actions
          class="mt-2 d-flex justify-end"
          style="background-color: #ffffff"
        >
          <!-- 초기화 버튼 회색 배경 넣어주기  -->
          <!-- 버튼 색깔 회색 좀 옅은거로 바꿔야 될듯 -->
          <v-btn style="background-color: #f3f3f3" text @click="clear()">
            초기화
          </v-btn>
          <v-btn style="background-color: #289672" text dark @click="addTag()">
            확인
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import { http } from "@/js/http.js";
import { mapState } from "vuex";
import InfiniteLoading from "vue-infinite-loading";

import tag from "@/assets/json/tag.json";

import PostCard from "../post/PostCard.vue";
import LatestPostList from "./LatestPostList.vue";
// import Vue from "vue";
// import VueGeolocationApi from "vue-geolocation-api";
// import haversine from 'haversine'

const userStore = "userStore";
// Vue.use(VueGeolocationApi);

export default {
  components: { PostCard, InfiniteLoading, LatestPostList },
  name: "S07P12A505CurationPostList",

  data() {
    return {
      posts: [],
      loadNum: 0,
      curationLoadNum: 0,
      curationPosts: [],
      // latitude: '',
      // longitude: '',

      dialog: false,
      slider: 10,
      min: 0,
      max: 200,

      categorys: tag, // 대분류,소분류 태그 json
      small: [
        { small_name: "한식" },
        { small_name: "중식" },
        { small_name: "일식" },
        { small_name: "양식" },
        { small_name: "분식" },
        { small_name: "아시안" },
        { small_name: "기타" },
      ],
      whos: ["가족", "친구", "연인", "혼자"],
      wheres: [
        "서울",
        "경기",
        "인천",
        "강원",
        "제주",
        "대전",
        "충북",
        "충남/세종",
        "부산",
        "울산",
        "경남",
        "대구",
        "경북",
        "광주",
        "전남",
        "전주/전북",
      ],

      // 선택한 태그 값들 (1, 2, ...)
      selected_1: "",
      selected_2: [],
      selected_3: [],
      selected_4: [],

      // 선택한 태그 항목들 (맛집, 가족 ...)
      tag_big: "",
      tag_small: [],
      tag_who: [],
      tag_where: [],

      // 타입을 latest와 curation으로 구분
      type: "latest",
    };
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },

  mounted() {},

  // async created() {
  //   this.getMylocation();
  // },

  methods: {
    // getDistance(lat1, lng1, lat2, lng2) {
    //   function deg2rad(deg) {
    //     return deg * (Math.PI/180)
    //   }

    //   var R = 6371;
    //   var dLat = deg2rad(lat2-lat1)
    //   var dLng = deg2rad(lng2-lng1)
    //  var a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.sin(dLng/2) * Math.sin(dLng/2);
    // var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
    // var d = R * c;
    // this.distance2 = d
    // },

    async infiniteHandler($state) {
      // this.curationPosts = [];

      // 선택된 값을 보내줘서 해당 태그에 맞는 게시물들을 보여줌
      await http
        .get(`search/posts/category/${this.tag_big}/{small}`, {
          params: {
            small: this.tag_small.join(","),
            who: this.tag_who.join(","),
            where: this.tag_where.join(","),
            page: this.curationLoadNum,
            size: 5,
          },
        })
        .then((res) => {
          if (res.data.totalPages == this.curationLoadNum) {
            $state.complete();
          } else {
            setTimeout(() => {
              this.curationLoadNum++;

              // console.log('전', res.data.content);

              // for(let i = 0; i < res.data.content.length; i++) {
              //   if(this.getDistance(this.latitude, this.longitude, res.data.content[i].postLat, res.data.content[i].postLng) > this.distance) {
              //       console.log(this.distance2)
              //       res.data.content.splice(i, 1);
              //       i--;
              //     }
              //   else{
              //     console.log(this.distance2)
              //   }
              //   }
              // console.log('반경',this.slider)
              // for(let i = 0; i < res.data.content.length; i++) {
              //   const a = { latitude: this.latitude, longitude: this.longitude}
              //   const b = { latitude: res.data.content[i].postLat, longitude: res.data.content[i].postLng}
              //   const d = haversine(a,b, {unit:'kilometer'})
              //   console.log(a)
              //   console.log(b)
              //   console.log(d)
              //   if(d <= this.slider) {
              //       res.data.content.splice(i, 1);
              //       i--;
              //     }
              //   }
              // console.log('후', res.data.content)

              const items = res.data.content;
              for (const i of items) {
                const datas = {
                  postId: i.postId,
                  userId: i.userId,
                  userImage: i.userImage,
                  postLat: i.postLat,
                  postLng: i.postLng,
                  nickname: i.nickname,
                  uploadTime: i.uploadTime,
                  postGpsName: i.postGpsName,
                  image: i.image,
                  likeCnt: i.likeCnt,
                  commentCnt: i.commentCnt,
                  classBig: i.classBig,
                  classSmall: i.classSmall,
                  classWhere: i.classWhere,
                  classWho: i.classWho,
                  content: i.content,
                };

                this.curationPosts.push(datas);
              }

              $state.loaded();
            }, 1000);
          }
          if (res.data.last) {
            setTimeout(() => {
              $state.complete();
            }, 1000);
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },

    // getMylocation() {
    //   navigator.geolocation.getCurrentPosition((pos) => {
    //     // console.log(pos)
    //     this.latitude = pos.coords.latitude;
    //     this.longitude = pos.coords.longitude;
    //   });
    // },

    detailSearch() {
      this.dialog = true;
    },
    selectBig(n) {
      // console.log(n);
      this.small = this.categorys.tag[n - 1].category;
    },
    clear() {
      // 초기화 버튼
    },

    addTag() {
      this.dialog = false;
      // this.type = "curation";

      // console.log(this.selected_1);
      // console.log(this.selected_2);
      // console.log(this.selected_3);
      // console.log(this.selected_4);

      // 선택항목 초기화
      this.tag_big = "";
      this.tag_small = [];
      this.tag_who = [];
      this.tag_where = [];

      this.tag_big = this.categorys.tag[this.selected_1].big_name;
      for (let i = 0; i < this.selected_2.length; i++) {
        this.tag_small.push(
          this.categorys.tag[this.selected_1].category[this.selected_2[i]]
            .small_name
        );
      }

      for (let i = 0; i < this.selected_3.length; i++) {
        this.tag_who.push(this.whos[this.selected_3[i]]);
      }

      for (let i = 0; i < this.selected_4.length; i++) {
        this.tag_where.push(this.wheres[this.selected_4[i]]);
      }

      this.type = "curation";

      this.curationPosts = [];

      this.curationLoadNum = 0;
    },
    //거리조절 아이콘
    season(val) {
      return this.icons[val];
    },
  },
};
</script>

<style scoped>
.distance-bar {
  max-width: 95%;
}
</style>
