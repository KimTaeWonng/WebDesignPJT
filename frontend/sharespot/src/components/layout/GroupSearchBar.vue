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
      <v-col cols="2" align="center" @click="showMyGroup()">
        <span style="color: #289672">MY</span>
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

    // 내가 가입한 그룹 리스트를 보여주는 함수
    showMyGroup() {
      console.log("click");
    },
  },
};
</script>

<style scoped></style>
