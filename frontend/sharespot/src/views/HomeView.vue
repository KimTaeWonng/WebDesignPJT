<template>
  <v-container>
    <v-row class="mb-10">
      <v-carousel
        :show-arrows="false"
        cycle
        height="530"
        hide-delimiter-background
        hide-delimiters
        interval="4000"
        class="mt-5"
      >
        <v-carousel-item v-for="i in 5" :key="i">
          <v-sheet height="100%">
            <v-row align="center" justify="center">
              <div class="text-h4 text-center mt-5" style="font-weight: bold">
                <!-- carousel: 첫 줄-->
                <div
                  class="animate__animated mt-3 mb-3"
                  :class="mainStyle[i - 1]"
                >
                  <span> {{ mainContent1[i - 1] }}</span>
                  <span style="color: #289672">{{ mainContent2[i - 1] }}</span>
                  <span>{{ mainContent3[i - 1] }}</span>
                </div>
                <!-- carousel: 두번째 줄-->
                <p class="text-h4 animate__animated" :class="subStyle[i - 1]">
                  {{ subContent[i - 1] }}
                </p>
              </div>
              <v-img
                class="animate__animated"
                :class="imgStyle[i - 1]"
                height="65%"
                :src="getImg(i)"
              />
            </v-row>
          </v-sheet>
        </v-carousel-item>
      </v-carousel>
    </v-row>
    <v-row justify="center" class="mb-5">
      <v-btn color="#269872" dark width="40%" @click="goLogin()">
        시작하기
      </v-btn>
    </v-row>
    <v-row justify="center">
      <p style="font-size: 4vw; font-weight: bold">
        계정이 없으신가요?
        <router-link
          to="/users/signup"
          style="color: #289672; text-decoration: none"
        >
          회원가입</router-link
        >
      </p>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: "HomeView",

  data() {
    return {
      mainStyle: [
        "animate__fadeInLeft",
        "animate__bounceInRight",
        "animate__bounce",
        "animate__fadeInLeft",
        "animate__bounce",
      ],
      subStyle: [
        "animate__fadeInRight",
        "animate__bounceInLeft",
        "animate__bounce",
        "animate__fadeInRight",
        "animate__bounce",
      ],
      imgStyle: [
        "animate__slideInUp",
        "animate__slideInLeft",
        "animate__",
        "animate__slideInDown",
        "animate__slideInRight",
      ],
      mainContent1: ["당신의 ", "나만의 ", "다양한 ", "원하는 ", "취향이 "],
      mainContent2: ["순간", "장소", "뱃지", "장소", "비슷"],
      mainContent3: ["을,", "를", "를", "를", "한"],
      subContent: [
        "모두의 공간으로.",
        "공유해보세요!",
        "모아보세요!",
        "검색하세요!",
        "유저와 만나보세요!",
      ],
    };
  },

  mounted() {},

  methods: {
    goLogin() {
      this.$router.push({ name: "login" });
    },
    getImg(i) {
      return require("@/assets/" + i + ".png");
    },
  },
};
</script>

<style scoped></style>
