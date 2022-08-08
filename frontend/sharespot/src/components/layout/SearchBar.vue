<template>
  <div>
    <v-row class="text-center d-flex" align="center" style="margin: 3px">
      <!-- < 버튼 -->
      <v-col cols="2" @click="goBack()">
        <v-icon>mdi-chevron-left</v-icon>
      </v-col>
      <!-- 검색창 -->
      <v-col cols="8" style="padding: 0; margin: 0">
        <v-text-field
          placeholder="검색어를 입력하세요"
          clearable
          color="#289672"
          v-model="searchContent"
          @keyup="search(searchContent)"
        ></v-text-field>
      </v-col>
      <!-- 돋보기 버튼 -->
      <v-col
        cols="1"
        class="ml-4"
        style="padding: 0"
        @click="search(searchContent)"
      >
        <v-icon>mdi-magnify</v-icon>
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
