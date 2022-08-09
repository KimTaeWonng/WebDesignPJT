<template>
  <v-container>
    <v-row class="text-center">
      <div style="margin-top: 20%">
        <img src="@/assets/logo.png" style="width: 50%; height: auto" />
      </div>
    </v-row>
    <v-row justify="center" v-if="isLoginError">
      <v-alert dense outlined type="error">
        <strong>아이디</strong> 혹은 <strong>비밀번호</strong>를 확인해주세요.
      </v-alert>
    </v-row>
    <v-row class="ml-4 mr-4 mb-4">
      <v-text-field
        label="아이디(이메일)"
        v-model="user.email"
        class="input-group--focused"
        color="#289672"
      ></v-text-field>
    </v-row>
    <v-row class="ml-4 mr-4">
      <v-text-field
        :append-icon="isPwdShow ? 'mdi-eye' : 'mdi-eye-off'"
        :type="isPwdShow ? 'text' : 'password'"
        label="비밀번호"
        v-model="user.password"
        class="input-group--focused"
        @click:append="isPwdShow = !isPwdShow"
        @keyup.enter="confirm"
        color="#289672"
      ></v-text-field>
    </v-row>
    <v-row justify="end" class="mr-5 mb-5">
      <router-link
        to="/users/findpass"
        style="color: #289672; text-decoration: none"
      >
        비밀번호를 잊으셨나요?
      </router-link>
    </v-row>

    <v-row justify="center" class="mb-5 mt-10">
      <v-btn color="#289672" dark width="35%" @click="confirm"> 로그인 </v-btn>
    </v-row>
    <v-row justify="center" class="mb-5">
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
    <v-row justify="center" class="mb-5">
      <v-btn color="#99C5B9" dark width="80%">
        <v-col cols="1"> <v-icon small>mdi-login</v-icon></v-col>
        <v-col cols="11">카카오 계정으로 로그인</v-col>
      </v-btn>
    </v-row>
  </v-container>
</template>

<script>
import { mapState, mapActions } from "vuex";

const userStore = "userStore";

export default {
  name: "LoginView",
  data() {
    return {
      user: {
        email: null,
        password: null,
      },
      isPwdShow: false,
    };
  },
  computed: {
    ...mapState(userStore, ["isLogin", "isLoginError"]),
  },
  methods: {
    ...mapActions(userStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      // 토큰 서버에서 생성 후 저장
      await this.userConfirm(this.user);
      // 토큰 받아오기
      let token = sessionStorage.getItem("Authorization");
      if (this.isLogin) {
        await this.getUserInfo(token);
        //console.log(token);
        this.$router.push({ name: "mainList" });
      }
    },
  },
};
</script>

<style scoped></style>
