<template>
  <v-row class="text-center d-flex mt-1">
    <v-col cols="3" @click="goBack()">
      <v-icon>mdi-chevron-left</v-icon>
    </v-col>
    <v-col class="text-center" cols="6">
      {{ title }}
    </v-col>
    <v-col cols="3" v-if="isRight" @click="editFinish()"> 완료 </v-col>
  </v-row>
</template>

<script>
export default {
  name: "BackMenu",
  props: {
    title: String,
    isRight: Boolean,
  },

  data() {
    return {};
  },

  mounted() {},

  methods: {
    editFinish() {
      //axios 쓰기
    },

    goBack() {
      this.$router.go(-1);
    },
  },
};
</script>

<style scoped></style>
