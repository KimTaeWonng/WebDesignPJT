<template>
  <v-app>
    <router-view />
    <!-- <div v-if="this.show"> -->
    <nav-bar :id = id></nav-bar>
    <!-- </div> -->
  </v-app>
</template>

<script>

import NavBar from "./components/layout/NavBar.vue";
export default {
  name: "App",
  components: { NavBar },
  data() {
    return {
      show: false,
      id: "",
    };
  },

  computed: {
    isLogin() {
      if (sessionStorage.length != 0) {
        // console.log(JSON.parse(sessionStorage.getItem("vuex")).userStore.userInfo)
        // this.id = JSON.parse(sessionStorage.getItem("vuex")).userStore.userInfo.user_id;

        return JSON.parse(sessionStorage.getItem("vuex")).userStore.isLogin;
      }
      return false;
    },
  },

  created() {
    if (this.isLogin) {
      this.show = true;
      this.id = JSON.parse(sessionStorage.getItem("vuex")).userStore.userInfo.user_id;
      console.log(this.id)
    }
  },

  watch: {
    // $route(to) {
    //   if (
    //     !(
    //       to.name == "login" ||
    //       to.name == "signUp" ||
    //       to.name == "findPass" ||
    //       to.name == "resetPass"
    //     )
    //   ) {
    //     this.show = true;
    //   } else {
    //     this.show = false;
    //   }
    // },
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
