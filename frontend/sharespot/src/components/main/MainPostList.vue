<template>
  <v-list>


        <post-card v-for="(post, i) in posts" :key="i" v-bind="post" ></post-card>

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
      post: {},
    };
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  async created() {

    try {
      const response = await http.get(`/main/posts/follow/${this.userInfo.user_id}`);
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
