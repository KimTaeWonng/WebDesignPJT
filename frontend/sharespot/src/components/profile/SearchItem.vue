<template>
<v-list-item @click="moveProfile(userItem.user_id)">
    <v-col cols="2" align="center">
      <v-list-item-avatar @click="moveProfile(userItem.user_id)">
      <v-img v-if="userItem.profileImage == null">
          <v-icon size="60">mdi-account-circle</v-icon></v-img>
      <v-img v-else :src="userItem.profileImage" size="60"></v-img>
    </v-list-item-avatar>
    </v-col>
    <v-col cols="10">
      <v-list-item-content class="mr-3"  @click="moveProfile(userItem.user_id)">
        <v-list-item-title v-text="userItem.nickname" style="font-weight: bold;"></v-list-item-title>
        <v-list-item-subtitle v-text="userItem.introduce" style="font-size: 13px;"></v-list-item-subtitle>
      </v-list-item-content>
    </v-col>

</v-list-item>
</template>

<script>
import { mapState, mapActions } from "vuex";
const userLogStore = "userLogStore";

  export default {
  data() {
    return {
      userItem: {},

      };
  },
  name: "SearchItem",
  props: {
    userDetail: Object,
  },
  async created() {
    this.userItem = this.userDetail;


 
  },

  computed: {
    
    ...mapState(userLogStore, ["searchWordList"]),
  },
  methods: {
    ...mapActions(userLogStore, ["setSearchWordList"]),

    moveProfile(user_id) {
      // 스토어 저장 코드
      const data = {
        user_id: this.userItem.user_id,
        profileImage: this.userItem.profileImage,
        nickname: this.userItem.nickname,
        introduce: this.userItem.introduce
      }
      console.log('유저데이터', data)

      // 중복 제거
      console.log('this.setSearchWordList.length', this.searchWordList)
      if (this.searchWordList.length != 0) {

        for (var i = 0; i < this.searchWordList.length; i++) {
          if (this.searchWordList[i].user_id == this.userItem.user_id) {
            break
          }
          else {
            this.setSearchWordList(data);
          }
        }
      }
      else {
        this.setSearchWordList(data);

      }

      console.log('user_id', user_id)

      //아래는 이동 코드

      this.$router.push({
        name: "profile",
        params: { userid: user_id }
      })
    },
  }
};
</script>

<style scoped>
.profile-btn {
  color: white;
}
</style>