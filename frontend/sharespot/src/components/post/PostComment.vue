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
        <!-- 검색창 -->
        <v-col cols="2">
          <v-list-item-avatar>
            <v-img v-if="this.userInfo.profileImage == null">
              <v-icon size="50">mdi-account-circle</v-icon></v-img
            >
            <v-img v-else :src="this.userInfo.profileImage"></v-img>
          </v-list-item-avatar>
        </v-col>
        <v-col cols="8" style="padding: 0" class="mt-8">
          <!-- <div class="text-center text-no-wrap rounded-pill" style="color:#289672;">
          <v-text-field
            pa-4 text-no-wrap rounded-pill
            @keyup.enter="search(searchContent)"
          ></v-text-field> -->
          <v-text-field
            v-model="commentContent"
            label="댓글 작성"
            outlined
            rounded
            dense
            @keyup.enter="search(searchContent)"
          ></v-text-field>
          <!-- </div> -->
          <!-- keyup 바꿔줘야됨 -->
        </v-col>
        <!-- MY 버튼-->
        <v-col cols="2" allign="center" @click="submitComment()" style="padding: 0%">
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
    };
  },
  async created() {
    // console.log(this.$route.params.postno)
    const comment = await http.get(`/main/posts/main/posts/${this.$route.params.postno}`);
    console.log("하는중");
    console.log(comment);
    this.comments = comment.data;
    console.log(comment.data);
  },

  computed: {
    ...mapState(userStore, ["userInfo"]),
  },

  methods: {
    async submitComment() {
      console.log("ㅎㅇ");
      console.log(this.$route.params.postno);
      const res = {
        uploadTime: "2022-08-11T06:02:10.994Z",
        userImage: "string",
        userNick: "string",
        comment: this.commentContent,
        postId: Number(this.$route.params.postno),
        userId: this.userInfo.user_id,
      };
      console.log(res);

      await http.post(`/main/posts/${this.$route.params.postno}/comments`, res);

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
