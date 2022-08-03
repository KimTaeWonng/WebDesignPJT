<template>
  <v-container>
    <!-- 변경: userInfo 받아오면 바꾸자 -->
    <back-menu
      title="프로필 수정"
      :isRight="isRight"
      type="modify"
      class="mb-1"
    ></back-menu>
    <v-row class="text-center" align="center">
      <!-- 프로필 사진 변경 -->
      <v-col>
        <v-avatar color="grey lighten-2" size="70">
          <v-icon color="#289672"> mdi-plus </v-icon>
        </v-avatar>
      </v-col>
    </v-row>
    <v-row class="text-center" align="center">
      <v-col
        style="padding: 0; color: #289672; font-weight: bold; font-size: 13px"
        >프로필 사진 변경</v-col
      >
    </v-row>
    <!-- 변경 폼 영역 start -->
    <!-- 계정 공개/비공개 여부 -->
    <v-row align="center">
      <v-col cols="4" align="center" id="formTitle" style="margin: 0">
        <span>비공개 계정</span>
      </v-col>
      <v-col cols="8" id="formContent">
        <v-switch v-model="user.PB" color="#289672"></v-switch>
      </v-col>
    </v-row>
    <!-- 닉네임 start -->
    <v-row align="center">
      <v-col cols="4" align="center" id="formTitle">
        <span>닉네임</span>
      </v-col>
      <v-col cols="7" id="formContent">
        <v-text-field
          v-model="user.nickName"
          clearable
          color="#289672"
        ></v-text-field>
      </v-col>
    </v-row>
    <!-- 닉네임 end -->
    <!-- 한줄소개 start -->
    <v-row align="center">
      <v-col cols="4" align="center" id="formTitle">
        <span>소개</span>
      </v-col>
      <v-col cols="7" id="formContent">
        <v-text-field
          color="#289672"
          v-model="user.introduce"
          clearable
        ></v-text-field>
      </v-col>
    </v-row>
    <!-- 한줄소개 end -->
    <!-- 전화번호 end -->
    <v-row align="center">
      <v-col cols="4" align="center" id="formTitle">
        <span>전화번호</span>
      </v-col>
      <v-col cols="7" id="formContent">
        <v-text-field
          placeholder="01012345678"
          v-model="user.phone"
          clearable
          color="#289672"
        ></v-text-field>
      </v-col>
    </v-row>
    <!-- 전화번호 end -->
    <!-- 생년월일 start -->
    <v-row align="center">
      <v-col cols="4" align="center" id="formTitle" style="margin: 0">
        <span>생년월일</span>
      </v-col>
      <v-col cols="8" id="formContent">
        <v-switch v-model="user.BR" color="#289672"></v-switch>
      </v-col>
    </v-row>
    <!-- <v-row align="center">
      <v-col cols="4" align="center" id="formTitle">
        <span>생년월일</span>
      </v-col>
      <v-col cols="7" id="formContent">
        <v-dialog
          ref="dialog"
          v-model="modal"
          :return-value.sync="user.birth"
          persistent
          width="290px"
        >
          <template v-slot:activator="{ on, attrs }">
            <v-text-field
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
            <v-btn text color="primary" @click="$refs.dialog.save(user.birth)">
              OK
            </v-btn>
          </v-date-picker>
        </v-dialog>
      </v-col>
    </v-row> -->
    <!-- 생년월일 end-->
    <!-- 성별 start -->
    <v-row align="center" style="height: 66px !important">
      <v-col cols="4" align="center" id="formTitle">
        <span>성별</span>
      </v-col>
      <v-col cols="8" id="formContent">
        <!-- 비공개:0, 남:1, 여:2-->
        <v-radio-group v-model="user.gender" row style="margin: 10 !important">
          <v-col cols="3">
            <v-radio label="남" color="#289672" value="1"></v-radio>
          </v-col>
          <v-col cols="3">
            <v-radio label="여" color="#289672" value="2"></v-radio>
          </v-col>
          <v-col cols="6">
            <v-radio label="비공개" color="#289672" value="0"></v-radio>
          </v-col>
        </v-radio-group>
      </v-col>
    </v-row>
    <!-- 성별 end -->
    <!-- 변경 폼 영역 end -->
    <!-- 대표 뱃지 선택 영역 -->
    <v-row align="center">
      <v-col cols="5" align="center" id="formTitle"> 대표 뱃지 (택3) </v-col>
    </v-row>
    <v-row>대표 뱃지 영역</v-row>
  </v-container>
</template>

<script>
import BackMenu from "@/components/layout/BackMenu.vue";

export default {
  components: { BackMenu },
  name: "ProfileModify",

  data() {
    return {
      isRight: true,
      user: {
        nickName: "예원",
        introduce: "한줄 소개",
        phone: "",
        gender: "",
        /*birth: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
          .toISOString()
          .substr(0, 10),*/
        PB: "",
        BR: "",
      },
      modal: false,
    };
  },

  mounted() {},

  methods: {
    test() {
      console.log(this.user);
    },
  },
};
</script>

<style scoped>
#formTitle {
  font-weight: bold;
  font-size: 15px;
}

#formContent {
  padding: 0px;
  font-size: 15px;
}

.v-text-field {
  margin: 0 !important;
}
</style>
