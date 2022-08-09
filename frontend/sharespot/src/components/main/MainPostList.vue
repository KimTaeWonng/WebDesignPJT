<template>
  <v-list>
    <template v-for="(item, i) in posts">
      <v-list-item :key="i">
        <post-card></post-card>
      </v-list-item>
    </template>
  </v-list>
</template>

<script>
import { http } from "@/js/http.js";

import PostCard from "../post/PostCard.vue";
export default {
  components: { PostCard },
  name: "SharespotMainPostList",

  data() {
    return {
      posts: [],
    };
  },
  async created() {
    // const data = JSON.parse(sessionStorage.getItem("vuex"));
    // const userid = data.memberStore.userInfo.userid;
    // this.userid = userid;

    try {
      const response = await http.get("/main/posts");
      console.log(response);
      console.log(response.data);
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
