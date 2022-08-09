<template>
    <v-container>
        <!-- 상단 바 -->
        <v-row class="text-center d-flex" align="center" style="margin: 3px">
      <v-col cols="3"></v-col>
      <!-- 로고 -->
      <v-col cols="6">
        <span style="color: #289672; font-weight: bold">Share Spot</span>
      </v-col>
      <v-col cols="3"></v-col>
    </v-row>

    <hot-spot-menu></hot-spot-menu>

    <curation-post-list></curation-post-list>

    

    </v-container>
</template>

<script>
import HotSpotMenu from '@/components/curation/HotSpotMenu.vue';
import CurationPostList from '@/components/curation/CurationPostList.vue';

export default {
  components: {HotSpotMenu , CurationPostList},
    name: 'S07P12A505CurationList',

    data() {
        return {
        
        };
    },

    mounted() {
        
    },

    methods: {
        
    },
};
</script>

<style scoped>

</style>