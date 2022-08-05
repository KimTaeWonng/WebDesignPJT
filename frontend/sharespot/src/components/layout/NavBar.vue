<template>
  <div>
    <!-- 게시글/그룹 등록 버튼 클릭시 나오는 메뉴 -->
  <div style="height: 56px;">
  </div>
    <div v-if="isMakeMenuOpen" id="overlay" @click="makeMenu()"></div>
    <div
      v-if="isMakeMenuOpen"
      class="text-center animate__animated animate__slideInUp"
      id="overlayBtns"
    >
      <div class="my-5">
        <v-btn rounded @click="changeRouter('postRegister')">게시글생성</v-btn>
      </div>
      <div class="my-5">
        <v-btn rounded @click="changeRouter('groupRegister')">그룹생성</v-btn>
      </div>
    </div>
    <!-- 하단 네비바 -->
    <v-bottom-navigation v-model="value" color="#289672" id="navBar">
      <!-- 메인 버튼 -->
      <v-btn value="main" @click="changeRouter('mainList')" id="navBtn">
        <v-icon>mdi-home-variant-outline</v-icon>
      </v-btn>
      <!-- 큐레이션 버튼 -->
      <v-btn value="curation" @click="changeRouter('curationList')" id="navBtn">
        <v-icon>mdi-compass-outline</v-icon>
      </v-btn>
      <v-divider vertical></v-divider>
      <!-- 게시글/그룹 등록 버튼 -->
      <v-btn value="register" @click="makeMenu()" id="navBtn">
        <v-icon>mdi-plus-box-outline</v-icon>
      </v-btn>
      <!-- 그룹 이동 버튼 -->
      <v-btn value="group" @click="changeRouter('groupList')" id="navBtn">
        <v-icon>mdi-account-multiple-outline</v-icon>
      </v-btn>
      <!-- 프로필 이동 버튼 -->
      <v-btn value="profile" @click="changeRouter('profile')" id="navBtn">
        <v-icon>mdi-account-circle</v-icon>
        <!-- 유저 프로필 이미지 가져오기...! -->
      </v-btn>
    </v-bottom-navigation>
  </div>
</template>

<script>
export default {
  name: "NavBar",
  data() {
    return {
      value: "",
      isMakeMenuOpen: false,
    };
  },
  methods: {
    // 네브바 클릭시 페이지 이동하는 함수
    changeRouter(pageName) {
      if (this.$route.path == "/" + pageName) {
        this.isMakeMenuOpen = false;
      } else {
        this.$router.push({ name: pageName });
        this.isMakeMenuOpen = false;
      }
    },

    // 게시글/그룹 등록 버튼 클릭 시 메뉴 나오는 함수
    makeMenu() {
      this.isMakeMenuOpen = !this.isMakeMenuOpen;
    },
  },
};
</script>

<style scoped>
#navBtn {
  background-color: #ffffff !important;
  height: 56px !important;
}

#navBar {
  position: fixed;
  bottom: 0;
  right: 0;
}

#makeMenu {
  position: fixed;
  bottom: 56px;
  left: 45%;
}

#overlay {
  position: fixed;
  width: 100%;
  height: 100%;
  bottom: 56px;
  background-color: rgba(0, 0, 0, 0.46);
}

#overlayBtns {
  position: fixed;
  left: 33%;
  width: 33%;
  bottom: 56px;
}
</style>
