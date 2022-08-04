<template>
  <v-container>
    <back-menu title="정보" :isRight="isRight" class="mb-1"></back-menu>
    <v-list class="mx-2">
      <v-list-item>
        <v-list-item-title class="mx-2 my-4">도움말</v-list-item-title>
        <v-icon @click="next"> mdi-chevron-right </v-icon>
      </v-list-item>
      <v-divider></v-divider>
      <v-list-item>
        <v-list-item-title class="mx-2 my-4">이용약관</v-list-item-title>
        <v-icon @click="next"> mdi-chevron-right </v-icon>
      </v-list-item>
    </v-list>
  </v-container>
</template>

<script>
import BackMenu from "@/components/layout/BackMenu.vue";

export default {
  components: { BackMenu },
  name: "S07P12A505SettingsNotice",

  data() {
    return {
      isRight: true,
    };
  },

  mounted() {},

  methods: {},
};
</script>

<style scoped></style>
