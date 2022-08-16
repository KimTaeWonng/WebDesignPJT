<template>
  <v-container>
    <!-- 상단 네브바 -->
    <v-row
      class="text-center d-flex mb-5"
      align="center"
      style="margin-top: 3px"
    >
      <!-- 닫기 버튼 -->
      <v-col cols="2" @click="changeRouter('')">
        <v-icon>mdi-close</v-icon>
      </v-col>
      <!-- 로고 -->
      <v-col cols="8">
        <span v-if="this.type == 'modify'">게시글 수정</span>
        <span v-else>게시글 작성</span>
      </v-col>
      <!-- 돋보기/설정 버튼 -->
      <v-col cols="2">
        <v-row>
          <!-- 여기에 제출 넣으면 될듯 -->
          <v-col align="center" @click="changeRouter('')">
            <v-icon>mdi-check</v-icon>
          </v-col>
        </v-row>
      </v-col>
    </v-row>
    <!-- 프로필 사진 변경 start 카드 크기 반응형으로 -->
    <!-- <v-row justify="space-around" style="margin: 0%">
      <v-card height="290px" width="290px">
        <v-row class="text-center" style="margin-top: 35%" align="center">
          <v-col>
            <label for="file">
              <v-avatar color="#289672" size="50">
                <v-icon v-if="user.img == ''" color="#ffffff">
                  mdi-plus
                </v-icon>
                <v-img v-if="user.img != ''" :src="user.img"></v-img>
              </v-avatar>
              <v-file-input
                dense
                hide-input
                v-model="user.img"
                color="#289672"
                prepend-icon=""
                id="file"
              ></v-file-input>
            </label>
          </v-col>
        </v-row>
      </v-card>
    </v-row> -->

  
        <!-- :style="{backgroundImage:`url('${image}')`}" -->
        <div class="ml-9" style="margin: 5%">
          <v-row align="center">
            이미지 추가
            <v-btn color="primary" dark icon>
              <label for="file">

                <v-avatar color="#289672" size="20">
                  
                  <label for="chooseFile">
                    <v-icon color="#ffffff" small> mdi-plus </v-icon>
                  </label>

                  <div>
                    <form method="post" enctype="multipart/form-data">
                      <input
                        multiple
                        style="display: none"
                        ref="image"
                        @change="upload()"
                        type="file"
                        id="chooseFile"
                        name="chooseFile"
                        accept="image/*"
                      />
                    </form>
                  </div>

                </v-avatar>

              </label>
            </v-btn>
          </v-row>
        </div>
    
          <!-- <v-row class="text-center" style="margin-bottom:12%; margin-right:12%;" align="center">
            <v-col>
              <label for="file">
                <v-avatar  color="#289672" size="50" style="position:fixed;">
                  <label for="chooseFile">
                    <v-icon  color="#ffffff">
                      mdi-plus
                    </v-icon>
                  </label>
                  
                <div>
                  <form method="post" enctype="multipart/form-data">
                    <input
                      multiple
                      style="display: none"
                      ref="image"
                      @change="uploadImg()"
                      type="file"
                      id="chooseFile"
                      name="chooseFile"
                      accept="image/*"
                    />
                  </form>
                </div>
                </v-avatar>
              </label>
            </v-col>
          </v-row> -->
        
        <v-carousel height="290px" width="290px" hide-delimiter-background v-if="user.img.length">
          <v-carousel-item v-for="(img, i) in user.img" :key="i" :src="img">
          </v-carousel-item>
        </v-carousel>
        <!-- </v-parallax> -->
  
    <!-- 태그추가 + 버튼 -->
    <div class="ml-9" style="margin: 5%">
      <v-dialog v-model="dialog" max-width="600px">
        <template v-slot:activator="{ on, attrs }">
          <v-row align="center"
            >태그 추가
            <v-btn color="primary" dark v-bind="attrs" v-on="on" icon>
              <v-avatar color="#289672" size="20">
                <v-icon color="#ffffff" small> mdi-plus </v-icon>
              </v-avatar>
            </v-btn>
          </v-row>
        </template>
        <v-card>
          <v-card-title
            class="justify-center"
            style="font-weight: bolder; font-size: 5vw"
            >태그 추가
          </v-card-title>

          <!-- 분류 제목 + 버튼 -->
          <!-- 대분류 -->
          <v-item-group mandatory align="center" v-model="selected_1">
            <v-subheader>대분류</v-subheader>
            <v-item v-for="n in 5" :key="n" v-slot="{ active, toggle }">
              <v-btn
                height="64px"
                elevation="0"
                style="margin: 5px"
                :color="active ? '#289672' : ''"
                active-class="white--text"
                :input-value="active"
                @click="toggle(), selectBig(n)"
              >
                {{ categorys.tag[n - 1].big_name }}
              </v-btn>
            </v-item>
          </v-item-group>

          <!-- 소분류 -->
          <v-item-group mandatory align="center" v-model="selected_2">
            <v-subheader>소분류</v-subheader>
            <v-item
              v-for="(item, i) in this.small"
              :key="i"
              v-slot="{ active, toggle }"
            >
              <v-btn
                width="64px"
                height="64px"
                elevation="0"
                style="margin: 5px"
                :color="active ? '#289672' : ''"
                active-class="white--text"
                :input-value="active"
                @click="toggle"
              >
                {{ item.small_name }}
              </v-btn>
            </v-item>
          </v-item-group>

          <!-- 누구랑 -->
          <v-item-group mandatory align="center" v-model="selected_3">
            <v-subheader>누구랑</v-subheader>
            <v-item
              v-for="(who, i) in whos"
              :key="i"
              v-slot="{ active, toggle }"
            >
              <v-btn
                height="64px"
                elevation="0"
                style="margin: 5px"
                :color="active ? '#289672' : ''"
                active-class="white--text"
                :input-value="active"
                @click="toggle"
              >
                {{ who }}
              </v-btn>
            </v-item>
          </v-item-group>

          <!-- 어디서 -->
          <v-item-group mandatory align="center" v-model="selected_4">
            <v-subheader>어디서</v-subheader>
            <v-item
              v-for="(where, i) in wheres"
              :key="i"
              v-slot="{ active, toggle }"
            >
              <v-btn
                width="64px"
                height="64px"
                elevation="0"
                style="margin: 5px"
                :color="active ? '#289672' : ''"
                active-class="white--text"
                :input-value="active"
                @click="toggle"
              >
                {{ where }}
              </v-btn>
            </v-item>
          </v-item-group>

          <!-- 분류 제목 + 버튼 여기까지  -->
          <v-divider class="mt-4"></v-divider>
          <v-card-actions
            class="mt-2 d-flex justify-end"
            style="background-color: #ffffff"
          >
            <!-- 초기화 버튼 회색 배경 넣어주기  -->
            <!-- 버튼 색깔 회색 좀 옅은거로 바꿔야 될듯 -->
            <!-- <v-btn style="background-color: #f3f3f3" text @click="test()"> 초기화 </v-btn> -->
            <v-btn
              style="background-color: #289672"
              text
              dark
              @click="addTag()"
            >
              확인
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </div>

    <!-- 태그 추가 시 칩 표시되는 공간 -->
    <div class="mt-5 ml-6 mb-5" v-show="isSelected">
      <v-chip
        style="
          background-color: #289672;
          font-size: 13px;
          color: white;
          text-align: center;
        "
        class="text-align-center mr-1"
        small
        >{{ tag_big }}</v-chip
      >
      <v-chip
        style="
          background-color: #289672;
          font-size: 13px;
          color: white;
          text-align: center;
        "
        class="text-align-center mr-1"
        small
        >{{ tag_small }}</v-chip
      >
      <v-chip
        style="
          background-color: #289672;
          font-size: 13px;
          color: white;
          text-align: center;
        "
        class="text-align-center mr-1"
        small
        >{{ tag_who }}</v-chip
      >
      <v-chip
        style="
          background-color: #289672;
          font-size: 13px;
          color: white;
          text-align: center;
        "
        class="text-align-center mr-1"
        small
        >{{ tag_where }}</v-chip
      >
    </div>

    <!-- 내용 작성 -->
    <div class="ml-9 mr-9">
      <v-row align="center">
        <span class="mb-3">내용 작성</span>
      </v-row>
      <v-row>
        <v-textarea auto-grow outlined></v-textarea>
      </v-row>
    </div>
  </v-container>
</template>

<script>
import tag from "@/assets/json/tag.json";
import { http } from "@/js/http.js";


export default {
  name: "PostInputItem",
  components: {},
  props: {
    type: String,
  },
  data() {
    return {
      isRight: true,
      image: '',
      user: {
        nickName: "",
        introduce: "",
        PB: "",
        BR: "",
        img: [],
      },
      modal: false,
      dialogm1: "",
      dialog: false,

      // componentKey: 0, // 소분류 key changing 이용하기 위한 key

      categorys: tag, // 대분류,소분류 태그 json
      small: [
        { small_name: "한식" },
        { small_name: "중식" },
        { small_name: "일식" },
        { small_name: "양식" },
        { small_name: "분식" },
        { small_name: "아시안" },
        { small_name: "기타" },
      ],
      whos: ["가족", "친구", "연인", "혼자"],
      wheres: [
        "서울",
        "경기",
        "인천",
        "강원",
        "제주",
        "대전",
        "충북",
        "충남/세종",
        "부산",
        "울산",
        "경남",
        "대구",
        "경북",
        "광주",
        "전남",
        "전주/전북",
      ],

      // 선택한 태그 값들 (1, 2, ...)
      selected_1: "",
      selected_2: "",
      selected_3: "",
      selected_4: "",

      isSelected: false,

      // 선택한 태그 항목들 (맛집, 가족 ...)
      tag_big: "",
      tag_small: "",
      tag_who: "",
      tag_where: "",
    };
  },
  computed: {},
  methods: {
    upload() {
      const formData = new FormData();
      const file = this.$refs["image"].files[0];
      console.log(file)

      formData.append('files', file);
      console.log(formData)

      http.post('/file', formData, {
        headers: {
          'Content-Type' : 'multipart/form-data'
        }
      }).then((res) => {
        console.log(res)
        console.log(res.data[0])

        const imagePath = res.data[0]
        this.image = `https://i7a505.p.ssafy.io/api/file?imagePath=${imagePath}`
        // console.log(this.image)
        this.user.img.push(this.image)
        console.log(this.user.img)
        // console.log(this.group.group_image)
        // http2.get(`/file?imagePath=${imagePath}`)
        

      }).catch((err) => {
        console.log(err)
      })
    },

    test() {
      // console.log(this.user);
      const data = this.categorys;
      console.log(data);
      console.log(data.tag);
      console.log(data.tag[0]);
      console.log(data.tag[0].big_id);
    },
    selectBig(n) {
      // this.componentKey = new Date();
      // this.componentKey = n;

      // this.smalls = this.categorys.tag[n - 1];

      console.log(n);
      this.small = this.categorys.tag[n - 1].category;
    },
    addTag() {
      this.dialog = false;
      this.isSelected = true;
      this.tag_big = this.categorys.tag[this.selected_1].big_name;
      this.tag_small =
        this.categorys.tag[this.selected_1].category[
          this.selected_2
        ].small_name;
      this.tag_who = this.whos[this.selected_3];
      this.tag_where = this.wheres[this.selected_4];
    },
  },
  created() {
    // this.type == 'modify' 인 경우 루트 경로의 게시글 내용 가져오기 함수 필요
  },
};
</script>

<style scoped>
#v-chip {
  color: #289672;
}
</style>
