<template>
  <v-container>
    <v-row class="text-center">
      <v-col cols="12">
        그룹화면
        <group-item></group-item>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import GroupItem from '@/components/group/GroupItem.vue';

export default {
  components: { GroupItem },
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