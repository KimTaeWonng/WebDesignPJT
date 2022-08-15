<template>
  <v-list>
    <post-card
      v-for="(post, i) in posts"
      :key="i"
      v-bind="post"
      :detailPost="post"
    ></post-card>
  </v-list>
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
    };
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  async created() {
    // 내가 팔로잉한 유저들의 게시글 조회
    try {
      const response = await http.get(
        `/main/posts/follow/${this.userInfo.user_id}`
      );
      //console.log(response.data);
      this.posts = response.data;
    } catch (error) {
      alert("MainPost 게시물들 조회 실패");
    }
  },
  mounted() {},

  methods: {},
};
</script>

<style scoped></style>
