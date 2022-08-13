<template>
  <v-container>
    <v-row class="text-center">
      <v-col cols="12">
        <v-card style="text-align:left; font-size:3vw;" @click.stop="dialog = true">
                <v-dialog
        v-model="dialog"
        max-width="80%"
      >
        <v-card>      
          <br>
          <div style="font-weight:bold; font-size:4vw;" class="text-center">
            불금 보드게임은 국룰이죠 (4명)
          </div>

          <br>

          <div style="margin-left:3%; font-weight:bold; font-size:5vw;">
            <p>참가자</p>
            <div>
              <v-img :src="require('/src/assets/profileSample.png')" contain width="10%" height="10%" style="float:left;"/>
              <p style="font-size:4vw;">맞집찾아삼만리 <span style="color: #289672;">운영진</span></p>
            </div>
            <div>
              <v-img :src="require('/src/assets/profileSample.png')" contain width="10%" height="10%" style="float:left;"/>
              <p style="font-size:4vw;">가모라</p>
            </div>
            <div>
              <v-img :src="require('/src/assets/profileSample.png')" contain width="10%" height="10%" style="float:left;"/>
              <p style="font-size:4vw;">토끼공듀</p>
            </div>
          </div>
          <br>

          <div>
            <p style="margin-left:3%; font-weight:bold; font-size:5vw;">모임 정보 <v-icon style="color: #289672;">mdi-square-edit-outline</v-icon> </p>
          </div>


          <div style="margin-left:5%; margin-right:5%;">
            <v-text-field
                filled
                rounded
                dense
                placeholder="2022.07.22"
                prepend-icon="calendar_today"
              ></v-text-field>

              <v-text-field
                filled
                rounded
                dense
                placeholder="오후 07:00"
                prepend-icon="schedule"
              ></v-text-field>

                <v-text-field
                filled
                rounded
                dense
                placeholder="정모 장소"
                prepend-icon="mdi-map-marker-outline"
              ></v-text-field>
              
              <v-text-field
                filled
                rounded
                dense
                placeholder="지도 URL"
                prepend-icon="mdi-map"
              ></v-text-field>

                <v-text-field
                filled
                rounded
                dense
                placeholder="정모비"
                prepend-icon="mdi-currency-krw"
              ></v-text-field>

                <v-text-field
                filled
                rounded
                dense
                placeholder=""
                prepend-icon="person_outline"
              ></v-text-field>
          </div>

          <div class="text-center">
            <v-btn color="rgb(40,150,114)" dark width="35%" @click.stop="dialog = false"> 
              확인
            </v-btn>
          </div>
          <br>

        </v-card>
      </v-dialog>


          <div style="margin-left:3%;">
            <div>
              <br>
              <!-- 모임 이름으로 대체 -->
              <p style="font-weight:bold; float:left;"> 데이터왜안받아와져왜 </p>
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
// import { http } from "@/js/http.js";

export default {
    name: "MeetingListItem",
    data() {
        return {
          dialog: false,
          meeting: {},
        };
    },
    mounted() {
    },

    methods: {
    
    },

    async created() {
      this.meeting = this.detailMeeting
    },

    props: {
      detailMeeting: Object,
    },

};
</script>

<style scoped>

</style>