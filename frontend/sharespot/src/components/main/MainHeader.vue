<template>
  <div>
    <v-row class="text-center d-flex" align="center" style="margin: 3px">
      <!-- 알림버튼 -->
      <v-col cols="3" @click="changeRouter('notice')">
        <v-icon>mdi-bell-outline</v-icon>
      </v-col>
      <!-- 로고 -->
      <v-col cols="6">
        <span style="color: #289672">Share Spot</span>
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
          <div>
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
          </div>
          <v-row
            ><!-- 변경: 최근 검색 v-if 걸어서 최근 검색 있는 경우 보여주기 -->
            <v-list three-line subheader>
              <v-subheader class="ml-4">최근 검색</v-subheader>
              <!-- 유저 프로필 + 닉네임 + 소개 -->
              <v-list-item @click="changeRouter()">
                <v-row style="padding: 10px">
                  <v-col cols="3" align="center">
                    <v-icon size="60">mdi-face-woman-profile</v-icon>
                  </v-col>
                  <v-col cols="9">
                    <v-list-item-content>
                      <v-list-item-title>User Nick Name</v-list-item-title>
                      <v-list-item-subtitle>한줄소개</v-list-item-subtitle>
                    </v-list-item-content>
                  </v-col>
                </v-row>
              </v-list-item>
              <v-list-item @click="changeRouter()">
                <v-row style="padding: 10px">
                  <v-col cols="3" align="center">
                    <v-icon size="60">mdi-face-man-profile</v-icon>
                  </v-col>
                  <v-col cols="9">
                    <v-list-item-content>
                      <v-list-item-title>User Nick Name</v-list-item-title>
                      <v-list-item-subtitle>한줄소개</v-list-item-subtitle>
                    </v-list-item-content>
                  </v-col>
                </v-row>
              </v-list-item>
            </v-list>
          </v-row>
          <v-row>
            <!-- 최근 검색 없는 경우 -->
            <v-subheader class="ml-4">최근 검색 내역이 없습니다.</v-subheader>
          </v-row>
          <!-- 변경: 검색 결과 내역 -->
          <v-row id="searchResult">
            <v-list three-line subheader>
              <v-list-item @click="changeRouter()">
                <v-row style="padding: 10px">
                  <v-col cols="3" align="center">
                    <v-icon size="60">mdi-face-woman-profile</v-icon>
                  </v-col>
                  <v-col cols="9">
                    <v-list-item-content>
                      <v-list-item-title>{{ searchContent }}</v-list-item-title>
                      <v-list-item-subtitle>한줄소개</v-list-item-subtitle>
                    </v-list-item-content>
                  </v-col>
                </v-row>
              </v-list-item>
              <v-list-item @click="changeRouter()">
                <v-row style="padding: 10px">
                  <v-col cols="3" align="center">
                    <v-icon size="60">mdi-face-man-profile</v-icon>
                  </v-col>
                  <v-col cols="9">
                    <v-list-item-content>
                      <v-list-item-title>User Nick Name</v-list-item-title>
                      <v-list-item-subtitle>한줄소개</v-list-item-subtitle>
                    </v-list-item-content>
                  </v-col>
                </v-row>
              </v-list-item>
            </v-list>
          </v-row>
          <p
            class="mt-1"
            align="center"
            style="font-size: 12px; color: #289672"
            @click="changeRouter('userSearchResult')"
          >
            결과 모두 보기
          </p>
        </v-card>
        <!-- 모달 content end -->
      </v-dialog>
    </v-row>
  </div>
</template>

<script>
export default {
  components: {},
  name: "MainHeader",

  data() {
    return {
      dialog: true,
      notifications: false,
      sound: true,
      widgets: false,
      searchContent: " ",
    };
  },

  mounted() {},

  methods: {
    changeRouter(pageName) {
      console.log("click");
      this.dialog = false;
      if (this.$route.path != pageName) {
        this.$router.push({ name: pageName });
      }
    },
    // searchContent로 검색하는 함수
    search(searchContent) {
      console.log(searchContent);
    },
  },
};
</script>

<style scoped>
#searchResult {
  z-index: 2;
}
</style>
