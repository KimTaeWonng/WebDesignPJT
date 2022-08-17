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
import { mapState, mapActions } from "vuex";

const userLogStore = "userLogStore";
const userStore = "userStore";

export default {
  components: { GroupItem, GroupSearchBar },
    name: "GroupList",

    data() {
        return {
          groups: [],
        };
  },
     computed: {
    ...mapState(userLogStore, [
      "myGroupList"
    ]),
    ...mapState(userStore, ["userInfo"]),
  },
    mounted() {
    },

  methods: {
      ...mapActions(userLogStore, ["setGroupList"]),
      async getGroup() {
        const response = await http.get("/group");
        console.log('그룹가져오기', response)
        this.groups = response.data.reverse()
      },
    },

  async created() {
    await this.setGroupList(this.userInfo.user_id);
    console.log('내 그룹',this.myGroupList)
      


    this.getGroup()
  },

};
</script>

<style scoped>

</style>