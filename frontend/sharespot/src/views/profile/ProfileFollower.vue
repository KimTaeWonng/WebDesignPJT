<template>
  <v-container>
    <back-menu title="팔로워" class="mb-1"></back-menu>
    <v-list>
      <v-list-item-group>
        <v-list-item v-for="(user, i) in followingUsers" :key="i" >
          <v-list-item-avatar @click="moveProfile()">
            <v-img :src="user.profile_image"></v-img>
          </v-list-item-avatar>
          <v-list-item-content class="mr-3" @click="moveProfile()">
            <v-list-item-title v-text="user.nickname" style="font-weight: bold"></v-list-item-title>
            <v-list-item-subtitle
              v-text="user.introduce"
              style="font-size: 13px"
            ></v-list-item-subtitle>
          </v-list-item-content>

          <!-- 변경: 팔로잉 안한 상태일 때, 버튼 변경 필요 -->
          <!-- 이미 팔로잉되어있는 상태 버튼 -->
          <v-btn
            class="following-btn"
            style="height: 25px; font-size: 12px;"
            color="#f3f3f3"
            
            >팔로잉</v-btn
          >
        </v-list-item>
      </v-list-item-group>
    </v-list>
  </v-container>
</template>

<script>
import BackMenu from "@/components/layout/BackMenu.vue";

export default {
  components: { BackMenu },
  name: "S07P12A505ProfileFollower",

  data() {
    return {
      followingUsers: [
        {
          user_id: 1,
          nickname: "맛집찾아삼만리",
          introduce:
            "서울프로맛집러에요~~~ 분식, 일식 위주로 글 올립니다!! 가끔 카페도 추천해드려요 >_<",
          profile_image: "https://cdn.vuetifyjs.com/images/lists/1.jpg",
          isBadge: null,
        },
        {
          user_id: 2,
          nickname: "맛집찾아삼만리2",
          introduce:
            "서울프로맛집러에요~~~ 분식, 일식 위주로 글 올립니다!! 가끔 카페도 추천해드려요 >_<",
          profile_image: "https://cdn.vuetifyjs.com/images/lists/2.jpg",
          isBadge: null,
        },
        {
          user_id: 3,
          nickname: "맛집찾아삼만리3",
          introduce:
            "서울프로맛집러에요~~~ 분식, 일식 위주로 글 올립니다!! 가끔 카페도 추천해드려요 >_<",
          profile_image: "https://cdn.vuetifyjs.com/images/lists/3.jpg",
          isBadge: null,
        },
        {
          user_id: 4,
          nickname: "맛집찾아삼만리4",
          introduce:
            "서울프로맛집러에요~~~ 분식, 일식 위주로 글 올립니다!! 가끔 카페도 추천해드려요 >_<",
          profile_image: "https://cdn.vuetifyjs.com/images/lists/4.jpg",
          isBadge: null,
        },
        {
          user_id: 5,
          nickname: "맛집찾아삼만리5",
          introduce:
            "서울프로맛집러에요~~~ 분식, 일식 위주로 글 올립니다!! 가끔 카페도 추천해드려요 >_<",
          profile_image: "https://cdn.vuetifyjs.com/images/lists/5.jpg",
          isBadge: null,
        },
      ],
      
    };
  },

  mounted() {},

  methods: {
    moveProfile(){
      console.log("해당 유저의 프로필로 이동")
    },
    cancelFollowing(){
      console.log("팔로우 취소")
    }
  },
};
</script>

<style scoped></style>
