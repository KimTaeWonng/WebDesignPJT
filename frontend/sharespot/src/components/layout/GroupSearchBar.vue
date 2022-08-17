<template>
  <div>
    <v-row class="text-center d-flex" align="center" style="margin: 3px">
      <!-- 검색창 -->
      <v-col cols="1"></v-col>
      <v-col cols="9" style="padding: 0">
        <v-text-field
          prepend-inner-icon="mdi-magnify"
          placeholder="검색어를 입력하세요"
          clearable
          color="#289672"
          @keyup.enter="search(searchContent)"
          @click="searchModal = true"
        ></v-text-field>

<v-dialog v-model="searchModal" fullscreen transition="dialog-bottom-transition">
      <v-list-group
        v-for="group in searchResultList"
        :key="group.group_id"
      >
          <v-list-item-content style="font-weight:bold; font-size:4vw;" @click="goGroup(group.group_id)">
            <v-list-item-title v-text="group.group_name"></v-list-item-title>
          </v-list-item-content>


        <!-- <v-list-item
          v-for="child in item.items"
          :key="child.title"
        > -->
          <!-- <v-list-item-content style="font-weight:bold; font-size:3vw;">
            <v-list-item-title v-text="child.title"></v-list-item-title>
          </v-list-item-content>
        </v-list-item> -->
      </v-list-group>
</v-dialog>  

      </v-col>
      <!-- MY 버튼-->
      <v-col cols="2" align="center" @click="showMyGroup()">
        <span style="color: #289672" @click.stop="dialog = true">MY</span>

        <v-dialog
        v-model="dialog"
        max-width="290"
      >
        <v-card
    class="mx-auto"
    max-width="500"
  >
    
    <br>
    <div style="font-weight:bold; font-size:5vw;" class="text-center">
      가입 모임 보기
    </div>

    <v-list>
      <v-list-group
        v-for="item in items"
        :key="item.group_id"
      >
        <template v-slot:activator>
          <v-list-item-content style="font-weight:bold; font-size:4vw;" @click="goGroup(item.group_id)">
            <v-list-item-title v-text="item.group_name"></v-list-item-title>
          </v-list-item-content>
        </template>

        <!-- <v-list-item
          v-for="child in item.items"
          :key="child.title"
        > -->
          <!-- <v-list-item-content style="font-weight:bold; font-size:3vw;">
            <v-list-item-title v-text="child.title"></v-list-item-title>
          </v-list-item-content>
        </v-list-item> -->
      </v-list-group>
    </v-list>
  </v-card>



    
      </v-dialog>

      </v-col>
    </v-row>
  </div>
</template>

<script>
const userLogStore = "userLogStore";

import { mapState } from "vuex";
import { http } from "@/js/http.js";


export default {
  name: "SearchBar",

  data() {
    return {
      searchContent: "",
      dialog: false,
      searchModal: false,
      items: [],
      searchResultList: [],
    };
  },
  async created() {
    
    const groupList = [];
    for (var i = 0; i < this.myGroupList.length; i++) {
      const temp = {
        group_name: this.myGroupList[i].group_name,
        group_id: this.myGroupList[i].group_id
      };
      groupList.push(temp);
    }
    await this.setGroupList(this.userInfo.user_id);
    console.log('내 그룹',this.myGroupList)
      


    this.getGroup()

    console.log(groupList)

    this.items = groupList
  },
  mounted() {},
  computed: {
    ...mapState(userLogStore, ["myGroupList"]),
    ...mapState(userStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(userLogStore, ["setGroupList"]),
      async getGroup() {
        const response = await http.get("/group");
        console.log('그룹가져오기', response)
        this.groups = response.data.reverse()
      },
    goBack() {
      this.$router.go(-1);
    },

    // searchContent로 검색하는 함수
    async search(searchContent) {
      console.log(searchContent);
      const result = await http.get(`/group/search/${searchContent}`);
      console.log('검색결과', result.data)
      this.searchResultList = result.data
    },

    // 내가 가입한 그룹 리스트를 보여주는 함수
    showMyGroup() {
      console.log("click");
    },
    goGroup(group_id) {
      this.$router.push({
        name: "groupDetail",
        params: { groupno: group_id},
      });
    },
  },
};
</script>

<style scoped></style>
