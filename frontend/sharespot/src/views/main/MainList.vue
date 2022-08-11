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
    ]),
    ...mapState(userStore, ["userInfo"]),
  },
  async created() {
    console.log("created");
    await this.setFollowingUserList(this.userInfo.user_id);
    console.log("===actions작동=====");
    console.log(this.followingUserList);
  },
  mounted() {},

  methods: {
    ...mapActions(userLogStore, ["setFollowingUserList"]),
    // clickFollow() {
    //   this.follow({
    //     userid: 23,
    //     nickname: "예원",
    //   });
    // },
    // clickUnfollow() {
    //   this.unfollow({
    //     userid: 23,
    //     nickname: "예원",
    //   });
    // },
  },
};
</script>

<style scoped></style>
