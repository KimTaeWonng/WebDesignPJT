<template>
  <div>
    <div class="mt-3">댓글 목록</div>
    <post-comment-item
      v-for="(comment, i) in this.comments"
      :key="i"
      :comment="comment"
    ></post-comment-item>

    <div>
      <v-row class="text-center d-flex" align="center">
        <v-col cols="2">
          <v-list-item-avatar>
            <v-img v-if="this.userInfo.profileImage == null">
              <v-icon size="50">mdi-account-circle</v-icon></v-img
            >
            <v-img v-else :src="this.userInfo.profileImage"></v-img>
          </v-list-item-avatar>
        </v-col>
        <v-col cols="8" style="padding: 0" class="mt-8">
          <v-text-field
            v-model="commentContent"
            placeholder="댓글 작성"
            outlined
            rounded
            dense
            color="#289672"
            @keyup.enter="submitComment()"
          ></v-text-field>
        </v-col>
        <v-col
          cols="2"
          allign="center"
          @click="submitComment()"
          style="padding: 0%"
        >
          <v-btn icon>
            <span style="color: #289672; font-weight: 700">등록</span>
          </v-btn>
        </v-col>
      </v-row>
    </div>
  </div>
</template>

<script>
import { http } from "@/js/http.js";
import PostCommentItem from "./PostCommentItem.vue";
const userStore = "userStore";
import { mapState } from "vuex";

export default {
  name: "PostComment",
  components: { PostCommentItem },
  props: {},
  data() {
    // loading: false,
    // selection: 1,
    return {
      commentContent: "",
      comments: [],

      badges: {
        badgeCafe: 0,
        badgeComment: 0,
        badgeCulture: 0,
        badgeFeed: 0,
        badgeFollow: 0,
        badgeFollower: 0,
        badgeFood: 0,
        badgeGroup: 0,
        badgeLife: 0,
        badgeMeet: 0,
        badgeTrip: 0,
        mainCafe: 0,
        mainComment: 0,
        mainCulture: 0,
        mainFeed: 0,
        mainFollow: 0,
        mainFollower: 0,
        mainFood: 0,
        mainGroup: 0,
        mainLife: 0,
        mainMeet: 0,
        mainTrip: 0,
        userId: "",
      },
    };
  },
  async created() {
    // console.log(this.$route.params.postno)
    const comment = await http.get(
      `/main/posts/main/posts/${this.$route.params.postno}`
    );
    // console.log(comment);
    this.comments = comment.data;
    // console.log(comment.data);

    const getBadgeList = await http.get(
      `/users/badge/${this.userInfo.user_id}`
    );
    this.badges = getBadgeList.data;
  },

  computed: {
    ...mapState(userStore, ["userInfo"]),
  },

  methods: {
    async submitComment() {
      // console.log(this.$route.params.postno);
      const res = {
        uploadTime: "2022-08-11T06:02:10.994Z",
        userImage: "string",
        userNick: "string",
        comment: this.commentContent,
        postId: Number(this.$route.params.postno),
        userId: this.userInfo.user_id,
      };
      // console.log(res);

      await http.post(`/main/posts/${this.$route.params.postno}/comments`, res);

      if (this.badges.badgeComment == 0) {
        this.badges.badgeComment = 1;
        const modifybadge = await http.put(`/users/badge`, this.badges);
        console.log(modifybadge);
      }

      this.$router.go();
    },
  },
};
</script>

<style scoped>
#v-chip {
  color: #289672;
}
</style>
