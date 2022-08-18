<template>
  <v-container>
    <back-menu title="팔로잉" class="mb-1"></back-menu>
    <v-list>
      <v-list-item-group>
        <follow-item
          v-for="user in followingUsers"
          :key="`${user.user_id}`"
          :userDetail="user"
        ></follow-item>
      </v-list-item-group>
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
      followingUsers: [],
    };
  },

  mounted() {},
  async created() {
    // 만약 내 페이지면 팔로잉리스트를 겟할 필요가 없음
    // console.log('this.userInfo.user_id',this.userInfo.user_id)
    if (this.userInfo.user_id == this.$route.params.userid) {
      this.followingUsers = this.followingUserList;
    } else {
      const following = await http.get(
        `users/${this.$route.params.userid}/following`
      );
      this.followingUsers = following.data;
    }
    // console.log('followingUsers', this.followingUsers)
  },
  updated() {
    // console.log('updated');
    this.$nextTick(function () {
      // 모든 화면이 렌더링된 후 실행합니다.
    });
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
    ...mapState(userLogStore, ["followingUserList"]),
  },
  methods: {},
};
</script>

<style scoped></style>
