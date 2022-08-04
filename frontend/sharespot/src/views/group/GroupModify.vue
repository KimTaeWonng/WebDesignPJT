<template>
  <v-container>
    <v-row class="text-center">
      <v-col cols="12">
        <back-menu title="그룹 수정" class="mb-1" :isRight="isRight" type="modify"></back-menu>
				<group-input></group-input>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import BackMenu from '@/components/layout/BackMenu.vue';
import GroupInput from '@/components/group/GroupInput.vue';

export default {
    name: "GroupModify",
    data() {
        return {
          isRight: true,
        };
    },
    mounted() {
    },
    methods: {},
    components: { BackMenu, GroupInput }
};
</script>

<style scoped>

</style>