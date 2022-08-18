<template>
  <div>
    <v-row class="text-center d-flex" align="center" style="margin: 3px">
      <!-- < 버튼 -->
      <v-col cols="2" @click="goHome()">
        <v-icon>mdi-chevron-left</v-icon>
      </v-col>
      <!-- 검색창 -->
      <v-col cols="8" style="padding: 0; margin: 0">
        <v-text-field
          clearable
          color="#289672"
          :value="this.searchWord"
        ></v-text-field>
      </v-col>
      <!-- 돋보기 버튼 -->
      <!-- <v-col cols="1" class="ml-4" style="padding: 0" @click="search(searchContent)">
            <v-icon>mdi-magnify</v-icon>
            </v-col> -->
    </v-row>
    <v-row>
      <search-item
        style="margin-left: 10px"
        v-for="user in this.searchResultList"
        :key="`${user.user_id}!`"
        :userDetail="user"
      ></search-item>
    </v-row>
  </div>
</template>

<script>
import SearchItem from "../../components/profile/SearchItem.vue";
import { http } from "@/js/http.js";
import { mapState, mapActions } from "vuex";
const userLogStore = "userLogStore";

export default {
  components: { SearchItem },
  name: "UserSearchResult",

  data() {
    return {
      searchResultList: [],
    };
  },

  mounted() {},
  computed: {
    ...mapState(userLogStore, ["searchWord"]),
  },
  async created() {
    // console.log(this.searchWord)
    const searchWord = this.searchWord;
    // console.log(searchWord);
    const result = await http.get(`/main/search-user/${searchWord}`);
    // console.log('검색결과', result.data)
    this.searchResultList = result.data;
  },
  methods: {
    ...mapActions(userLogStore, ["saveSearchWord"]),
    // searchContent로 검색하는 함수
    changeRouter(pageName) {
      // console.log("click");
      this.saveSearchWord(this.searchContent);
      this.dialog = false;
      if (this.$route.path != pageName) {
        this.$router.push({
          name: "userSearchResult",
          params: { word: this.searchContent },
        });
      }
    },
    goHome() {
      this.$router.push({ name: "mainList" });
    },
  },
};
</script>

<style scoped></style>
