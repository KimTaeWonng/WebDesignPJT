<template>
  <v-container>
    <group-search-bar></group-search-bar>
    <v-row class="text-center">
      <v-col cols="12">
        <group-item v-for="(group,i) in groups" :key="i" :detailGroup="group"></group-item>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import GroupItem from '@/components/group/GroupItem.vue';
import GroupSearchBar from '@/components/layout/GroupSearchBar.vue';
import { http } from "@/js/http.js";


export default {
  components: { GroupItem, GroupSearchBar },
    name: "GroupList",

    data() {
        return {
          groups: [],
        };
    },
    mounted() {
    },

    methods: {
      async getGroup() {
        const response = await http.get("/group");
        this.groups = response.data.reverse()
      },
    },

    created() {
    this.getGroup()
  },

};
</script>

<style scoped>

</style>