<template>
  <v-app>
    <v-main>
      <main-header></main-header>
      <group-search-bar></group-search-bar>
      <router-view />
      <nav-bar></nav-bar>
    </v-main>
  </v-app>
</template>

<script>
import GroupSearchBar from "./components/layout/GroupSearchBar.vue";
import NavBar from "./components/layout/NavBar.vue";
import MainHeader from "./components/main/MainHeader.vue";

export default {
  name: "App",
  components: { NavBar, MainHeader, GroupSearchBar },
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
