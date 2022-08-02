<template>
  <v-app>
    <v-main>
      <back-menu title="안녕" type="groupDetail" :isRight="isRight"></back-menu>
      <router-view />
      <nav-bar></nav-bar>
    </v-main>
  </v-app>
</template>

<script>
import NavBar from "./components/layout/NavBar.vue";
import BackMenu from "./components/layout/BackMenu.vue";

export default {
  name: "App",
  components: { NavBar, BackMenu },
  data() {
    return {
      isRight: true,
    };
  },
};
</script>
<style>
@import url("https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@700&display=swap");
@import url("https://fonts.googleapis.com/css2?family=Gothic+A1:wght@700&display=swap");
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap");

* {
  font-family: "Nanum Gothic";
}
</style>
