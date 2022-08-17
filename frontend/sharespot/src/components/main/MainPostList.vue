<template>
  <div>
    <v-list>
      <post-card
        v-for="(post, i) in posts"
        :key="i"
        v-bind="post"
        :detailPost="post"
      ></post-card>
    </v-list>
    <infinite-loading @infinite="infiniteHandler" spinner="wavedots">
      <div
        slot="no-more"
        style="color: rgb(102, 102, 102); font-size: 14px; padding: 10px 0px"
      >
        게시글을 다 봤어요 :)
      </div>
    </infinite-loading>

    <v-snackbar
      style="margin-bottom: 17%"
      v-model="snackbar"
      shaped
      color="#289672"
    >
      팔로우한 유저의 게시글이 없어서 <br />모든 게시물이 표시됩니다.

      <template v-slot:action="{ attrs }">
        <v-btn color="#289672" text v-bind="attrs" @click="snackbar = false">
          Close
        </v-btn>
      </template>
    </v-snackbar>
  </div>
</template>

<script>
import { http } from "@/js/http.js";
import { mapState } from "vuex";
import InfiniteLoading from "vue-infinite-loading";
import PostCard from "../post/PostCard.vue";

const userStore = "userStore";
const userLogStore = "userLogStore";

export default {
  components: { PostCard, InfiniteLoading },
  name: "SharespotMainPostList",

  data() {
    return {
      posts: [],
      loadNum: 0,
      loadSize: 5,
      snackbar: false,
    };
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
    ...mapState(userLogStore, ["followingUserList"]),
  },
  async created() {
    // 팔로잉 유저가 없는 경우 스낵바 나타남
    if (this.followingUserList.length === 0) {
      this.snackbar = true;
    }
  },
  mounted() {},

  methods: {
    async infiniteHandler($state) {
      if (this.followingUserList.length === 0) {
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
      } else {
        // 최신피드 (무한스크롤) 조회
        await http
          .get(`/main/posts/follow/${this.userInfo.user_id}`, {
            params: {
              page: this.loadNum,
              size: this.loadSize,
            },
          })
          .then((res) => {
            console.log(res);
            if (res.data.content.length === 0) {
              this.isFollowEmpty = true;
            }
            {
              if (res.data.totalPages == this.loadNum) {
                $state.complete();
              } else {
                setTimeout(() => {
                  this.loadNum++;

                  const items = res.data.content;
                  console.log(items.length);
                  console.log("data " + res.data.totalPages);

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
      }
    },
  },
};
</script>

<style scoped></style>
