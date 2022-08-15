<template>
    <div>
        <v-row class="text-center d-flex" align="center" style="margin: 3px">
            <!-- < 버튼 -->
            <v-col cols="2" @click="dialog = true">
            <v-icon>mdi-chevron-left</v-icon>
            </v-col>
            <!-- 검색창 -->
            <v-col cols="8" style="padding: 0; margin: 0">
            <v-text-field
                clearable
                color="#289672"
                :value="this.searchWord"
                @click="dialog = true"
            ></v-text-field>
            </v-col>
            <!-- 돋보기 버튼 -->
            <!-- <v-col cols="1" class="ml-4" style="padding: 0" @click="search(searchContent)">
            <v-icon>mdi-magnify</v-icon>
            </v-col> -->
        </v-row>
    <v-row>
        <search-item style="margin-left: 10px;" v-for="user in this.searchResultList" :key="`${user.user_id}`" :userDetail="user"></search-item>
    </v-row>
        <!-- 여기부터 모달 -->
      <v-row justify="center">
      <v-dialog v-model="dialog" fullscreen hide-overlay transition="dialog-bottom-transition">
        <!-- 모달 content start -->
          <!-- searchBar -->
          <div>
            <v-row class="text-center d-flex" align="center" style="margin: 3px">
              <!-- < 버튼 -->
              <v-col cols="2" @click="dialog = false">
                <v-icon>mdi-chevron-left</v-icon>
              </v-col>
              <!-- 검색창 -->
              <v-col cols="8" style="padding: 0; margin: 0">
                <v-text-field
                  placeholder="검색어를 입력하세요"
                  clearable
                  color="#289672"
                  v-model="searchContent"
                  @keyup="search(searchContent)"
                ></v-text-field>
              </v-col>
              <!-- 돋보기 버튼 -->
              <v-col cols="1" class="ml-4" style="padding: 0" @click="search(searchContent)">
                <v-icon>mdi-magnify</v-icon>
              </v-col>
            </v-row>
          </div>
          <v-row
            ><!-- 변경: 최근 검색 v-if 걸어서 최근 검색 있는 경우 보여주기 -->
            <v-list three-line subheader>
              <v-row v-if="searchContent == null">
              <v-subheader  class="ml-4">최근 검색</v-subheader>
              <search-item style="margin-left: 10px;" v-for="user1 in this.searchWordList" :key="`${user1.user_id}`" :userDetail="user1"></search-item>
              <!-- 유저 프로필 + 닉네임 + 소개 -->


              
              </v-row>
            </v-list>
        </v-row>
        
    </v-dialog>
    </v-row>
    </div>
</template>

<script>
import SearchItem from '../../components/profile/SearchItem.vue';
import { http } from "@/js/http.js";
import { mapState, mapActions } from "vuex";
const userLogStore = "userLogStore";

export default {
  components: { SearchItem },
    name: 'UserSearchResult',

    data() {
        return {
      dialog: false,
      notifications: false,
      sound: true,
      widgets: false,
      searchContent: " ",
      searchResultList: [],

        };
    },

    mounted() {
        
    },
    computed: {
    
    ...mapState(userLogStore, ["searchWordList", "searchWord"]),

  },
    async created() {
      console.log(this.searchWord)
      const searchWord = this.searchWord
      console.log(searchWord);
      const result = await http.get(`/main/search-user/${searchWord}`);
      console.log('검색결과', result.data)
      this.searchResultList = result.data
    },
    methods: {
    ...mapActions(userLogStore, ["saveSearchWord"]),
    // searchContent로 검색하는 함수
    changeRouter(pageName) {
      console.log("click");
      this.saveSearchWord(this.searchContent) 
      this.dialog = false;
      if (this.$route.path != pageName) {
        this.$router.push({
          name: "userSearchResult",
          params: {word: this.searchContent}
        })
      }
    },
    // searchContent로 검색하는 함수
    async search(searchContent) {
      console.log(searchContent);
      const result = await http.get(`/main/search-user/${searchContent}`);
      console.log('검색결과', result.data)
      this.searchResultList = result.data.slice(0, 3)
    },

},
};
</script>

<style scoped>

</style>