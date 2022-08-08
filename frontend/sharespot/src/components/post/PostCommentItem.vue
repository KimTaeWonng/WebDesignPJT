<template>
    <!-- 개인프로필 표시 -->
    <v-list-item style="padding:0%;">
        <!-- :alt="`${chat.title} avatar`"  이거 ${user.username} 이런식으로 불러오기-->
      <v-col cols="2" allign="center">
      <v-list-item-avatar>
          <v-img
            src="https://cdn.vuetifyjs.com/images/lists/1.jpg"
          ></v-img>
      </v-list-item-avatar>
      </v-col>
      <v-col cols="8" style="padding:4px;">
      <div>
      <v-list-item-content >
        <!--  이거 ${user.username} 이런식으로 불러오기 + 아래는 위치정보 -->
        <v-list-item-subtitle style="font-size: 13px; font-weight: 700;">하위</v-list-item-subtitle>
        <!-- {{ PostComment }} -->
        <v-list-item-subtitle style="font-size: 12px; font-weight: 700;">내용</v-list-item-subtitle>
        <v-row style="padding: 13px">
          <v-col style="padding: 0%; font-size: 12px; font-weight: 400;" cols="2">
            dd1
          </v-col>
          <v-col style="padding: 0%; font-size: 12px; font-weight: 400; align-items: center;" cols="4">
            좋아요 1개
            <!-- 좋아요 {{likecount}} 개 -->
          </v-col>
        </v-row>
      </v-list-item-content>
      </div>
      </v-col>

      <!-- 옵션 버튼 -->
      <v-col>
      <v-btn @click="showOption()" id="dotBtn" icon>
        <v-icon color="red" small>
        mdi-heart-outline
        </v-icon>
      </v-btn>
      </v-col>
    </v-list-item>
</template>

<script>
export default {
  name: 'PostCommentItem',
  props: {

  },
  methods: {

  },

}
</script>

<style scoped>
  #v-chip {
    color: #289672;
  }
</style>