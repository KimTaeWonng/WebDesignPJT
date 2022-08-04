<template>
  <v-container>
    <v-row class="text-center">
      <v-col cols="12">
        그룹상세조회
				<GroupInfo></GroupInfo>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import GroupInfo from './GroupInfo.vue';

export default {
    name: "GroupDetail",
    data() {
        return {};
    },
    mounted() {
    },
    methods: {},
    components: { GroupInfo }
};
</script>

<style scoped>

</style>