<template>
  <v-app>
    <v-main>
      <!-- <back-menu title="안녕"></back-menu>
      <main-header></main-header>
      <group-search-bar></group-search-bar>
      <search-bar></search-bar> -->
      <router-view />
      <nav-bar></nav-bar>
    </v-main>
  </v-app>
</template>

<script>
import NavBar from "./components/layout/NavBar.vue";
// import BackMenu from "./components/layout/BackMenu.vue";
// import GroupSearchBar from "./components/layout/GroupSearchBar.vue";
// import SearchBar from "./components/layout/SearchBar.vue";
// import MainHeader from "./components/main/MainHeader.vue";

export default {
  name: "App",
  components: { NavBar },
  // MainHeader, GroupSearchBar, SearchBar, BackMenu 
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
