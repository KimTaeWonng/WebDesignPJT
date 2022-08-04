<template>
  <v-container>
    <v-row class="text-center">
      <v-col cols="12">
        그룹상세조회
				<group-info></group-info>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import GroupInfo from '@/components/group/GroupInfo.vue';

export default {
  components: { GroupInfo },
    name: "GroupDetail",
    data() {
        return {};
    },
    mounted() {
    },
    methods: {},

};
</script>

<style scoped>

</style>