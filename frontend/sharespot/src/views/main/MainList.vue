<template>
  <v-container>
    <main-header></main-header>
    <main-post-list></main-post-list>
  </v-container>
</template>

<script>
import MainHeader from "@/components/main/MainHeader.vue";
import MainPostList from "@/components/main/MainPostList.vue";
import { mapState, mapActions } from "vuex";

const userLogStore = "userLogStore";
const userStore = "userStore";

export default {
  components: { MainHeader, MainPostList },
  name: "S07P12A505MainList",

  data() {
    return {};
  },
  computed: {
    ...mapState(userLogStore, [
      "searchWordList",
      "scrapPostList",
      "likePostList",
      "followingUserList",
      "followUserList",
      "myGroupList",
    ]),
    ...mapState(userStore, ["userInfo"]),
  },
  async created() {
    await this.setFollowingUserList(this.userInfo.user_id);
    await this.setGroupList(this.userInfo.user_id);
  },
  mounted() {},

  methods: {
    ...mapActions(userLogStore, ["setFollowingUserList", "setGroupList"]),
  },
};
</script>

<style scoped></style>
