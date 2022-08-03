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
          <p style="text-align: right;">
            <router-link to="/users/findpass" style="color: #289672; text-decoration:none;">
              비밀번호를 잊으셨나요?
            </router-link>
          </p>
        </div>
      </v-col>

      <v-col cols="12">
        <div>
          <router-link to="/main/mainlist" style="color:white; text-decoration: none">
            <v-btn color="rgb(40,150,114)" dark width="35%"> 
              로그인
            </v-btn>
          </router-link>
        </div>

        <br />
        <div>
          <p style="font-size: 10px; font-weight: bold">
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
          <router-link to="/main/mainlist" style="color:white; text-decoration: none">
            <v-col cols="12">
              <v-btn color="#99C5B9" dark>
                <div class="d-flex justify-content-between" style="width:100%;">
                  <v-col cols="2">
                    <v-icon small>mdi-login</v-icon> 
                  </v-col>
                  <v-col cols="4">
                    카카오 계정으로 로그인
                  </v-col>
                </div>
              </v-btn>
            </v-col>
          </router-link>
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
