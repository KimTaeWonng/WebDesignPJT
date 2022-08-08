<template>
  <v-container>
    <v-row class="text-center">
      <v-col cols="12">
        <v-row>
          <!-- 그룹 대표 이미지로 대체 -->
          <v-img
              :src="require('/src/assets/groupinfo.png')"
              class="mt-3"
              contain          
            />
        </v-row>
        <br>
        <br>

        <div style="text-align:left; vertical-align:middle;">
          <div style="font-weight:bold; margin-top:3%; margin-left:3%; font-size:5vw;" class="d-flex justify-content-between">
            <!-- 그룹 이름으로 대체 -->
            <p style="float:left;">[보드게임] 강남보드모임 GB</p>
            <v-btn color="rgb(40,150,114)" dark width="20%" height="7vw" style="margin-left:5%; font-size:3vw;"> 
              가입  
            </v-btn>
          </div>
        </div>
  
        
        <div style="margin-left:3%; text-align:left; font-size:3.3vw; font-weight:bold;">
          <!-- 그룹 설명으로 대체 -->
					보드게임 소모임입니다. <br>
					주요 활동지 : 강남, 역삼
				</div>
        
        <br>

        <div style="margin-left:3%; text-align:left; font-size:3.3vw; font-weight:bold;">
          <!-- 그룹 설명으로 대체 -->
					보드게임, 방탈출, 다트, 피크닉, 스포츠관람, 볼링 등 하고 <br>
          싶었던 것들, 혼자 하기 힘들었던 것들! <br>
          함께 즐겨요~ <br>
          즐겁게 게임하는 매너인 환영~ <br><br>
          · 영업, 상대방에게 불쾌감을 주는 행동 X <br><br>
          가입 후 공지사항 필독!!
        </div>

        <div>
          <p style="text-align: left; margin-left:3%; margin-top:3%; font-weight:bold; font-size:5vw;">
            모임 일정
            <span class="material-icons" style="vertical-align:middle; color:rgb(40,150,114); font-size:5vw;" @click.stop="dialog = true">add_circle_outline</span>

            <v-dialog
        v-model="dialog"
        max-width="290"
      >
        <v-card>
          <div style="margin-left:5%; margin-right:5%;">
            <br>
            <p class="text-center" style="font-weight:bold; font-size:5vw;">정모 생성하기</p>
          
            <v-col cols="12">
              <v-text-field
                placeholder="정모 이름"
                filled
                rounded
                dense
              ></v-text-field>
        
            <v-text-field
              filled
              rounded
              dense
              placeholder="정모 날짜"
              prepend-icon="calendar_today"
            ></v-text-field>

            <v-text-field
              filled
              rounded
              dense
              placeholder="정모 시간"
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
              style="margin-left:15%;"
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
              placeholder="정모 인원"
              prepend-icon="person_outline"
            ></v-text-field>

              <div class="text-center">
                <v-btn color="rgb(40,150,114)" dark width="35%" @click.stop="dialog = false"> 
                  생성하기
                </v-btn>
              </div>

            </v-col>
        </div>
        
      

        </v-card>
      </v-dialog>


            <MeetingListItem></MeetingListItem>
          </p>
        </div>

      </v-col>
    </v-row>
  </v-container>
</template>


<script>
import MeetingListItem from '@/components/group/MeetingListItem.vue';

export default {
  components: { MeetingListItem },
    name: "GroupInfo",

    data() {
        return {
          dialog: false,
        };
    },
    mounted() {
    },
    methods: {},
};
</script>

<style scoped>

</style>