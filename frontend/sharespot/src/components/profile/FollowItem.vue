<template>
  <v-list-item>
    <v-list-item-avatar @click="moveProfile(userItem.user_id)">
      <v-img v-if="userItem.profileImage == null">
        <v-icon size="50">mdi-account-circle</v-icon></v-img
      >
      <v-img v-else :src="userItem.profileImage"></v-img>
    </v-list-item-avatar>

    <v-list-item-content class="mr-3" @click="moveProfile(userItem.user_id)">
      <v-list-item-title
        v-text="userItem.nickname"
        style="font-weight: bold"
      ></v-list-item-title>
      <v-list-item-subtitle
        v-text="userItem.introduce"
        style="font-size: 13px"
      ></v-list-item-subtitle>
    </v-list-item-content>

    <v-btn v-if="this.isme == true" hidden plain></v-btn>

    <v-btn
      v-else-if="this.isfollowed == false"
      class="profile-btn"
      style="height: 25px; font-size: 12px"
      color="#289672"
      @click="clickFollow()"
      >팔로우</v-btn
    >

    <v-btn
      v-else-if="this.isfollowed == true"
      class="profile-btn"
      style="height: 25px; font-size: 12px"
      color="#c4c4c4"
      @click="clickUnFollow()"
      >팔로잉</v-btn
    >
  </v-list-item>
</template>

<script>
import { mapState, mapActions } from "vuex";
const userLogStore = "userLogStore";
const userStore = "userStore";

export default {
  data() {
    return {
      userItem: {},
      isfollowed: false,
      isme: false,
    };
  },
  name: "FollowItem",
  props: {
    userDetail: Object,
  },
  async created() {
    this.userItem = this.userDetail;
    // console.log("===========팔로우 아이템==================")

    if (this.followingUserList.length != 0) {
      for (var i = 0; i < this.followingUserList.length; i++) {
        if (this.userItem.user_id == this.userInfo.user_id) {
          this.isme = true;
          break;
        } else if (this.followingUserList[i].user_id == this.userItem.user_id) {
          this.isfollowed = true;
          break;
        }
      }
    }
  },

  computed: {
    ...mapState(userLogStore, ["followingUserList"]),
    ...mapState(userStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(userLogStore, ["follow", "unfollow"]),

    moveProfile(user_id) {
      this.$router.push({
        name: "profile",
        params: { userid: user_id },
      });
    },

    async clickFollow() {
      const res = {
        followerId: this.userInfo.user_id,
        userId: this.userItem.user_id,
      };
      const user = {
        // 팔로우할 유저
        user_id: this.userItem.user_id,
        nickname: this.userItem.nickname,
        profileImage: this.userItem.profileImage,
        introduce: this.userItem.introduce,
      };

      const followInfo = {
        user,
        res,
      };

      this.follow(followInfo);

      this.isfollowed = true;
    },

    async clickUnFollow() {
      const followInfo = {
        followerId: this.userInfo.user_id,
        userId: this.userItem.user_id,
      };

      this.unfollow(followInfo);
      // console.log('언팔로우 성공')

      // console.log('팔로잉유저리스트', this.followingUserList[0])
      this.isfollowed = false;
    },
  },
};
</script>

<style scoped>
.profile-btn {
  color: white;
}
</style>
