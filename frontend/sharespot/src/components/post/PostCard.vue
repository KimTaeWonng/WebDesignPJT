<template>
  <div>
    <!-- 개인프로필 표시 -->
    <v-list-item style="padding:0%;">
        <!-- :alt="`${chat.title} avatar`"  이거 ${user.username} 이런식으로 불러오기-->
      <v-col style="padding: 12px 0px 0px 0px" cols="2" align="center">
      <v-list-item-avatar>
          <v-img
            src="https://cdn.vuetifyjs.com/images/lists/1.jpg"
          ></v-img>
      </v-list-item-avatar>
      </v-col>
      <v-col style="padding: 12px 0px 0px 0px" cols="8">
      <div>
      <v-list-item-content href="">
        <v-list-item-title>
          <div style="font-weight: 600;" class="mb-0.4">{{post.nickname}}</div>
        </v-list-item-title>
        <!--  이거 ${user.username} 이런식으로 불러오기 + 아래는 위치정보 -->
        <v-list-item-subtitle style="font-size: 12px;">{{post.postGpsName}} </v-list-item-subtitle>
      </v-list-item-content>
      </div>
      </v-col>

      <!-- 옵션 버튼 -->
      <v-col style="padding: 0px 0px 0px 0px" align="right">
      <v-btn @click="showOption()" id="dotBtn" icon>
        <v-icon style="padding:0%" align="right">mdi-dots-horizontal</v-icon>
      </v-btn>
      </v-col>
    </v-list-item>
    <!-- <router-link :to="{ name: 'postDetail', params: { postPk: post.id }}"></router-link> -->

    <template slot="progress">
      <v-progress-linear
        color="deep-purple"
        height="10"
        indeterminate
      ></v-progress-linear>
    </template>

    <!-- 사진 -->
    <v-img
      :aspect-ratio="1/1"
      
      :src="this.post.image"
    ></v-img>
<!-- @click="likeMovie(moviePk)" -->
<!-- 좋아요 버튼 -->
  
    <v-btn icon>
      <v-icon color="red">
        mdi-heart-outline
      </v-icon>
    </v-btn>
    <span style="font-size: 12px; font-weight:lighter;">{{post.likeCnt}}개
      </span>

<!-- 댓글 버튼 여기는 라우터 추가 -->
    <v-btn icon>
      <v-icon>
        mdi-chat-processing-outline
      </v-icon>
    </v-btn>
    <span style="font-size: 12px; font-weight:lighter;">{{post.commentCnt}}개
      </span>
    <br>



    <!-- <template v-for="item in 소분류items" :key="item.id"> 로 하나씩 넣어주기 -->
        <v-chip style="background-color:#A9D5C7; font-size: 11px; text-align: center;" 
    class="text-align-center mr-1" small>
    {{post.classBig}}

    </v-chip>
    <!-- {{ 소분류.text }} -->
        <v-chip style="background-color:#A9D5C7; font-size: 11px; text-align: center;" 
    class="text-align-center mr-1" small>
    {{post.classSmall}}

    </v-chip>
        <v-chip style="background-color:#A9D5C7; font-size: 11px; text-align: center;" 
    class="text-align-center mr-1" small>
    {{post.classWhere}}
    </v-chip>

        <v-chip style="background-color:#A9D5C7; font-size: 11px; text-align: center;" 
    class="text-align-center mr-1" small>
    {{post.classWho}}
    </v-chip>

    <!-- {{ article.content }}  -->
    <v-card-text class="mt-2" style="padding:0%; ">{{post.content}}</v-card-text>
    <v-btn style="padding:0%; font-size: 12px;" plain small>자세히보기</v-btn>


  </div>
</template>

<script>
export default {
  name: 'PostCard',
  props: {
    
    classBig: String,
    classSmall: String,
    classWhere: String,
    classWho: String,
    commentCnt: Number,
    content: String,
    image: String,
    likeCnt: Number,
    nickname: String,
    postGpsName: String,
    postId : Number,
    postLat: Number,
    postLng: Number,
    uploadTime: String,
    userId : Number,
  },
  created() {
    this.post.classBig = this.classBig;
    this.post.classSmall = this.classSmall;
    this.post.classWhere = this.classWhere;
    this.post.classWho = this.classWho;
    this.post.commentCnt = this.commentCnt;
    this.post.content = this.content;
    this.post.image = this.image;
    this.post.likeCnt = this.likeCnt;
    this.post.nickname = this.nickname;
    this.post.postGpsName = this.postGpsName;
    this.post.postId = this.postId;
    this.post.postLat = this.postLat;
    this.post.postLng = this.postLng;
    this.post.uploadTime = this.uploadTime;
    this.post.userId = this.userId;
  },
  data() {
    return {
      post: {},
    };
  },
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