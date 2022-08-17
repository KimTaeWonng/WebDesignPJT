<template>
  <v-container>
    <v-container fluid>
      <v-row class="text-center justify-sm">
        <v-col cols="3" @click="goBack()">
          <v-icon>mdi-chevron-left</v-icon>
        </v-col>
        <v-col class="text-center" cols="6"> 프로필 수정 </v-col>
        <v-col cols="3">
          <div style="color: #289672; font-weight: bold" @click="modify()">
            완료
          </div>
        </v-col>
      </v-row>
    </v-container>

    <!-- 프로필 사진 변경 start -->
    <v-row class="text-center" align="center">
      <v-col>
        <v-avatar color="grey lighten-2" size="70">
          <v-icon v-if="user.profileImage == ''" color="#289672">
            mdi-plus
          </v-icon>
          <v-img
            v-if="user.profileImage != ''"
            :src="user.profileImage"
          ></v-img>
        </v-avatar>
      </v-col>
    </v-row>
    <v-row class="text-center" align="center">
      <v-col cols="1" style="margin: 0"></v-col>
      <v-col cols="10" style="margin: 0">
        <div>
          <label for="chooseFile">
            <div style="text-align: center; color: #289672; font-weight: bold">
              프로필 사진 변경
            </div>
          </label>
          <div>
            <form method="post" enctype="multipart/form-data">
              <input
                style="display: none"
                @change="upload"
                accept="image/*"
                color="#289672"
                type="file"
                ref="image"
                id="chooseFile"
                name="chooseFile"
              />
            </form>
          </div>
        </div>
      </v-col>
      <v-col cols="1"></v-col>
    </v-row>
    <!-- 프로필 사진 변경 end -->
    <!-- 변경 폼 영역 start -->
    <!-- 계정 공개/비공개 여부 -->
    <!-- <v-row align="center">
      <v-col cols="4" align="center" id="formTitle" style="margin: 0">
        <span>비공개 계정</span>
      </v-col>
      <v-col cols="8" id="formContent">
        <v-switch v-model="user.PB" color="#289672"></v-switch>
      </v-col>
    </v-row> -->
    <!-- 닉네임 start -->
    <v-row align="center">
      <v-col cols="4" align="center" id="formTitle">
        <span>닉네임</span>
      </v-col>
      <v-col cols="7" id="formContent">
        <v-text-field
          v-model="user.nickname"
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
        <v-switch
          id="birthSwitch"
          @change="brState = !brState"
          style="margin-right: 3%"
          v-model="user.br"
          :label="`${user.br == 1 ? '공개' : '비공개'}`"
          color="#289672"
        ></v-switch>
      </v-col>
    </v-row>
    <!-- 성별 start -->
    <v-row align="center" style="height: 66px !important">
      <v-col cols="4" align="center" id="formTitle">
        <span>성별</span>
      </v-col>
      <v-col cols="8" id="formContent">
        <v-switch
          id="genderSwitch"
          @change="gdState = !gdState"
          style="margin-right: 3%"
          v-model="user.gd"
          :label="`${user.gd == 1 ? '공개' : '비공개'}`"
          color="#289672"
        ></v-switch>
      </v-col>
    </v-row>
    <!-- 성별 end -->
    <!-- 변경 폼 영역 end -->
    <!-- 대표 뱃지 선택 영역 start -->
    <!-- <v-row align="center">
      <v-col cols="5" align="center" id="formTitle"> 대표 뱃지 (택3) </v-col>
    </v-row>
    <v-row algin="center"> </v-row> -->
    <!-- 대표 뱃지 선책 영역 end -->
    <v-dialog v-model="dialogSuccess" max-width="290">
      <v-card>
        <div class=""><br /><br /></div>
        <div class="text-center" style="color: rgb(40, 150, 114)">
          <span class="material-icons" style="font-size: 80px"> task_alt </span>
        </div>
        <div class="text-center mt-5">
          <div style="font-weight: bold; font-size: 6vw">
            {{ userInfo.nickname }} 님의
          </div>
          <div style="font-size: 5vw">프로필 수정이 완료되었습니다!</div>
        </div>

        <div class="text-center mt-10">
          <v-btn color="rgb(40,150,114)" dark @click="goProfile()">
            확인
          </v-btn>
        </div>

        <div>
          <br />
          <br />
        </div>
      </v-card>
    </v-dialog>
    <!-- 수정 실패 모달 -->
    <v-dialog v-model="dialogFail" max-width="290">
      <v-card>
        <div>
          <br />
          <br />
        </div>

        <div class="text-center">
          <v-icon style="color: #ff647c" size="80"
            >mdi-close-circle-outline</v-icon
          >
        </div>

        <div class="text-center mt-5">
          <div style="font-weight: bold; font-size: 6vw">
            {{ userInfo.nickname }} 님의
          </div>
          <div style="font-size: 5vw">프로필 수정에 실패하였습니다!</div>
        </div>

        <div class="text-center mt-10">
          <v-btn color="#ff647c" dark @click="dialogFail = false"> 이전 </v-btn>
        </div>

        <div>
          <br />
          <br />
        </div>
      </v-card>
    </v-dialog>
  </v-container>
</template>

<script src="https://cdn.jsdelivr.net/npm/exif-js"></script>
<script>
import { mapState } from "vuex";
import { http } from "@/js/http.js";

const userStore = "userStore";

export default {
  components: {},
  name: "ProfileModify",

  data() {
    return {
      dialogSuccess: false,
      dialogFail: false,
      isRight: true,
      image: "",
      brState: true,
      gdState: true,
      user: {
        nickname: "",
        introduce: "",
        phone: "",
        gd: "",
        pb: "",
        br: "",
        profileImage: "",
      },
      modal: false,
    };
  },

  async created() {
    this.user.nickname = this.userInfo.nickname;
    this.user.introduce = this.userInfo.introduce;
    this.user.phone = this.userInfo.phone;
    this.user.br = this.userInfo.br;
    this.user.gd = this.userInfo.gd;
    this.user.profileImage = this.userInfo.profileImage;
    if (this.userInfo.br == 1) {
      this.brState = true;
    }
    if (this.userInfo.gd == 1) {
      this.gdState = true;
    }
  },

  computed: {
    ...mapState(userStore, ["userInfo"]),
  },

  methods: {
    goBack() {
      this.$router.go(-1);
    },
    upload() {
      const formData = new FormData();
      const file = this.$refs["image"].files[0];
      formData.append("files", file);
      http
        .post("/file", formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then((res) => {
          const imagePath = res.data[0];
          this.image = `https://i7a505.p.ssafy.io/api/file?imagePath=${imagePath}`;
          this.user.profileImage = this.image;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 프로필 변경 함수
    async modify() {
      // store의 userInfo에 바꾼 내용을 담는다.
      this.userInfo.nickname = this.user.nickname;
      this.userInfo.introduce = this.user.introduce;
      this.userInfo.profileImage = this.user.profileImage;
      this.userInfo.phone = this.user.phone;

      // 스위치가 true: 값 1, false: 값 0 로 변경.
      if (this.brState) {
        this.userInfo.br = 1;
      } else {
        this.userInfo.br = 0;
      }

      if (this.gdState) {
        this.userInfo.gd = 1;
      } else {
        this.userInfo.gd = 0;
      }

      //axios
      const response = await http.put(
        `/users/${this.userInfo.user_id}`,
        this.userInfo
      );

      // 성공시 성공 모달, 실패시 실패 모달 뜬다.
      if (response.data.message == "success") {
        this.dialogSuccess = true;
      } else {
        this.dialogFail = true;
      }
    },

    // 성공 모달에서 해당 유저의 프로필페이지로 페이지 이동
    goProfile() {
      this.$router.push({
        name: "profile",
        params: {
          userid: this.userInfo.user_id,
        },
      });
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
