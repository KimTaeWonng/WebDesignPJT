<template>
    <v-container>
      <back-menu title="보드게임조아조아" :isRight="true" type="groupDetail" class="mb-1"></back-menu>
      <v-row class="text-center">
        <v-col cols="12">
          <group-info v-for="(group,i) in groups" :key="i" :detailGroup="group"></group-info>
        </v-col>
      </v-row>
    </v-container>
</template>

<script>
import BackMenu from "@/components/layout/BackMenu.vue";
import GroupInfo from '@/components/group/GroupInfo.vue';
import { http } from "@/js/http.js";

export default {
    components: { BackMenu, GroupInfo },
    name: 'S07P12A505GroupDetail',
    
    data() {
        return {
            isRight: true,
            groups: [],
        };
    },
    mounted() {
    },
    methods: {
      async getGroup() {
        const response = await http.get("/group");
        this.groups = response.data
      },
    },

    created() {
    this.getGroup()
  },

};
</script>

<style scoped>

</style>