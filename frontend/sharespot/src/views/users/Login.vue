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
      <router-link to="/users/findpass" style="color: #289672; text-decoration: none">
        비밀번호를 잊으셨나요?
      </router-link>
    </v-row>

    <v-row justify="center" class="mb-5 mt-10">
      <v-btn color="#289672" dark width="35%" @click="confirm"> 로그인 </v-btn>
    </v-row>
    <v-row justify="center" class="mb-5">
      <p style="font-size: 4vw; font-weight: bold">
        계정이 없으신가요?
        <router-link to="/users/signup" style="color: #289672; text-decoration: none">
          회원가입</router-link
        >
      </p>
    </v-row>
    <v-row justify="center" class="mb-5">
      <v-btn color="#99C5B9" dark width="80%" @click="kakaoLogin">
        <v-col cols="1"> <v-icon small>mdi-login</v-icon></v-col>
        <v-col cols="11">카카오 계정으로 로그인</v-col>
      </v-btn>
    </v-row>
  </v-container>
</template>

<script>
import { http } from "@/js/http.js";
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
    ...mapState(userStore, ["isLogin", "isLoginError", "kakaoUserInfo"]),
  },
  methods: {
    ...mapActions(userStore, ["userConfirm", "getUserInfo", "setKakaoUserInfo"]),
    async confirm() {
      // 토큰 서버에서 생성 후 저장
      await this.userConfirm(this.user);
      // 토큰 받아오기
      let token = sessionStorage.getItem("Authorization");
      if (this.isLogin) {
        // 현재 토큰과 로그인한 유저가 일치하는지 확인
        await this.getUserInfo(token);
        // 메인 화면으로 이동
        this.$router.push({ name: "mainList" });
      }
    },
    async kakaoLogin() {
      // const token = `${process.env.VUE_APP_KAKAO_ACCESS_TOKEN}`;

      window.location.replace(
        "https://kauth.kakao.com/oauth/authorize?client_id=75a6c91b7b162696354ad4fc2e5b4a7d&redirect_uri=https://localhost:8080/users/signup&response_type=code"
      );

      const response = await http.post(`/oauth/login`, null, {
        params: {
          token: "IHPbZkwYFcYHvTCz_MIoTB456hTiDkzrdAxSh7klCilwFAAAAYKSgRPF",
        },
      });

      // console.log(response);
      if (response.status == 200) {
        // console.log(response.data);
        this.setKakaoUserInfo(response.data);
        // console.log(this.kakaoUserInfo);
        this.$router.push({ name: "signUp" });
      } else {
        console.log("사용자 정보 조회 실패");
      }
    },
  },
};
</script>

<style scoped></style>
