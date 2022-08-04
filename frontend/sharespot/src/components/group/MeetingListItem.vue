<template>
  <v-container>
    <v-row class="text-center">
      <v-col cols="12">
        <v-card style="text-align:left; font-size:3vw;">
          <div style="margin-left:3%;">
            <div>
              <br>
              <!-- 모임 이름으로 대체 -->
              <p style="font-weight:bold; float:left;">불금 보드게임은 국룰이죠 (4명)</p>
              <p align="right" style="margin-right:3%;">
                <!-- 참여자 프로필 사진으로 대체 -->
                <v-row>
                  <v-img :src="require('/src/assets/profileSample.png')" contain width="5%" height="5%"/>
                  <v-img :src="require('/src/assets/profileSample.png')" contain width="5%" height="5%"/>
                  <v-img :src="require('/src/assets/profileSample.png')" contain width="5%" height="5%"/>
                </v-row>
              </p>
              <br>
              <div style="vertical-align:middle;">
                <span class="material-icons" style="font-size:3.5vw;">
                  calendar_today
                </span>
                <!-- 사용자 지정 모임일로 대체 -->
                22.08.19
                <br>
                <span class="material-icons" style="font-size:3.5vw;">
                  room
                </span>
                <!-- 사용자 지정 장소로 대체 -->
                레드버튼 강남점
              </div>
            </div>
          </div>

          <br>
        
          <div class="d-flex justify-content-between" style="font-size:3.5vw; margin-left:3%;">
            <!-- 사용자 지정 모임비로 대체 -->
            <p>₩ 모임비 10000원</p>
            <!-- 현재인원/사용자 지정 참여인원 수로 대체 -->
            <p style="margin-left:5%;">참여가능 (3/4)</p>
          </div>  

        
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
    name: "MeetingListItem",
    data() {
        return {};
    },
    mounted() {
    },
    methods: {},
};
</script>

<style scoped>

</style>