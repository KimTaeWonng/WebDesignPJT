<template>
  <div>
    <post-comment-item></post-comment-item>
    <post-comment-item></post-comment-item>
    <post-comment-item></post-comment-item>
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
        <v-col cols="2" allign="center" @click="searchContent()" style="padding:0%">
        <v-btn icon>
          <span style="color: #289672; font-weight: 700;">확인</span>
        </v-btn>
        </v-col>
      </v-row>
    </div>
  </div>

</template>

<script>
import PostCommentItem from './PostCommentItem.vue'

export default {
  name: 'PostComment',
  components: { PostCommentItem },
  props: {
  },
  data: () => ({
      loading: false,
      selection: 1,
    }),
  methods: {
  },

}
</script>

<style scoped>
  #v-chip {
    color: #289672;
  }
</style>