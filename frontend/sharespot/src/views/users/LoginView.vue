<template>
  <v-container>
    <v-row class="text-center">
      <v-col cols="12">
        <div style="margin-top: 30%; margin-left: 25%">
          <v-img
            :src="require('/src/assets/logo.png')"
            class="my-3"
            contain
            width="70%"
            height="70%"
          />
        </div>

        <p style="text-align: left; margin-left: 3%">아이디 (이메일)</p>
        <div style="margin-left: 5%; margin-right: 5%; line-height: 0">
          <v-textarea
            label=""
            auto-grow
            outlined
            rows="1"
            row-height="15"
          ></v-textarea>
        </div>

        <br />

        <p style="text-align: left; margin-left: 3%">비밀번호</p>
        <div style="margin-left: 5%; margin-right: 5%; line-height: 0">
          <v-textarea
            label=""
            auto-grow
            outlined
            rows="1"
            row-height="15"
          ></v-textarea>
          <p style="text-align: right; color: #289672">
            비밀번호를 잊으셨나요?
          </p>
        </div>
      </v-col>

      <v-col cols="12">
        <div>
          <v-btn color="rgb(40,150,114)" dark width="35%"> 로그인 </v-btn>
        </div>

        <br />
        <div>
          <p style="font-size: 10px; font-weight: bold">
            계정이 없으신가요?
            <router-link
              to="/about"
              style="color: #289672; text-decoration: none"
            >
              회원가입</router-link
            >
          </p>
        </div>
        <br />
        <div>
          <!--카카오 로그인 버튼-->
          <v-btn color="#99C5B9" dark width="80%">
            <div class="aling-center">
              <v-icon small>mdi-login</v-icon> 카카오 계정으로 로그인
            </div>
          </v-btn>
        </div>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: "LoginView",
};
</script>
