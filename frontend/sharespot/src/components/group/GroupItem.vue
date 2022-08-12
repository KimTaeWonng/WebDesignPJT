<template>
  <v-container>
    <v-row>
      <v-col cols="12">
        <v-card style="text-align: left; font-size: 5vw">
          <div>
            <p
              style="
                float: left;
                font-weight: bold;
                margin-top: 3%;
                margin-left: 3%;
              "
            >
              <!-- 그룹명, 유저아이디로 대체 -->
              { group_name } <br />
              운영진 | { group_manger }
            </p>

            <!-- 그룹 대표 이미지로 대체 -->
            <p align="right">
              <v-img
                :src="require('/src/assets/logo.png')"
                contain
                width="25%"
                height="25%"
              />
            </p>

            <!-- 그룹 설명으로 대체 -->
            <div style="margin-left: 3%">
              {보드게임 소모임입니다. <br />
              주요 활동지 : 강남, 역삼}
            </div>
          </div>

          <br />

          <div>
            <!-- 년생: 입력값으로 대체, 라디오 버튼 값으로 대체 -->
            <v-chip
              style="
                margin-left: 3%;
                font-weight: bold;
                font-size: 3vw;
                height: 5vw;
              "
              >{ group_age_min }년생부터 { group_age_max }년생까지</v-chip
            >
            <v-chip
              style="
                margin-left: 3%;
                font-weight: bold;
                font-size: 3vw;
                height: 5vw;
              "
              >{ group_gender }</v-chip
            >
          </div>

          <!-- 현재/전체 참여자로 대체 -->
          <div
            style="margin-left: 5%; margin-top: 3%"
            class="d-flex justify-content-between"
          >
            <p style="color: #8cc7b3; font-size: 5vw">3/{ group_limit }명</p>
            <router-link to="/group/detail" style="text-decoration: none">
              <v-chip
                color="#8CC7B3"
                style="
                  margin-left: 5%;
                  font-weight: bold;
                  color: white;
                  font-size: 3vw;
                  height: 5vw;
                "
                >참여가능</v-chip
              >
            </router-link>
          </div>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>

export default {
  name: "GroupItem",
  data() {
    return {
      group: [],
    };
  },

  mounted() {},

  methods: {},
};
</script>
