<template>
  <v-container>
    <v-row class="text-center">
      <v-col cols="12">
        그룹화면
        <GroupItem></GroupItem>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import GroupItem from './GroupItem.vue';


export default {
    name: "GroupList",
    componentes: { GroupItem },
    data() {
        return {};
    },
    mounted() {
    },
    methods: {},
    components: { GroupItem }
};
</script>

<style scoped>

</style>