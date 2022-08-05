<template>
    <v-container>
      <back-menu title="보드게임조아조아" :isRight="true" type="groupDetail" class="mb-1"></back-menu>
      <v-row class="text-center">
        <v-col cols="12">
          <group-info></group-info>
        </v-col>
      </v-row>
    </v-container>
</template>

<script>
import BackMenu from "@/components/layout/BackMenu.vue";
import GroupInfo from '@/components/group/GroupInfo.vue';

export default {
    components: { BackMenu, GroupInfo },
    name: 'S07P12A505GroupDetail',
    
    data() {
        return {
            isRight: true,
        };
    },
    mounted() {
    },
    methods: {},

};
</script>

<style scoped>

</style>