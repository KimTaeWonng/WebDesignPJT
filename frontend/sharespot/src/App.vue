<template>
  <v-app>
    <router-view />
    <nav-bar></nav-bar>
  </v-app>
</template>

<script>
import NavBar from "./components/layout/NavBar.vue";
export default {
  name: "App",
  components: { NavBar },
  data() {
    return {
      isRight: true,
    };
  },
};
</script>
<style>
@import url("https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap");
@import url("https://fonts.googleapis.com/css2?family=Gothic+A1:wght@700&display=swap");
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap");

* {
  font-family: "Nanum Gothic";
}
</style>
