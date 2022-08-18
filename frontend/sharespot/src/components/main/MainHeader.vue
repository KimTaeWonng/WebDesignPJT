<template>
  <div>
    <v-row class="text-center d-flex" align="center" style="margin: 3px">
      <!-- 알림버튼 -->
      <v-col cols="3" @click="changeRouter('notice')">
        <v-icon>mdi-bell-outline</v-icon>
      </v-col>
      <!-- 로고 -->
      <v-col cols="6">
        <span style="color: #289672; font-weight: bold; font-size: 5vw"
          >Share Spot</span
        >
      </v-col>
      <!-- 돋보기/설정 버튼 -->
      <v-col cols="3">
        <v-row>
          <v-col cols="6" @click="dialog = true">
            <v-icon>mdi-magnify</v-icon>
          </v-col>
          <v-col cols="6" @click="changeRouter('settings')">
            <v-icon>mdi-cog-outline</v-icon>
          </v-col>
        </v-row>
      </v-col>
    </v-row>
    <!-- 돋보기 클릭시 나오는 풀스크린 모달(유저 검색)-->
    <v-row justify="center">
      <v-dialog
        v-model="dialog"
        fullscreen
        hide-overlay
        transition="dialog-bottom-transition"
      >
        <!-- 모달 content start -->
        <v-card>
          <!-- searchBar -->
            <v-row
              class="text-center d-flex"
              align="center"
              style="margin: 3px"
            >
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
              <v-col
                cols="1"
                class="ml-4"
                style="padding: 0"
                @click="search(searchContent)"
              >
                <v-icon>mdi-magnify</v-icon>
              </v-col>
            </v-row>
          <v-row style="margin-left: 3%;">
            <!-- 변경: 최근 검색 v-if 걸어서 최근 검색 있는 경우 보여주기 -->
            <v-list subheader>
              <v-row v-if="searchContent == ''">
                <v-subheader class="ml-4">최근 검색</v-subheader>
                <search-item
                  style="margin-left: 10px; margin-bottom: 5%;" 
                  v-for="user1 in this.searchWordList"
                  :key="`${user1.user_id}`"
                  :userDetail="user1"
                >
                </search-item>

                <!-- 유저 프로필 + 닉네임 + 소개 -->
              </v-row>
            </v-list>
          </v-row>
          <v-row>
            <!-- 최근 검색 없는 경우 -->

            <v-subheader
              v-if="searchContent == null && this.searchWordList.length == 0"
              class="ml-4"
              >최근 검색 내역이 없습니다.</v-subheader
            >
          </v-row>
          <!-- 변경: 검색 결과 내역 -->
          <v-row v-if="searchContent != null" id="searchResult">
            <search-item
              style="margin-left: 10px"
              v-for="user2 in searchResultList"
              :key="`${user2.user_id}`"
              :userDetail="user2"
            ></search-item>
          </v-row>
          <V-col
            v-if="this.searchResultList.length != 0"
            class="mt-1"
            align="center"
            style="font-size: 12px; color: #289672"
            @click="goSearch('userSearchResult')"
          >
            결과 모두 보기
          </V-col>
        </v-card>
        <!-- 모달 content end -->
      </v-dialog>
    </v-row>
  </div>
</template>

<script>
import { http } from "@/js/http.js";
import SearchItem from "../profile/SearchItem.vue";
import { mapState, mapActions } from "vuex";
const userLogStore = "userLogStore";

export default {
  components: { SearchItem },
  name: "MainHeader",

  data() {
    return {
      dialog: false,
      notifications: false,
      sound: true,
      widgets: false,
      searchContent: "",
      searchResultList: [],
    };
  },

  mounted() {},
  async created() {
    this.userItem = this.userDetail;
    // console.log('최근검색', this.searchWordList)
  },
  computed: {
    ...mapState(userLogStore, ["searchWordList", "searchWord"]),
  },
  methods: {
    ...mapActions(userLogStore, ["saveSearchWord"]),

    goSearch(pageName) {
      this.saveSearchWord(this.searchContent);
      this.dialog = false;
      if (this.$route.path != pageName) {
        this.$router.push({
          name: "userSearchResult",
          params: { word: this.searchContent },
        });
      }
    },

    // searchContent로 검색하는 함수
    async search(searchContent) {
      const result = await http.get(`/main/search-user/${searchContent}`);
      // console.log('검색결과', result.data)
      this.searchResultList = result.data.slice(0, 3);
    },
    changeRouter(pageName) {
      // console.log("click");
      this.dialog = false;
      if (this.$route.path != pageName) {
        this.$router.push({ name: pageName });
      }
    },
  },
};
</script>

<style scoped>
#searchResult {
  z-index: 2;
}
</style>
