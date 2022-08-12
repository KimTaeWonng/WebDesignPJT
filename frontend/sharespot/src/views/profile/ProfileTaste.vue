<template>
  <v-container>
    <back-menu title="취향분석" class="mb-1"></back-menu>
    <div>지도</div>
    <div>
      <div>{{ currentUser.nickname }} 님의 게시글 분석</div>
    </div>
  </v-container>
</template>

<script>
// /profile/taste/:userid
import BackMenu from "@/components/layout/BackMenu.vue";
import { http } from "@/js/http.js";

export default {
  components: { BackMenu },
  name: "ProfileTaste",

  data() {
    return {
      currentUser: {
        userid: "",
        nickname: "",
      },
    };
  },

  async created() {
    this.currentUser.userid = this.$route.params.userid;
    const response = await http.get(
      `/users/info/user/${this.currentUser.userid}`
    );
    this.currentUser.nickname = response.data;
  },

  mounted() {},

  methods: {},
};
</script>

<style scoped></style>
