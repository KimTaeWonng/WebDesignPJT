<template>
  <div>
    <!-- 개인프로필 표시 -->
    <v-list-item style="padding: 0%">
      <!-- :alt="`${chat.title} avatar`"  이거 ${user.username} 이런식으로 불러오기-->
      <v-col style="padding: 12px 0px 0px 0px" cols="2" align="center">
        <router-link
          class="link"
          :to="{
            name: 'profile',
            params: { userid: this.post.userId },
          }"
        >
          <v-list-item-avatar>
            <v-img :src="this.post.userImage"></v-img>
          </v-list-item-avatar>
        </router-link>
      </v-col>
      <v-col style="padding: 12px 0px 0px 0px" cols="8">
        <div>
          <v-list-item-content href="">
            <v-row no-gutters>
              <span style="font-weight: 600" class="mb-0.4">
                {{ post.nickname }}
              </span>
              <span class="ml-2 my-1" style="font-size: 10px">
                {{ post.uploadTime | moment("from", "now") }}
              </span>
            </v-row>

            <!--  이거 ${user.username} 이런식으로 불러오기 + 아래는 위치정보 -->
            <v-list-item-subtitle style="font-size: 12px"
              >{{ post.postGpsName }}
            </v-list-item-subtitle>
          </v-list-item-content>
        </div>
      </v-col>

      <!-- 메뉴옵션 버튼 -->
      <v-col style="padding: 0px 0px 0px 0px" align="right">
        <v-btn @click.stop="dialog = true" id="dotBtn" icon>
          <v-icon style="padding: 0%" align="right">mdi-dots-horizontal</v-icon>
        </v-btn>
      </v-col>
    </v-list-item>
    <!-- <router-link :to="{ name: 'postDetail', params: { postPk: post.id }}"></router-link> -->

    <!-- 메뉴옵션 모달 -->
    <v-dialog v-model="dialog" max-width="200">
      <v-card>
        <div class="text-center" style="font-size: 4vw; align-items: center">
          <v-col @click.stop="dialog = false()">
            <span style="color: #ff0000">신고</span>
          </v-col>

          <v-divider></v-divider>
          <v-col>
            <span>링크 복사</span>
          </v-col>

          <v-divider></v-divider>

          <v-col>
            <span>공유하기</span>
          </v-col>

          <v-divider></v-divider>

          <v-col>
            <span
              ><router-link
                class="link"
                :to="{
                  name: 'postDetail',
                  params: { postno: this.post.postId },
                }"
                >게시글로 이동
              </router-link></span
            >
          </v-col>
        </div>
      </v-card>
    </v-dialog>

    <template slot="progress">
      <v-progress-linear color="deep-purple" height="10" indeterminate></v-progress-linear>
    </template>

    <!-- 사진 -->
    <v-img :aspect-ratio="1 / 1" :src="this.post.image"></v-img>

    <v-row no-gutters>
      <v-col cols="8">
        <!-- 좋아요 버튼 -->
        <v-btn icon @click="(like = !like), clickLike()">
          <v-icon color="red"> {{ like ? "mdi-heart" : "mdi-heart-outline" }} </v-icon>
        </v-btn>
        <span style="font-size: 12px; font-weight: lighter">{{ cntLike }}개 </span>

        <!-- 댓글 버튼 -->
        <router-link
          class="link"
          :to="{
            name: 'postDetail',
            params: { postno: this.post.postId },
          }"
        >
          <v-btn icon>
            <v-icon> mdi-comment-processing-outline </v-icon>
          </v-btn>
        </router-link>
        <span style="font-size: 12px; font-weight: lighter">{{ cntComment }}개 </span>
      </v-col>
      <v-col cols="4" align="right">
        <!-- 스크랩 버튼 -->
        <v-btn icon @click="(bookmark = !bookmark), clickBookmark()">
          <v-icon> {{ bookmark ? "mdi-bookmark" : "mdi-bookmark-outline" }} </v-icon>
        </v-btn>
        <!-- 지도 버튼 -->
        <v-btn icon v-if="this.post.postLat != null" @click="clickMap()">
          <v-icon> mdi-map-outline </v-icon>
        </v-btn>
      </v-col>
    </v-row>

    <!-- <template v-for="item in 소분류items" :key="item.id"> 로 하나씩 넣어주기 -->
    <v-chip
      style="background-color: #a9d5c7; font-size: 11px; text-align: center"
      class="text-align-center mr-1"
      small
    >
      {{ post.classBig }}
    </v-chip>
    <!-- {{ 소분류.text }} -->
    <v-chip
      style="background-color: #a9d5c7; font-size: 11px; text-align: center"
      class="text-align-center mr-1"
      small
    >
      {{ post.classSmall }}
    </v-chip>
    <v-chip
      style="background-color: #a9d5c7; font-size: 11px; text-align: center"
      class="text-align-center mr-1"
      small
    >
      {{ post.classWho }}
    </v-chip>

    <v-chip
      style="background-color: #a9d5c7; font-size: 11px; text-align: center"
      class="text-align-center mr-1"
      small
    >
      {{ post.classWhere }}
    </v-chip>

    <!-- {{ article.content }}  -->
    <v-card-text class="mt-2" style="padding: 0%">{{ post.content }}</v-card-text>
    <router-link
      class="link"
      :to="{
        name: 'postDetail',
        params: { postno: this.post.postId },
      }"
    >
      <v-btn style="padding: 0%; font-size: 12px" plain small>자세히보기</v-btn>
    </router-link>
  </div>
</template>

<script>
import { http } from "@/js/http.js";
import { mapState } from "vuex";

const userStore = "userStore";

export default {
  name: "PostCard",
  props: {
    detailPost: Object,
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  async created() {
    this.post = this.detailPost;
    // console.log("포스트카드");
    // console.log(this.detailPost);
    // console.log(this.post);

    this.cntLike = this.post.likeCnt;

    // 좋아요를 이미 한 게시글에 좋아요 유지
    const likeTemp = await http.get(`/LikeScrap/listL/${this.userInfo.user_id}`);
    // console.log(likeTemp);

    for (const likeList of likeTemp.data) {
      if (likeList.postId == this.post.postId) {
        this.like = true;
      }
    }

    // 스크랩을 이미 한 게시글에 스크랩 유지
    const scrapTemp = await http.get(`/LikeScrap/listS/${this.userInfo.user_id}`);
    // console.log(scrapTemp);

    for (const ScrapList of scrapTemp.data) {
      if (ScrapList.postId == this.post.postId) {
        this.bookmark = true;
      }
    }

    // 댓글 갯수 받아오기
    const commentTemp = await http.get(`/main/posts/main/posts/${this.post.postId}`);
    // console.log(commentTemp.data.length);
    this.cntComment = commentTemp.data.length;
  },
  data() {
    return {
      dialog: false,
      like: false,
      bookmark: false,

      cntLike: null,
      cntComment: null,

      post: {},
    };
  },
  methods: {
    async clickLike() {
      // 좋아요가 안눌러진 상태에서 좋아요를 누를 때
      if (this.like) {
        // 유저가 해당 게시글 좋아요 하기 (post)
        // console.log(this.post.postId);
        // console.log(this.userInfo);
        // console.log(this.userInfo.nickname);

        const response = await http.post(
          `/main/posts/like/${this.post.postId}/${this.userInfo.user_id}`,
          {
            userNick: this.userInfo.nickname,
          }
        );
        if (response.data == 1) {
          console.log("좋아요 성공");
        } else {
          console.log("좋아요 실패");
        }

        // 해당 게시글의 좋아요 개수 1 증가시킨 걸로 수정 (put)
        this.post.likeCnt += 1;
        this.cntLike = this.post.likeCnt;
        // console.log(this.post.likeCnt);
        // console.log(this.cntLike);
        // console.log(this.post);

        const response2 = await http.put(`/main/posts/${this.post.postId}`, this.post);
        if (response2.data == 1) {
          console.log("조아요 증가 성공");
        } else {
          console.log("조아요 증가 실패");
        }
        console.log("현재 조아요 갯수: " + this.post.likeCnt);
      }
      // 좋아요가 눌러진 상태에서 좋아요를 취소할 때
      else {
        // 유저가 해당 게시글 좋아요 취소하기 (delete)
        // console.log(this.post.postId);
        // console.log(this.userInfo);
        // console.log(this.userInfo.nickname);

        const response = await http.delete(
          `/main/posts/like/${this.post.postId}/${this.userInfo.user_id}`
        );
        if (response.data == 1) {
          console.log("좋아요 취소 성공");
        } else {
          console.log("좋아요 취소 실패");
        }

        // 해당 게시글의 좋아요 개수 1 감소시킨 걸로 수정 (put)
        this.post.likeCnt -= 1;
        this.cntLike = this.post.likeCnt;

        const response2 = await http.put(`/main/posts/${this.post.postId}`, this.post);
        if (response2.data == 1) {
          console.log("조아요 감소 성공");
        } else {
          console.log("조아요 감소 실패");
        }
        console.log("현재 조아요 갯수: " + this.post.likeCnt);
      }
    },
    async clickBookmark() {
      // 스크랩이 안눌러진 상태에서 스크랩을 누를 때
      if (this.bookmark) {
        // 유저가 해당 게시글 스크랩하기 (post)
        const response = await http.post(
          `/main/posts/scrap/${this.post.postId}/${this.userInfo.user_id}`
        );
        if (response.data == 1) {
          console.log("스크랩 성공");
        } else {
          console.log("스크랩 실패");
        }
      }
      // 스크랩이 눌러진 상태에서 스크랩을 취소할 때
      else {
        // 유저가 해당 게시글 스크랩 취소하기 (delete)
        const response = await http.delete(
          `/main/posts/scrap/${this.post.postId}/${this.userInfo.user_id}`
        );
        if (response.data == 1) {
          console.log("스크랩 취소 성공");
        } else {
          console.log("스크랩 취소 실패");
        }
      }
    },
    async clickMap() {
      let gpsName = this.post.postGpsName;
      console.log(gpsName);
      if (gpsName == null) {
        gpsName = this.post.postLat;
      }
      let link =
        "https://map.kakao.com/link/to/" +
        gpsName +
        "," +
        this.post.postLat +
        "," +
        this.post.postLng;
      window.open(link);
    },
  },
};
</script>

<style scoped>
.link {
  text-decoration: none;
}
</style>
