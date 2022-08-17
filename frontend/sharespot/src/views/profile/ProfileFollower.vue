<template>
  <v-container>
    <back-menu title="팔로워" class="mb-1"></back-menu>
    <v-list>
      <v-list-item-group>
       <follow-item v-for="user in followerUsers" :key="`${user.user_id}`" :userDetail="user"></follow-item>
      </v-list-item-group>
    
    </v-list>
  </v-container>
</template>

<script>
import { http } from "@/js/http.js";
import BackMenu from "@/components/layout/BackMenu.vue";
import FollowItem from "../../components/profile/FollowItem.vue";

export default {
  components: { BackMenu, FollowItem }, 
  name: "S07P12A505ProfileFollower",

  data() {
    return {
      followerUsers: []

    };
    // 팔로우 리스트 => 팔로우 버튼을 누르면 => data 팔로우 리스트에 id 넣어주고 이거 db에 쏴줘야겠지 => 언팔로우면 delete 팔로우면 post
      
  },

  mounted() {},
  async created() {
    const follower = await http.get(`/users/${this.$route.params.userid}/follower`);
    

    this.followerUsers = follower.data
    console.log('followerUsers', this.followerUsers)
  },
  methods: {

  },
};
</script>

<style scoped></style>
