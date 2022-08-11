<template>
  <div>
    <post-comment-item v-for="(comment, i) in this.comments" :key="i" :comment="comment"></post-comment-item>

    <div>
      <v-row class="text-center d-flex" align="center">
        <!-- 검색창 -->
        <v-col cols="2">
          <v-list-item-avatar>
            <v-img 
              src="https://cdn.vuetifyjs.com/images/lists/1.jpg"
            ></v-img>
          </v-list-item-avatar>
        </v-col>
        <v-col cols="8" style="padding: 0;" class="mt-8">
        <!-- <div class="text-center text-no-wrap rounded-pill" style="color:#289672;">
          <v-text-field
            pa-4 text-no-wrap rounded-pill
            @keyup.enter="search(searchContent)"
          ></v-text-field> -->
          <v-text-field
            v-model="commentContent"
            label="Filled"
            placeholder="댓글달기..."
            outlined
            rounded
            dense
            @keyup.enter="search(searchContent)"
          ></v-text-field>
        <!-- </div> -->
          <!-- keyup 바꿔줘야됨 -->
        </v-col>
        <!-- MY 버튼-->
        <v-col cols="2" allign="center" @click="submitComment()" style="padding:0%">
        <v-btn icon>
          <span style="color: #289672; font-weight: 700;">확인</span>
        </v-btn>
        </v-col>
      </v-row>
    </div>
  </div>

</template>

<script>
import { http } from "@/js/http.js";
import PostCommentItem from './PostCommentItem.vue'
const userStore = "userStore";
import { mapState } from "vuex";

export default {
  name: 'PostComment',
  components: { PostCommentItem },
  props: {
  },
  data() {
    // loading: false,
    // selection: 1,
    return {
      commentContent: "",
      comments: []
    }
  },
   async created() {
    // console.log(this.$route.params.postno)
     const comment = await http.get(`/main/posts/main/posts/${this.$route.params.postno}`);
     console.log('여기')
    console.log(comment)
    this.comments = comment.data
    console.log(comment.data)
  },

computed: {
    
    ...mapState(userStore, ["userInfo"]),

  },

  methods: {
    async submitComment() {
      console.log('ㅎㅇ')
      console.log(this.$route.params.postno)
      const res = {
    "comment": this.commentContent,
    "postId": Number(this.$route.params.postno),
    "userId": this.userInfo.user_id,
      }
      console.log(res)

      await http.post(`/main/posts/${this.$route.params.postno}/comments`, res);
    },
  },

}
</script>

<style scoped>
  #v-chip {
    color: #289672;
  }
</style>