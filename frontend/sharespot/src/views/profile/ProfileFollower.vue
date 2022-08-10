<template>
  <v-container>
    <back-menu title="팔로워" class="mb-1"></back-menu>
    <v-list>
      <v-list-item-group>
        <v-list-item v-for="(user, i) in followingUsers" :key="i">
          <v-list-item-avatar @click="moveProfile()">
            <v-img :src="user.profile_image"></v-img>
          </v-list-item-avatar>
          <v-list-item-content class="mr-3" @click="moveProfile()">
            <v-list-item-title v-text="user.nickname" style="font-weight: bold"></v-list-item-title>
            <v-list-item-subtitle
              v-text="user.introduce"
              style="font-size: 13px"
            ></v-list-item-subtitle>
          </v-list-item-content>

          <!-- 변경: 팔로잉 안한 상태일 때, 버튼 변경 필요 -->
          <!-- 이미 팔로잉되어있는 상태 버튼 -->
          <v-btn

            class="following-btn"
            style="height: 25px; font-size: 12px;"
            color="#f3f3f3"
            @click="cancelFollowing()"
            >팔로잉</v-btn
          >
        </v-list-item>
      </v-list-item-group>
    </v-list>
  </v-container>
</template>

<script>
import { http } from "@/js/http.js";
import BackMenu from "@/components/layout/BackMenu.vue";

export default {
  components: { BackMenu }, 
  name: "S07P12A505ProfileFollower",

  data() {
    return {
      followingUsers: []

    };
    // 팔로우 리스트 => 팔로우 버튼을 누르면 => data 팔로우 리스트에 id 넣어주고 이거 db에 쏴줘야겠지 => 언팔로우면 delete 팔로우면 post
      
  },

  mounted() {},
  async created() {
    // const following = await http.get(`/users/${this.$route.params.userid}/following`);
    const follower = await http.get(`/users/${this.$route.params.userid}/follower`);
    console.log(follower.data)
  },
  methods: {
    moveProfile(){
      console.log("해당 유저의 프로필로 이동")
    },
    cancelFollowing(){
      console.log("팔로우 취소")
    }
  },
};
</script>

<style scoped></style>
