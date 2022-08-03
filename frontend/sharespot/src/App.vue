<template>
  <v-app>
    <v-main>
      <hot-spot-menu></hot-spot-menu>
      <router-view />
    </v-main>
  </v-app>
</template>

<script>
import HotSpotMenu from './components/curation/HotSpotMenu.vue';

export default {
  components: { HotSpotMenu },
  name: "App",

  data: () => ({
    //
  }),
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
