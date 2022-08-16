<template>
<div @click="deleteInfo">
    <v-alert
      dense
      type="info"
      v-if="isFollowEmpty"
    >
      팔로우한 유저의 게시글이 없어서 모든 게시물이 표시됩니다.
    </v-alert>
  <v-list>
    <post-card
      v-for="(post, i) in posts"
      :key="i"
      v-bind="post"
      :detailPost="post"
    ></post-card>
  </v-list>
  <infinite-loading  @infinite="infiniteHandler" spinner="wavedots">
      <div slot="no-more" style="color: rgb(102, 102, 102); font-size: 14px; padding: 10px 0px">
        게시글을 다 봤어요 :)
      </div>
    </infinite-loading>
  </div>
</template>

<script>
import { http } from "@/js/http.js";
import { mapState } from "vuex";
import InfiniteLoading from "vue-infinite-loading";
import PostCard from "../post/PostCard.vue";

const userStore = "userStore";

export default {
  components: { PostCard, InfiniteLoading },
  name: "SharespotMainPostList",

  data() {
    return {
      posts: [],
      loadNum: 0,
      loadSize: 5,
      isFollowEmpty: false,
    };
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  async created() {
    // 내가 팔로잉한 유저들의 게시글 조회
    // try {
    //   let response = await http.get(
    //     `/main/posts/follow/${this.userInfo.user_id}`
    //   );
    //   // console.log(response.data.length);
    //   if(response.data.length === 0){
    //     this.isFollowEmpty = true;
    //     // console.log("팔로잉 게시글이 없넹")
    //     response = await http.get(
    //       `/main/posts`
    //     );
    //     this.posts = response.data;
    //     }else{
    //       // console.log("팔로잉 게시글이 생겼어!")
    //       this.posts = response.data;
    //     }
    // } catch (error) {
    //   alert("MainPost 게시물들 조회 실패");
    // }
  },
  mounted() {},

  methods: {
    deleteInfo(){
      this.isFollowEmpty = false;
    },

    async infiniteHandler($state) {
      // 최신피드 (무한스크롤) 조회
      await http
        .get(`/main/posts/follow/${this.userInfo.user_id}`, {
          params: {
            page: this.loadNum,
            size: this.loadSize,
          },
        })
        .then((res) => {
          
          if (res.data.totalPages == this.loadNum) {
            $state.complete();
          } else {
            setTimeout(() => {
              this.loadNum++;

              const items = res.data.content;
              console.log(items.length);
              console.log("data "+ res.data.totalPages);
              
              for (const i of items) {
                const data = {
                  postId: i.postId,
                  userId: this.userInfo.user_id,
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
          if(res.data.last){
            setTimeout(()=>{
              $state.complete();
            },1000);
            
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
