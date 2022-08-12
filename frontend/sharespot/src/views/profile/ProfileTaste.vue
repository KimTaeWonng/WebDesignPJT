<template>
  <v-container>
    <back-menu title="취향분석" class="mb-1"></back-menu>
    <div class="text-center">
      <strong>{{ currentUser.nickname }}</strong> 님의
      <span style="color: #289672; font-weight: bold">지도</span>
    </div>
    <div>
      <div class="text-center">
        <strong>{{ currentUser.nickname }}</strong> 님의
        <span style="color: #289672; font-weight: bold">취향분석</span>
      </div>
      <div>
        <canvas id="tasteChart" width="300"></canvas>
      </div>
      <div class="text-center mt-8">
        <div v-if="this.postType.eatCnt - 1 == 0">
          <span>맛집</span> 게시글이 아직 없어요.🥲
        </div>
        <div v-else>
          <span style="color: #289672; font-size: 20px; font-weight: bold"
            >맛집</span
          >
          게시글은 총 <span>{{ this.postType.eatCnt - 1 }}</span> 개 입니다.
        </div>
        <div v-if="this.postType.cafeCnt - 1 == 0">
          <span>카페</span> 게시글이 아직 없어요.🥲
        </div>
        <div v-else>
          <span span style="color: #289672; font-size: 20px; font-weight: bold"
            >카페</span
          >
          게시글은 총 <span>{{ this.postType.cafeCnt - 1 }}</span> 개 입니다.
        </div>
        <div v-if="this.postType.lifeCnt - 1 == 0">
          <span span style="color: #289672; font-size: 20px; font-weight: bold"
            >생활</span
          >
          게시글이 아직 없어요.🥲
        </div>
        <div v-else>
          <strong>생활</strong> 게시글은 총
          <strong>{{ this.postType.lifeCnt - 1 }}</strong> 개 입니다.
        </div>
        <div v-if="this.postType.cultureCnt - 1 == 0">
          <strong
            span
            style="color: #289672; font-size: 20px; font-weight: bold"
            >문화</strong
          >
          게시글이 아직 없어요.🥲
        </div>
        <div v-else>
          <strong>문화</strong> 게시글은 총
          <strong>{{ this.postType.cultureCnt - 1 }}</strong> 개 입니다.
        </div>
        <div v-if="this.postType.tripCnt - 1 == 0">
          <strong>여행</strong> 게시글이 아직 없어요.🥲
        </div>
        <div v-else>
          <strong
            span
            style="color: #289672; font-size: 20px; font-weight: bold"
            >여행</strong
          >
          게시글은 총
          <strong style="font-size: 20px">{{
            this.postType.tripCnt - 1
          }}</strong>
          개 입니다.
        </div>
      </div>
    </div>
  </v-container>
</template>

<script>
// /profile/taste/:userid
import BackMenu from "@/components/layout/BackMenu.vue";
import { http } from "@/js/http.js";
import { Chart, registerables } from "chart.js";
Chart.register(...registerables);

export default {
  components: { BackMenu },
  name: "ProfileTaste",

  data() {
    return {
      tasteChart: null,
      currentUser: {
        userid: "",
        nickname: "",
      },
      postType: {
        tripCnt: 1, // 여행 게시글 수
        lifeCnt: 1, // 생활 게시글 수
        cafeCnt: 1, // 카페 게시글 수
        cultureCnt: 1, // 문화 게시글 수
        eatCnt: 1, // 맛집 게시글 수
        maxCnt: 0, // 최대 게시글 수
      },
    };
  },

  async created() {
    this.currentUser.userid = this.$route.params.userid;
    // 현재 유저의 닉네임을 가져온다.
    const response = await http.get(
      `/users/info/user/${this.currentUser.userid}`
    );
    this.currentUser.nickname = response.data[0][1];

    // 현재 유저의 게시글을 가져온다.
    const posts = await http.get(`/main/posts/user/${this.currentUser.userid}`);

    for (var i = 0; i < posts.data.length; i++) {
      let type = posts.data[i][1];

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

    // 얻어온 데이터를 기준으로 차트 생성
    this.createChart();
    console.log(
      this.postType.eatCnt +
        " " +
        this.postType.cafeCnt +
        " " +
        this.postType.cultureCnt +
        " " +
        this.postType.tripCnt +
        " " +
        this.postType.lifeCnt
    );
  },

  computed: {},

  methods: {
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
            // {
            //   label: "배경색",
            //   borderWidth: 1,
            //   data: [
            //     this.postType.maxCnt,
            //     this.postType.maxCnt,
            //     this.postType.maxCnt,
            //     this.postType.maxCnt,
            //     this.postType.maxCnt,
            //   ],
            //   fill: true,
            //   //rgb(40, 150, 113, 0.3)
            //   backgroundColor: "rgba(217,217,217,0.3)",
            //   borderColor: "rgba(217,217,217)",
            //   pointRadius: 0,
            // },
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
#big {
  color: " #289672";
  font-size: " 20px";
  font-weight: "bold";
}
</style>
