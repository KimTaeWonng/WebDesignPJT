<template>
    <v-container>
      <back-menu title="보드게임조아조아" :isRight="true" type="groupDetail" class="mb-1"></back-menu>
      <v-row class="text-center">
        <v-col cols="12">
          <group-info :no="no"></group-info>
          <meeting-list-item v-for="(meeting,i) in meetings" :key="i" :detailGroup="meeting"></meeting-list-item>
        </v-col>
      </v-row>
    </v-container>
</template>

<script>
import BackMenu from "@/components/layout/BackMenu.vue";
import GroupInfo from '@/components/group/GroupInfo.vue';
import MeetingListItem from '@/components/group/MeetingListItem.vue';
import { http } from "@/js/http.js";

export default {
    components: { BackMenu, GroupInfo, MeetingListItem },
    name: 'S07P12A505GroupDetail',
    
    data() {
        return {
            isRight: true,
            groups: [],
            meetings: [],
            no: 0,
        };
    },

    mounted() {
    },

    methods: {
      // async getGroup() {
      //   const response = await http.get(`/group/${this.group}`);
      //   this.groups = response.data
      // },
      
      async getMeeting() {
        const response = await http.get(`/group/${this.meeting.groupId}/meetings`);
        this.meetings = response.data
      },
    },

    created() {
    this.getMeeting()
    this.no = this.$route.params.groupno
  },


};
</script>

<style scoped>

</style>