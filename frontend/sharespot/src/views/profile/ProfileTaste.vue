<template>
  <v-container>
    <back-menu title="취향분석" class="mb-1"></back-menu>
    <!-- 지도 start -->
    <div class="text-center mt-5 mb-5" style="margin: 0">
      <!-- <v-icon>mdi-map-search-outline</v-icon> -->
      <strong> {{ currentUser.nickname }}</strong> 님의
      <span style="color: #289672; font-weight: bold">지도 </span>
    </div>
    <div
      class="root_daum_roughmap root_daum_roughmap_landing"
      align="center"
      style="margin: 0"
      id="map"
    ></div>
    <div class="mb-5"></div>
    <!-- 지도 end -->
    <v-divider></v-divider>
    <!-- 차트 start -->
    <div class="text-center mt-5 mb-5" style="margin: 0">
      <strong>{{ currentUser.nickname }}</strong> 님의
      <span style="color: #289672; font-weight: bold">차트</span>
      <div>
        <div>
          <canvas id="tasteChart" width="300"></canvas>
        </div>
        <div class="text-center mt-8">
          <div
            v-if="
              this.postType.eatCnt - 1 == 0 || this.postType.eatCnt - 1 == -1
            "
          >
            <span style="color: #289672; font-size: 20px; font-weight: bold"
              >맛집</span
            >
            게시글이 아직 없어요.🥲
          </div>
          <div v-else>
            <span style="color: #289672; font-size: 20px; font-weight: bold"
              >맛집</span
            >
            게시글은 총
            <span style="font-size: 20px; font-weight: bold">{{
              this.postType.eatCnt - 1
            }}</span>
            개 입니다.
          </div>
          <div
            v-if="
              this.postType.cafeCnt - 1 == 0 || this.postType.cafeCnt - 1 == -1
            "
          >
            <span style="color: #289672; font-size: 20px; font-weight: bold"
              >카페</span
            >
            게시글이 아직 없어요.🥲
          </div>
          <div v-else>
            <span style="color: #289672; font-size: 20px; font-weight: bold"
              >카페</span
            >
            게시글은 총
            <span style="font-size: 20px; font-weight: bold">{{
              this.postType.cafeCnt - 1
            }}</span>
            개 입니다.
          </div>
          <div
            v-if="
              this.postType.lifeCnt - 1 == 0 || this.postType.lifeCnt - 1 == -1
            "
          >
            <strong style="color: #289672; font-size: 20px; font-weight: bold"
              >생활</strong
            >
            게시글이 아직 없어요.🥲
          </div>
          <div v-else>
            <span style="color: #289672; font-size: 20px; font-weight: bold"
              >생활</span
            >
            게시글은 총
            <span style="font-size: 20px; font-weight: bold">{{
              this.postType.lifeCnt - 1
            }}</span>
            개 입니다.
          </div>
          <div
            v-if="
              this.postType.cultureCnt - 1 == 0 ||
              this.postType.cultureCnt - 1 == -1
            "
          >
            <span style="color: #289672; font-size: 20px; font-weight: bold"
              >문화</span
            >
            게시글이 아직 없어요.🥲
          </div>
          <div v-else>
            <span style="color: #289672; font-size: 20px; font-weight: bold"
              >문화</span
            >
            게시글은 총
            <span style="font-size: 20px; font-weight: bold">{{
              this.postType.cultureCnt - 1
            }}</span>
            개 입니다.
          </div>
          <div
            v-if="
              this.postType.tripCnt - 1 == 0 || this.postType.tripCnt - 1 == -1
            "
          >
            <span style="color: #289672; font-size: 20px; font-weight: bold"
              >여행</span
            >
            게시글이 아직 없어요.🥲
          </div>
          <div v-else>
            <span style="color: #289672; font-size: 20px; font-weight: bold"
              >여행</span
            >
            게시글은 총
            <span style="font-size: 20px; font-weight: bold">{{
              this.postType.tripCnt - 1
            }}</span>
            개 입니다.
          </div>
        </div>
      </div>
    </div>
    <!-- 차트 end -->
    <v-divider></v-divider>
    <!-- 타임라인 start -->
    <div class="text-center mt-5 mb-5" style="margin: 0">
      <strong>{{ currentUser.nickname }}</strong> 님의
      <span style="color: #289672; font-weight: bold">타임라인</span>

      <div>타임라인</div>
    </div>
    <!-- 타임라인 end -->
    <v-divider></v-divider>
    <!-- 함께한 시간 start-->
    <div class="text-center mt-5 mb-5" style="margin: 0">
      <strong>셰어스팟과 함께한 시간</strong>
      <div>
        <span style="color: #289672; font-size: 20px; font-weight: bold"
          >{{ currentUser.registTime }} 시간
        </span>
      </div>
    </div>
    <!-- 함께한 시간 end -->
  </v-container>
</template>

<script>
// /profile/taste/:userid
import BackMenu from "@/components/layout/BackMenu.vue";
import { http } from "@/js/http.js";
import { Chart, registerables } from "chart.js";
Chart.register(...registerables);

// 지도 키
const MAP_API_KEY = process.env.VUE_APP_KAKAOMAP_KEY;

export default {
  components: { BackMenu },
  name: "ProfileTaste",

  data() {
    return {
      page: "map",
      tabs: null,
      showMap: true,
      tasteChart: null,
      currentUser: {
        userid: "",
        nickname: "",
        registTime: "",
      },
      postType: {
        tripCnt: 1, // 여행 게시글 수
        lifeCnt: 1, // 생활 게시글 수
        cafeCnt: 1, // 카페 게시글 수
        cultureCnt: 1, // 문화 게시글 수
        eatCnt: 1, // 맛집 게시글 수
        maxCnt: 0, // 최대 게시글 수
      },
      posts: [],
    };
  },

  async created() {
    this.currentUser.userid = this.$route.params.userid;
    // 현재 유저의 닉네임을 가져온다.
    const response1 = await http.get(
      `/users/info/user/${this.currentUser.userid}`
    );

    this.currentUser.registTime = new Date(response1.data[0][6]);
    var today = new Date();
    this.currentUser.registTime = parseInt(
      (today.getTime() - this.currentUser.registTime.getTime()) / (1000 * 3600)
    );

    this.currentUser.nickname = response1.data[0][1];

    // 현재 유저의 게시글을 가져온다.
    const response2 = await http.get(
      `/main/posts/user/${this.currentUser.userid}`
    );
    this.posts = response2.data;
    for (var i = 0; i < response2.data.length; i++) {
      let type = response2.data[i][1];

      if (type == "여행") {
        this.postType.tripCnt++;
      } else if (type == "생활") {
        this.postType.tripCnt++;
      } else if (type == "맛집") {
        this.postType.eatCnt++;
      } else if (type == "카페") {
        this.postType.cafeCnt++;
      } else if (type == "문화") {
        this.postType.cultureCnt++;
      }
    }

    // 게시글 종류 중 가장 많은 게시글 수를 찾는다.
    this.postType.maxCnt += Math.max(
      this.postType.tripCnt,
      this.postType.tripCnt,
      this.postType.eatCnt,
      this.postType.cafeCnt,
      this.postType.cultureCnt
    );

    if (this.postType.maxCnt == 1) {
      this.postType.maxCnt = 5;
      this.postType.tripCnt = 0;
      this.postType.lifeCnt = 0;
      this.postType.eatCnt = 0;
      this.postType.cafeCnt = 0;
      this.postType.cultureCnt = 0;
    }

    // 얻어온 데이터를 기준으로 차트 생성
    this.createChart();
  },

  computed: {},

  mounted() {
    window.kakao && window.kakao.maps ? this.initMap() : this.addMapScript();
  },

  methods: {
    addMapScript() {
      const script = document.createElement("script");
      /* global kakao */
      script.addEventListener("load", () => {
        kakao.maps.load(this.initMap);
      });
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=" +
        MAP_API_KEY;
      document.head.appendChild(script);
    },
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.5665734, 126.978179), // 변경: 접속한 유저의 위도경도로 바꿔보자
        level: 3,
        maxLevel: 13, // 지도 레벨
      };
      this.map = new kakao.maps.Map(container, options); //지도 생성
    },
    createChart() {
      const ctx = document.getElementById("tasteChart");
      this.tasteChart = new Chart(ctx, {
        // chart type
        type: "radar",
        // chart data
        data: {
          labels: ["맛집", "카페", "문화", "여행", "생활"],
          datasets: [
            // 차트 내용 데이터셋
            {
              label: "대분류",
              borderWidth: 1,
              data: [
                this.postType.eatCnt,
                this.postType.cafeCnt,
                this.postType.cultureCnt,
                this.postType.tripCnt,
                this.postType.lifeCnt,
              ],
              fill: true,
              //rgb(40, 150, 113, 0.3)
              backgroundColor: "rgb(40, 150, 113, 0.3)",
              borderColor: "#289672",
              pointRadius: 0,
            },
          ],
        },
        // chart options
        options: {
          plugins: {
            legend: {
              display: false,
            },
          },
          scales: {
            r: {
              ticks: {
                maxTicksLimit: 5,
                display: false,
              },
              angleLines: {
                display: false,
              },
              min: 0,
              max: this.postType.maxCnt,
              pointLabels: {
                font: {
                  size: 15,
                  weight: "600",
                },
              },
            },
          },
        },
      });
    },
  },
};
</script>

<style scoped>
#map {
  width: 300px;
  height: 500px;
}
.root_daum_roughmap {
  margin: auto !important;
}
</style>
