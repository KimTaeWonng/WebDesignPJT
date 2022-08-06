<template>
  <div>
    <v-row class="text-center d-flex" align="center" style="margin: 3px">
      <!-- 검색창 -->
      <v-col cols="1"></v-col>
      <v-col cols="9" style="padding: 0">
        <v-text-field
          prepend-inner-icon="mdi-magnify"
          placeholder="검색어를 입력하세요"
          clearable
          color="black"
          v-model="searchContent"
          @keyup.enter="search(searchContent)"
        ></v-text-field>
      </v-col>
      <!-- MY 버튼-->
      <v-col cols="2" align="center">
        <span style="color: #289672" @click.stop="dialog = true">MY</span>

        <v-dialog v-model="dialog" max-width="290">
          <v-card class="mx-auto" max-width="500">
            <br />
            <div style="font-weight: bold; font-size: 5vw" class="text-center">
              가입 모임 보기
            </div>

            <v-list>
              <v-list-group v-for="item in items" :key="item.title">
                <template v-slot:activator>
                  <v-list-item-content
                    style="font-weight: bold; font-size: 4vw"
                  >
                    <v-list-item-title v-text="item.title"></v-list-item-title>
                  </v-list-item-content>
                </template>

                <v-list-item v-for="child in item.items" :key="child.title">
                  <v-list-item-content
                    style="font-weight: bold; font-size: 3vw"
                  >
                    <v-list-item-title v-text="child.title"></v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
              </v-list-group>
            </v-list>
          </v-card>
        </v-dialog>
      </v-col>
    </v-row>
  </div>
</template>

<script>
export default {
  name: "SearchBar",

  data() {
    return {
      searchContent: "",
      dialog: false,
      items: [
        {
          action: "mdi-ticket",
          items: [
            { title: "7월 27일 강남 보드게임 정모~~" },
            { title: "7월 28일 역삼 보드게임 정모해용" },
            { title: "7월 29일 사당 보드게임 정모~~" },
          ],
          title: "[보드게임] 강남보드모임 GB",
        },
        {
          action: "mdi-silverware-fork-knife",
          active: true,
          items: [
            { title: "7월 27일 강남 보드게임 정모~~" },
            { title: "7월 28일 역삼 보드게임 정모해용" },
            { title: "7월 29일 사당 보드게임 정모~~" },
          ],
          title: "[보드게임] 강남보드모임 GB",
        },
        {
          action: "mdi-school",
          items: [
            { title: "7월 27일 강남 보드게임 정모~~" },
            { title: "7월 28일 역삼 보드게임 정모해용" },
            { title: "7월 29일 사당 보드게임 정모~~" },
          ],
          title: "[보드게임] 강남보드모임 GB",
        },
      ],
    };
  },

  mounted() {},

  methods: {
    goBack() {
      this.$router.go(-1);
    },

    // searchContent로 검색하는 함수
    search(searchContent) {
      console.log(searchContent);
    },
  },
};
</script>

<style scoped></style>
