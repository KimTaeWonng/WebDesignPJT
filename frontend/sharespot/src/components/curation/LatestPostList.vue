<template>
  <div>
    <!-- 최신피드 -->
    <v-list>
      <post-card
        class="post-card"
        v-for="(post, i) in posts"
        :key="i"
        :detailPost="post"
      ></post-card>
    </v-list>
    <infinite-loading @infinite="infiniteHandler" spinner="wavedots">
      <div slot="no-more" style="color: rgb(102, 102, 102); font-size: 14px; padding: 10px 0px">
        게시글을 다 봤어요 :)
      </div>
    </infinite-loading>
  </div>
</template>

<script>
import { http } from "@/js/http.js";
import InfiniteLoading from "vue-infinite-loading";

import PostCard from "../post/PostCard.vue";

export default {
  components: { PostCard, InfiniteLoading },
  name: "SharespotLatestPostList",

  data() {
    return {
      posts: [],
      loadNum: 0,
    };
  },

  mounted() {},

  methods: {
    async infiniteHandler($state) {
      // 최신피드 (무한스크롤) 조회
      await http
        .get(`/main/search/posts/new`, {
          params: {
            page: this.loadNum,
            size: 5,
          },
        })
        .then((res) => {
          if (res.data.totalPages == this.loadNum) {
            $state.complete();
          } else {
            setTimeout(() => {
              this.loadNum++;

              const items = res.data.content;
              for (const i of items) {
                const data = {
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
                this.posts.push(data);
              }

              $state.loaded();
            }, 1000);
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>

<style scoped></style>
