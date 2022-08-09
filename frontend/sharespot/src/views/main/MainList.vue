<template>
  <v-container>
    <main-header></main-header>
    <main-post-list></main-post-list>
  </v-container>
</template>

<script>
import MainHeader from "@/components/main/MainHeader.vue";
import MainPostList from "@/components/main/MainPostList.vue";

export default {
  components: { MainHeader, MainPostList },
  name: "S07P12A505MainList",

  data() {
    return {};
  },

  mounted() {},

  methods: {},
};
</script>

<style scoped></style>
