<template>
  <v-container>
    <v-row>
      <v-carousel cycle height="500" hide-delimiter-background interval="3000">
        <v-carousel-item> </v-carousel-item>
      </v-carousel>
    </v-row>
    <v-row justify="center">
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
    return {};
  },

  mounted() {},

  methods: {
    goLogin() {
      this.$router.push({ name: "login" });
    },
  },
};
</script>

<style scoped></style>
