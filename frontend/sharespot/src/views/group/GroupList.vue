<template>
  <v-container>
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
          v-model="searchContent"
          @keyup="search(searchContent)"
          @click="searchModal = true"
        ></v-text-field>
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
      </v-list-group>
    </v-list>
  </v-card>
  </v-dialog>
  </v-col>
      </v-row>
    
      <v-row>
        <v-dialog v-model="searchModal" fullscreen hide-overlay transition="dialog-bottom-transition">
        <v-card>
          <div>
            <v-row class="text-center d-flex" align="center" style="margin: 3px">
              <!-- 버튼 -->
              <v-col cols="2" @click="searchModal = false">
                <v-icon>mdi-chevron-left</v-icon>
              </v-col>
              <!--검색창-->  
              <v-col cols="8" style="padding: 0; margin: 0">
                <v-text-field
                  placeholder="검색어를 입력하세요"
                  clearable
                  color="#289672"
                  v-model="searchContent"
                  @keyup="search(searchContent)"
                ></v-text-field>
              </v-col>
              <!--돋보기 버튼-->  
              <v-col cols="1" class="ml-4" style="padding: 0" @click="search(searchContent)">
                <v-icon>mdi-magnify</v-icon>
              </v-col>
            </v-row>
          </div>
       <v-row class="text-center">
      <v-col cols="12">
        <group-item v-for="result in searchResultList" :key="result.group_id" :detailGroup="result"></group-item>
      </v-col>
      </v-row>
        <!-- <v-list
          v-for="group in searchResultList"
          :key="group[0]"
        >
        <v-card class="my-2 mx-4">
          <v-list-item three-line>
            <v-list-item-content style="font-weight:bold; font-size:4vw;" @click="goGroup(group[0])">
            <v-list-item-title style="font-weight:bold; font-size:5vw; margin-left: 5%;" v-text="group[1]"></v-list-item-title>
                <v-row style="margin-top: 3%; margin-left: 1%;">
                <v-chip
                    style="
                      margin-left: 3%;
                      font-weight: bold;
                      font-size: 3vw;
                      height: 5vw;
                    "
                    >{{ group[4]}}년생부터 {{ group[3] }}년생까지</v-chip>
            </v-row>
            </v-list-item-content>
          </v-list-item>
        </v-card>
          </v-list> -->
        </v-card>

        </v-dialog>
    </v-row>




      

  </div>

<!-- 아래 그룹 -->

    <v-row class="text-center">
      <v-col cols="12">
        <group-item v-for="(group,i) in groups" :key="i" :detailGroup="group"></group-item>
      </v-col>



    </v-row>
  </v-container>
</template>

<script>
import GroupItem from '@/components/group/GroupItem.vue';

import { http } from "@/js/http.js";
import { mapState, mapActions } from "vuex";

const userLogStore = "userLogStore";
const userStore = "userStore";

export default {
  components: { GroupItem },
    name: "GroupList",

    data() {
        return {
      groups: [],
      searchContent: "",
      dialog: false,
      searchModal: false,
      items: [],
      searchResultList: [],
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
        goBack() {
      this.$router.go(-1);
    },

    // searchContent로 검색하는 함수
    async search(searchContent) {
      console.log('검색어',searchContent);
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

  async created() {

    const groupList = [];
    for (var i = 0; i < this.myGroupList.length; i++) {
      const temp = {
        group_name: this.myGroupList[i].group_name,
        group_id: this.myGroupList[i].group_id
      };
      groupList.push(temp);
    }

    console.log(groupList)

    this.items = groupList

    await this.setGroupList(this.userInfo.user_id);
    console.log('내 그룹',this.myGroupList)
      


    this.getGroup()
  },

};
</script>

<style scoped>

</style>