<template>
  <v-container>
    <back-menu title="팔로잉" class="mb-1"></back-menu>
    <v-list>
      <v-list-item-group>
       <follow-item v-for="(user, k) in followingUsers" :key="k" :userDetail="user"></follow-item>
      </v-list-item-group>

        <!-- <v-list-item v-for="(user, i) in followingUsers" :key="i">
          <v-list-item-avatar @click="moveProfile(user.user_id)">
            <v-img :src="user.profileImage"></v-img>
          </v-list-item-avatar>
          <v-list-item-content class="mr-3" @click="moveProfile()">
            <v-list-item-title v-text="user.nickname" style="font-weight: bold;"></v-list-item-title>
            <v-list-item-subtitle v-text="user.introduce" style="font-size: 13px;"></v-list-item-subtitle>
          </v-list-item-content>
         -->
          <!-- <v-btn
            v-if="this.followingUsers.indexOf(user.user_id) == -1"
            class="profile-btn"
            style="height: 25px; font-size: 12px"
            color="#289672"
            @click="clickFollow()"
            >팔로우</v-btn
          > -->

          <!-- <v-btn
            v-if="this.isfollow == false"
            class="profile-btn"
            style="height: 25px; font-size: 12px"
            color="#289672"
            @click="clickFollow()"
            >팔로우</v-btn
          > -->

          <!-- <v-btn
            v-else
            class="profile-btn"
            style="height: 25px; font-size: 12px"
            color="#289672"
            @click="clickUnFollow()"
            >언팔로우</v-btn> -->
          <!-- <v-btn
            class="following-btn"
            style="height: 25px; font-size: 12px;"
            color="#f3f3f3"
            
            >팔로잉</v-btn
          > -->
        <!-- </v-list-item>
      </v-list-item>-->
    </v-list>
  </v-container>
</template>

<script>
import { http } from "@/js/http.js";
import { mapState } from "vuex";
import BackMenu from "@/components/layout/BackMenu.vue";
import FollowItem from "../../components/profile/FollowItem.vue";

const userStore = "userStore";
const userLogStore = "userLogStore";
export default {
  components: { BackMenu, FollowItem },
  name: "S07P12A505ProfileFollowing",

  data() {
    return {
      followingUsers: [
      ],
      

    };
  },

  mounted() { },
  async created() {

        // 만약 내 페이지면 팔로잉리스트를 겟할 필요가 없음
    console.log('this.userInfo.user_id',this.userInfo.user_id)
    if (this.userInfo.user_id == this.$route.params.userid) {
      this.followingUsers = this.followingUserList
    }
    else {
      const following = await http.get(`users/${this.$route.params.userid}/following`); 
      this.followingUsers = following.data
    }
    console.log('followingUsers', this.followingUsers)

    
    // const following = await http.get(`/users/${this.$route.params.userid}/following`);
    // console.log('여기')
    // console.log(following.data)
    // this.followingUsers = following.data
    

    // 팔로우 여부 리스트 만들어서 인덱스 슬라이싱
    //[팔로, 아님, 아님, 팔로]

  },
  computed: {
    
    ...mapState(userStore, ["userInfo"]),
    ...mapState(userLogStore, ["followingUserList"])

  },
  methods: {

    
    // moveProfile(user_id) {
    //     this.$router.push({
    //       name: "profile",
    //       params: {userid: user_id}
        
    //   })
    // },

    }
};
</script>

<style scoped></style>
