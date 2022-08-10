<template>
  <v-container>
    <back-menu title="팔로잉" class="mb-1"></back-menu>
    <v-list>
      <v-list-item-group >
        <v-list-item v-for="(user, i) in followingUsers" :key="i">
          <v-list-item-avatar @click="moveProfile(user.user_id)">
            <v-img :src="user.profile_image"></v-img>
          </v-list-item-avatar>
          <v-list-item-content class="mr-3" @click="moveProfile()">
            <v-list-item-title v-text="user.nickname" style="font-weight: bold;"></v-list-item-title>
            <v-list-item-subtitle v-text="user.introduce" style="font-size: 13px;"></v-list-item-subtitle>
          </v-list-item-content>
         
          <v-btn
            class="following-btn"
            style="height: 25px; font-size: 12px;"
            color="#f3f3f3"
            
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
  name: "S07P12A505ProfileFollowing",

  data() {
    return {
      followingUsers: [
      ],
      

    };
  },

  mounted() { },
  async created() {
    // const following = await http.get(`/users/${this.$route.params.userid}/following`);
    const following = await http.get(`/users/${this.$route.params.userid}/following`);
    console.log('여기')
    console.log(following.data)
    this.followingUsers = following.data
  },

  methods: {
    moveProfile(user_id) {
        this.$router.push({
          name: "profile",
          params: {userid: user_id}
        
      })
    },
    cancelFollowing(){
      console.log("팔로우 취소")
    }
  },
};
</script>

<style scoped></style>
