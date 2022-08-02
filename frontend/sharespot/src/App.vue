<template>
  <v-app>
    <v-main>
      <router-view />
      <nav-bar></nav-bar>
    </v-main>
  </v-app>
</template>

<script>
import NavBar from "./components/layout/NavBar.vue";

export default {
  components: { NavBar },
  name: "App",
  component: "NavBar",
  data: () => ({
    //
  }),
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
