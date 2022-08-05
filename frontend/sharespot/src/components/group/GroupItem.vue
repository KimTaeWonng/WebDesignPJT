<template>
  <v-container>
    <v-row>
      <v-col cols="12">
        <v-card style="text-align:left; font-size:5vw;">
					<div>
						<p style="float:left; font-weight:bold; margin-top:3%; margin-left:3%;">
              <!-- 그룹명, 유저아이디로 대체 -->
							[보드게임] 강남보드모임 GB <br>
							운영진 | 티츄장인
						</p>
						
            <!-- 그룹 대표 이미지로 대체 -->
						<p align="right"><v-img
            :src="require('/src/assets/logo.png')"
						contain
            width="25%"
            height="25%"
          /></p>
						
            <!-- 그룹 설명으로 대체 -->
						<div style="margin-left:3%;">
							보드게임 소모임입니다. <br>
							주요 활동지 : 강남, 역삼
						</div>
					</div>

					<br>

					<div>
            <!-- 년생: 입력값으로 대체, 라디오 버튼 값으로 대체 -->
						<v-chip style="margin-left:3%; font-weight:bold;">1980년생부터 2000년생까지</v-chip>
						<v-chip style="margin-left:3%; font-weight:bold;">남녀모두</v-chip>
					</div>

          <!-- 현재/전체 참여자로 대체 -->
					<div style="margin-left:5%; margin-top:3%;" class="d-flex justify-content-between">
						<p style="color:#8CC7B3;">3/40명</p> 
						<v-chip color="#8CC7B3" style="margin-left:3%; font-weight:bold; color:white;">참여가능</v-chip>
					</div>
				</v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
    name: 'GroupItem',

    data() {
        return {
            
        };
    },

    mounted() {
        
    },

    methods: {
        
    },
};
</script>