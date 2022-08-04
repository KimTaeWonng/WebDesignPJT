<template>
  <div>
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
      <v-col cols="8">
      <div>
      <v-list-item-content href="">
        <v-list-item-title>
          <div style="font-weight:bold;">하위</div>
        </v-list-item-title>
        <!--  이거 ${user.username} 이런식으로 불러오기 + 아래는 위치정보 -->
        <v-list-item-subtitle style="font-weight:lighter; font-size: 10px;">서울 강남구 역삼대로 110 </v-list-item-subtitle>
      </v-list-item-content>
      </div>
      </v-col>

      <!-- 옵션 버튼 -->
      <v-col>
      <v-btn @click="showOption()" id="dotBtn" icon>
        <v-icon style="padding:0%" align="right">mdi-dots-horizontal</v-icon>
      </v-btn>
      </v-col>
    </v-list-item>
    <!-- <router-link :to="{ name: 'postDetail', params: { postPk: post.id }}"></router-link> -->
  <v-card
    :loading="loading"
    class="mx-auto"
    max-width="374"
  >
    <template slot="progress">
      <v-progress-linear
        color="deep-purple"
        height="10"
        indeterminate
      ></v-progress-linear>
    </template>

    <!-- 사진 -->
    <v-img
      height="250"
      src="https://cdn.vuetifyjs.com/images/cards/cooking.png"
    ></v-img>
<!-- @click="likeMovie(moviePk)" -->
<!-- 좋아요 버튼 -->
  
    <v-btn icon>
      <v-icon color="red">
        mdi-heart-outline
      </v-icon>
    </v-btn>
    <span style="font-size: 12px; font-weight:lighter;">100개
      <!-- {{ likeCount }}}개 로 데이터 수정 예정--></span>

<!-- 댓글 버튼 여기는 라우터 추가 -->
    <v-btn icon>
      <v-icon>
        mdi-chat-processing-outline
      </v-icon>
    </v-btn>
    <span style="font-size: 12px; font-weight:lighter;">100개
      <!-- {{ commentCount }}}개 로 데이터 수정 예정--></span>
    <br>



    <!-- <template v-for="item in 소분류items" :key="item.id"> 로 하나씩 넣어주기 -->
        <v-chip style="background-color:#289672; font-size: 9px; text-align: center;" 
    class="text-align-center mr-1" small>
    ㅇㅇㅇ

    </v-chip>
    <!-- {{ 소분류.text }} -->
        <v-chip style="background-color:#289672; font-size: 9px; text-align: center;" 
    class="text-align-center mr-1" small>
    ㅇㅇㅇ

    </v-chip>
        <v-chip style="background-color:#289672; font-size: 9px; text-align: center;" 
    class="text-align-center mr-1" small>
    ㅇㅇㅇ

    </v-chip>
    <!-- {{ article.content }}  -->
    <v-card-text class="mt-2" style="padding:0%; font-weight:600;">내일 또 와도 괜찮을 듯</v-card-text>
    <v-btn style="padding:0%; font-weight:lighter; font-size: 10px;" plain small>자세히보기</v-btn>
    
  </v-card>
  </div>
</template>

<script>
export default {
  name: 'MovieCard',
  props: {
    movie: Object,
  },
  computed : {
    movieImgURL () {
      return `https://themoviedb.org/t/p/w600_and_h900_bestv2${this.movie.poster_path}`
    },
  },
  data: () => ({
      loading: false,
      selection: 1,
    }),
  methods: {
    reserve() {
      this.loading = true

      setTimeout(() => (this.loading = false), 2000)
    },
    showOption() {
      this.isMakeMenuOpen = !this.isMakeMenuOpen;
    },
  },

}
</script>

<style scoped>
  #v-chip {
    color: #289672;
  }
</style>