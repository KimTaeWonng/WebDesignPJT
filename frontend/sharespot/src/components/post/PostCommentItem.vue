<template>
  <!-- 개인프로필 표시 -->
  <v-list-item style="padding: 0%">
    <!-- :alt="`${chat.title} avatar`"  이거 ${user.username} 이런식으로 불러오기-->
    <v-col cols="2" allign="center" @click="goProfile()">
      <v-list-item-avatar>
        <v-img v-if="comment.userImage == null">
          <v-icon size="50">mdi-account-circle</v-icon></v-img
        >
        <v-img v-else :src="comment.userImage"></v-img>
      </v-list-item-avatar>
    </v-col>
    <v-col cols="8" style="padding: 4px" @click="goProfile()">
      <div>
        <v-list-item-content>
          <!--  이거 ${user.username} 이런식으로 불러오기 + 아래는 위치정보 -->
          <v-list-item-subtitle style="font-size: 11px; font-weight: 700">{{
            comment.userNick
          }}</v-list-item-subtitle>
          <!-- {{ PostComment }} -->
          <v-list-item-subtitle style="font-size: 12px; font-weight: 700">{{
            comment.comment
          }}</v-list-item-subtitle>
          <v-row style="padding: 13px">
            <v-col
              style="padding: 0%; font-size: 11px; font-weight: 400"
              cols="3"
            >
              <!-- 시간 데이터 추후에 해야됨 -->
              {{ comment.uploadTime | moment("from", "now") }}
            </v-col>
            <v-col
              style="
                padding: 0%;
                font-size: 11px;
                font-weight: 400;
                align-items: center;
              "
              cols="4"
            >
              좋아요 {{ number }}개
            </v-col>
          </v-row>
        </v-list-item-content>
      </div>
    </v-col>
    <!-- 하트 좋아요 -->
    <v-col>
      <v-btn id="dotBtn" icon>
        <v-icon color="red" small v-if="isLikeClicked" @click="UnLike()">
          mdi-heart
        </v-icon>
        <v-icon color="red" small v-else @click="Like()">
          mdi-heart-outline
        </v-icon>
      </v-btn>
    </v-col>
  </v-list-item>
</template>

<script>
export default {
  data() {
    return {
      commentItem: [],
      number: 0,
      isLikeClicked: false,
    };
  },
  name: "PostCommentItem",
  props: {
    comment: Object,
  },

  methods: {
    goProfile() {
      this.$router.push({
        name: "profile",
        params: {
          userid: this.comment.userId,
        },
      });
    },
    Like() {
      this.isLikeClicked = !this.isLikeClicked;
      this.number++;
    },
    UnLike() {
      this.isLikeClicked = !this.isLikeClicked;
      this.number--;
    },
  },
  created() {
    this.number = Math.floor(Math.random() * 6);
  },
};
</script>

<style scoped>
#v-chip {
  color: #289672;
}
</style>
