<template>
  <div>



    <!-- 상세 태그 검색 플로팅 버튼 -->
    <v-btn style="margin-bottom: 16%" color="#289672" right bottom fixed  elevation="2" fab
    @click="detailSearch()">
      <v-icon size="30px" color="white">search</v-icon>
    </v-btn>

    <v-dialog v-model="dialog" max-width="600px">
        <v-card>
          <v-card-title class="justify-center" style="font-weight: bolder; font-size: 5vw"
            >태그 상세 검색</v-card-title
          >

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
          <v-item-group multiple align="center" v-model="selected_2">
            <v-subheader>소분류</v-subheader>
            <v-item v-for="(item, i) in this.small" :key="i" v-slot="{ active, toggle }">
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
          <v-item-group multiple align="center" v-model="selected_3">
            <v-subheader>누구랑</v-subheader>
            <v-item v-for="(who, i) in whos" :key="i" v-slot="{ active, toggle }">
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
          <v-item-group multiple align="center" v-model="selected_4">
            <v-subheader>어디서</v-subheader>
            <v-item v-for="(where, i) in wheres" :key="i" v-slot="{ active, toggle }">
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
          <v-card-actions class="mt-2 d-flex justify-end" style="background-color: #ffffff">
            <!-- 초기화 버튼 회색 배경 넣어주기  -->
            <!-- 버튼 색깔 회색 좀 옅은거로 바꿔야 될듯 -->
            <v-btn style="background-color: #f3f3f3" text @click="clear()"> 초기화 </v-btn>
            <v-btn style="background-color: #289672" text dark @click="addTag()"> 확인 </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
  </div>
</template>

<script>
import tag from "@/assets/json/tag.json";

export default {
  name: "S07P12A505CurationPostList",

  data() {
    return {
        dialog: false,

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
    };
  },

  mounted() {},

  methods: {
    detailSearch(){
        this.dialog = true;
    },
    clear(){
        
    },
    addTag(){
        this.dialog = false;

        // 선택된 값을 보내줘서 해당 태그에 맞는 게시물들을 보여줌
    },
  },
};
</script>

<style scoped></style>
