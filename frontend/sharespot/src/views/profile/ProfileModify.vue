<template>
  <v-container>
    <!-- 변경: userInfo 받아오면 바꾸자 -->
    <back-menu
      title="프로필 수정"
      :isRight="isRight" 
      type="modify"
      class="mb-1"
    ></back-menu>
    <!-- 프로필 사진 변경 start -->
    <v-row class="text-center" align="center">
      <v-col>
        <v-avatar color="grey lighten-2" size="70">
          <v-icon v-if="user.img == ''" color="#289672"> mdi-plus </v-icon>
          <v-img v-if="user.img != ''" :src="user.img"></v-img>
        </v-avatar>
      </v-col>
    </v-row>
    <v-row class="text-center" align="center">
      <v-col cols="1" style="margin: 0"></v-col>
      <v-col cols="10" style="margin: 0">
        <label for="file">
          <div style="text-align: center; color: #289672; font-weight: bold">
            프로필 사진 변경
          </div>
        </label>
        <v-file-input
          @change="previewImg(user.img)"
          dense
          hide-input
          v-model="user.img"
          color="#289672"
          prepend-icon=""
          ref="userImg"
          id="file"
        ></v-file-input>
      </v-col>
      <v-col cols="1"></v-col>
    </v-row>
    <!-- 프로필 사진 변경 end -->
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
    <v-row algin="center"> </v-row>
  </v-container>
</template>

<script src="https://cdn.jsdelivr.net/npm/exif-js"></script>
<script>
import BackMenu from "@/components/layout/BackMenu.vue";

export default {
  components: { BackMenu },
  name: "ProfileModify",

  data() {
    return {
      isRight: true,
      user: {
        nickName: "",
        introduce: "",
        phone: "",
        gender: "",
        /*birth: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
          .toISOString()
          .substr(0, 10),*/
        PB: "",
        BR: "",
        img: "",
      },
      modal: false,
    };
  },

  methods: {
    test() {
      console.log(this.user.img);
    },

    previewImg(img) {
      // fileData: reader.result로 나온 토큰 값을 this.user.img에 넣어준다.
      const fileData = (data) => {
        this.user.img = data;
      };

      const reader = new FileReader();
      reader.readAsDataURL(img); // Web API의 함수
      reader.addEventListener(
        "load",
        function () {
          fileData(reader.result); // 파일의 내용을 반환
        },
        false
      );
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
