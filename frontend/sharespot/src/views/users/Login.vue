<template>
  <v-container>
    <v-row class="text-center">
      <v-col cols="12">
        <div align="center">
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
          <p style="text-align: right;">
            <router-link to="/users/findpass" style="color: #289672; text-decoration:none;">
              비밀번호를 잊으셨나요?
            </router-link>
          </p>
        </div>
      </v-col>

      <v-col cols="12">
        <div>
          <v-btn color="rgb(40,150,114)" dark width="35%"> 로그인 </v-btn>
        </div>

        <br />
        <div>
          <p style="font-size: 15px; font-weight: bold">
            계정이 없으신가요?
            <router-link
              to="/users/signup"
              style="color: #289672; text-decoration: none"
            >
              회원가입</router-link
            >
          </p>
        </div>
        <br />
        <div>
          <!--카카오 로그인 버튼-->
          <v-btn color="#99C5B9" dark width="80%" @click="kakaoLogin()">
            <div class="align-center">
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
  methods: {
    kakaoLogin(){
      
      // Kakao Developers에서 JavaScript 키 할당
      window.Kakao.init('272f421bb03463e4fb2a7f69398737a9') 

      if (window.Kakao.Auth.getAccessToken()) {
        window.Kakao.API.request({
          url: '/v1/user/unlink',
          success: function (response) {
            console.log(response)
          },
          fail: function (error) {
            console.log(error)
          },
        })
        window.Kakao.Auth.setAccessToken(undefined)
      }


      window.Kakao.Auth.login({
        success: function () {
          window.Kakao.API.request({
            url: '/v2/user/me',
            data: {
              property_keys: ["kakao_account.email", "kakao_account.gender"]
            },
            success: async function (response) {
              console.log(response);
            },
            fail: function (error) {
              console.log(error)
            },
          })
        },
        fail: function (error) {
          console.log(error)
        },
      })
    }
  },
};
</script>
