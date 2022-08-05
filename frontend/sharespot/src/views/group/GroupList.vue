<template>
  <v-container>
    <group-search-bar></group-search-bar>
    <v-row class="text-center">
      <v-col cols="12">
        <group-item></group-item>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import GroupItem from '@/components/group/GroupItem.vue';
import GroupSearchBar from '@/components/layout/GroupSearchBar.vue';


export default {
  components: { GroupItem, GroupSearchBar },
    name: "GroupList",

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