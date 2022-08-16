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
  </div>
</template>

<script>
import { http } from "@/js/http.js";
import { mapState } from "vuex";

import PostCard from "../post/PostCard.vue";

const userStore = "userStore";

export default {
  components: { PostCard },
  name: "SharespotMainPostList",

  data() {
    return {
      posts: [],
      isFollowEmpty: false,
    };
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  async created() {
    // 내가 팔로잉한 유저들의 게시글 조회
    try {
      let response = await http.get(
        `/main/posts/follow/${this.userInfo.user_id}`
      );
      // console.log(response.data.length);
      if(response.data.length === 0){
        this.isFollowEmpty = true;
        // console.log("팔로잉 게시글이 없넹")
        response = await http.get(
          `/main/posts`
        );
        this.posts = response.data;
        }else{
          // console.log("팔로잉 게시글이 생겼어!")
          this.posts = response.data;
        }
    } catch (error) {
      alert("MainPost 게시물들 조회 실패");
    }
  },
  mounted() {},

  methods: {
    deleteInfo(){
      this.isFollowEmpty = false;
    },
  },
};
</script>

<style scoped></style>
