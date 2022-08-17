<template>
    <v-container>
      <back-menu :title="this.group.group_name" :isRight="true" type="groupDetail" class="mb-1"></back-menu>
      <v-row class="text-center">
        <v-col cols="12">
          <group-info :detailGroup="group">
          </group-info>
          <meeting-list-item v-for="(meeting,i) in meetings" :key="i" :detailMeeting="meeting"></meeting-list-item>
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
  components: { BackMenu, GroupInfo, MeetingListItem},

    name: 'S07P12A505GroupDetail',
    
    data() {
        return {
            isRight: true,
            group: {},
            meetings: {},
            no: 0,
        };
    },


    mounted() {
    },

    methods: {
      async getGroup() {
        const response = await http.get(`/group/${this.no}`);
        this.group = response.data
      

        // console.log(this.group)
        // console.log(typeof(this.group))
      },
      
      async getMeeting() {
        const response = await http.get(`/group/${this.no}/meetings`);
        this.meetings = response.data
        // console.log(this.meetings)
      },
    },

    created() {
    this.no = Number(this.$route.params.groupno)
    console.log(this.no)
    this.getGroup()
    this.getMeeting()
  },


};
</script>

<style scoped>

</style>