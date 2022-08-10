<template>
  <v-container>
    <back-menu title="그룹 수정" class="mb-1"></back-menu>
    <v-row class="text-center">
      <v-col cols="12">
        <group-input groupType="modify"></group-input>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import BackMenu from "@/components/layout/BackMenu.vue";
import GroupInput from "@/components/group/GroupInput.vue";

export default {
  components: { BackMenu, GroupInput },
  name: "S07P12A505GroupModify",
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
