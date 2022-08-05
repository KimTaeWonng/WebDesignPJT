<template>
  <v-container>
    <back-menu title="회원가입" class="mb-1"></back-menu>
    <validation-observer ref="observer" v-slot="{ invalid }">
      <form @submit.prevent="submit" class="mr-5 ml-5">

        <!-- 아이디(이메일) 입력 -->
        <validation-provider 
        v-slot="{ errors }" 
        name="아이디"
        rules="required|email|idCheck"
        >
          <v-text-field
            class="mt-3"
            v-model="user.email"
            :error-messages="errors"
            label="아이디(이메일)"
            required
          ></v-text-field>
        </validation-provider>

        <!-- 비밀번호 입력 -->
        <validation-provider
          v-slot="{ errors }"
          name="비밀번호"
          rules="required|confirmed:confirmPwd"
        >
          <v-text-field
            class="mt-3"
            id="password"
            v-model="user.password"
            :counter="20"
            type="password"
            :error-messages="errors"
            label="비밀번호"
            required
            ref=""
          ></v-text-field>
        </validation-provider>

        <!-- 비밀번호 확인 입력 -->
        <validation-provider
          v-slot="{ errors }"
          name="confirmPwd"
          rules="required"
        >
          <v-text-field
            class="mt-3"
            :counter="20"
            type="password"
            :error-messages="errors"
            label="비밀번호 확인"
            v-model="confirmPwd"
            required
          ></v-text-field>
        </validation-provider>

        <!-- 이름 입력 -->
        <validation-provider
          v-slot="{ errors }"
          name="이름"
          :rules="{
            required: true,
            minmax: [2, 6],
          }"
        >
          <v-text-field
            class="mt-3"
            v-model="user.name"
            :counter="6"
            :error-messages="errors"
            label="이름"
            required
          ></v-text-field>
        </validation-provider>

        <!-- 닉네임 입력 -->
        <validation-provider
          v-slot="{ errors }"
          name="닉네임"
          :rules="{
            required: true,
            minmax: [2, 10],
          }"
        >
          <v-text-field
            class="mt-3"
            v-model="user.nickname"
            :counter="10"
            :error-messages="errors"
            label="닉네임"
            required
          ></v-text-field>
        </validation-provider>

        <!-- 전화번호 입력 -->
        <validation-provider
          v-slot="{ errors }"
          name="전화번호"
          :rules="{
            required: true,
            regex: '(01[016789])(\\d{3,4})(\\d{4})',
          }"
        >
          <v-text-field
            class="mt-3"
            v-model="user.phone"
            :error-messages="errors"
            label="전화번호"
            required
          ></v-text-field>
        </validation-provider>

        <!-- 생년월일 입력 -->
        <div id="formContent" style="line-height: 0">
          <v-dialog
            ref="dialog"
            v-model="modal"
            :return-value.sync="user.birth"
            persistent
            width="290px"
          >
            <template v-slot:activator="{ on, attrs }">
              <v-text-field
                class="mt-3"
                label="생년월일"
                id="userBirth"
                v-model="user.birth"
                prepend-icon="mdi-calendar-blank-outline"
                readonly
                v-bind="attrs"
                v-on="on"
                color="#289672"
              ></v-text-field>
            </template>
            <v-date-picker v-model="user.birth" scrollable color="#289672">
              <v-spacer></v-spacer>
              <v-btn text color="primary" @click="modal = false"> Cancel </v-btn>
              <v-btn text color="primary" @click="$refs.dialog.save(user.birth)"> OK </v-btn>
            </v-date-picker>
          </v-dialog>
        </div>

        <div>
          <v-row no-gutters justify="space-between">
            <p style="text-align: left">생년월일 공개여부</p>
            <!-- 생년월일 공개/비공개 토글 -->
            <!-- 토글이 꺼져있는 경우, false/공개/0 / 토클이 켜져있는 경우, true/비공개/1 -->
            <v-switch
              id="birthSwitch"
              @change="brState = !brState"
              class="mt-3"
              style="margin-right: 3%"
              v-model="user.br"
              :label="`${user.br == 1 ? '공개' : '비공개'}`"
              color="#289672"
            ></v-switch>
          </v-row>
        </div>

        <!-- 성별 입력 -->
        <div style="line-height: 0">
          <v-radio-group v-model="user.gender" row>
            <v-radio label="남" value="0" color="#289672"></v-radio>
            <v-radio label="여" value="1" color="#289672"></v-radio>
          </v-radio-group>
        </div>
        <div>
          <v-row no-gutters justify="space-between">
            <p style="text-align: left">성별 공개여부 <v-icon>info</v-icon></p>
            <!-- 성별 공개/비공개 토글 -->
            <v-switch
              id="genderSwitch"
              @change="gdState = !gdState"
              style="margin-right: 3%"
              v-model="user.gd"
              :label="`${user.gd == 1 ? '공개' : '비공개'}`"
              color="#289672"
            ></v-switch>
          </v-row>
        </div>

        <v-row class="mt-2">
          <v-col cols="6">
            <v-btn color="#99C5B9" dark width="100%"> 이전 </v-btn>
          </v-col>
          <v-col cols="6">
            <div>
              <v-btn
                type="submit"
                :disabled="invalid"
                style="background-color: #289672; color: white"
                width="100%"
                @click="registUser"
              >
                가입하기
              </v-btn>
            </div>
          </v-col>
        </v-row>
      </form>
    </validation-observer>
  </v-container>
</template>

<script>
import { http } from "@/js/http.js";
import { required, email, max, regex, confirmed } from "vee-validate/dist/rules";
import { extend, ValidationObserver, ValidationProvider, setInteractionMode } from "vee-validate";

import BackMenu from "@/components/layout/BackMenu.vue";

setInteractionMode("eager");

extend("confirmed", {
  ...confirmed,
  message: "비밀번호와 비밀번호 확인이 일치하지 않습니다..",
});

extend("required", {
  ...required,
  message: "{_field_}(을)를 입력해주세요.",
});

extend("minmax", {
  validate(value, { min, max }) {
    return value.length >= min && value.length <= max;
  },
  params: ["min", "max"],
  message: "{_field_}(은)는 {min}~{max}자로 입력해주세요.",
});

extend("idCheck", {
  async validate(value){
    const response = await http.get(`/users/idcheck/${value}`);
    console.log("res:" + response.data);
    console.log("val:" + value);
    return !response.data;
  },
  message: "중복된 아이디가 있습니다."
});

// extend("pwdCheck", {
//   validate(value){
//     console.log("res:");
//     console.log("val:" + value);
//     return false;
//   },
//   message: "비밀번호 확인이 틀렸습니다."
// });

extend("max", {
  ...max,
  message: "{_field_}은 {length}자 이내로 입력해주세요.",
});

extend("regex", {
  ...regex,
  message: "{_field_} 형식이 올바르지 않습니다.",
});

extend("email", {
  ...email,
  message: "이메일 형식이 올바르지 않습니다.",
});

export default {
  components: { BackMenu, ValidationProvider, ValidationObserver },
  name: "SignUpView",

  data() {
    return {
      modal: false,
      brState: true,
      gdState: true,
      
      // 유저 정보
      user: {
        email: "",
        password: "",
        name: "",
        phone: "",
        nickname: "",
        gender: "",
        birth: "",
        alarm: 0,
        user_grade: 0,
        br: 1, // 생일공개여부 
        gd: 1, // 성별공개여부
        bd: 0, // 뱃지보유여부
        pb: 0, // 비공개계정
      },
      confirmPwd: "",

    };
  },

  methods: {
    submit(){
      this.$refs.observer.validate()
    },
    async registUser() {
      
      //생년월일 state가 true일 때 생년월일을 1로, false일 때 0으로 전송
      if(this.brState){
        this.user.br = 1;
      }else{
        this.user.br = 0;
      }
      //성별 state가 true일 때 생년월일을 1로, false일 때 0으로 전송
      if(this.gdState){
        this.user.gd = 1;
      }else{
        this.user.gd = 0;
      }

      const response = await http.post("/users/signup", this.user);
      console.log(response.data);
      console.log(this.user);

      // if (response.data == 1) {
      //   alert("ShareSpot의 가족이 되신 것을 환영합니다.");
      //   // this.$router.push({ name: "mainList" });
      // } else {
      //   alert("ShareSpot 가입에 실패했습니다.");
      // }

      
    },
  },
};
</script>

<style scoped>
* {
  margin: 0px;
}
</style>
