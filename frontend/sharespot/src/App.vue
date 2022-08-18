<template>
  <v-app>
    <router-view />
    <nav-bar v-if="this.show" style="width:100%;"></nav-bar>
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
        return JSON.parse(sessionStorage.getItem("vuex")).userStore.isLogin;
      }
      return false;
    },
  },

  created() {
    if (this.isLogin) {
      this.show = true;
      this.id = JSON.parse(
        sessionStorage.getItem("vuex")
      ).userStore.userInfo.user_id;
    }
  },

  watch: {
    $route(to) {
      if (
        !(
          to.name == "login" ||
          to.name == "signUp" ||
          to.name == "findPass" ||
          to.name == "resetPass"
        )
      ) {
        this.show = true;
      } else {
        this.show = false;
      }
    },
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
