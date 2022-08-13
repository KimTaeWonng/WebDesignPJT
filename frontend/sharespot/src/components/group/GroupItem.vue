<template>
  <v-container>
    <v-row>
      <v-col cols="12">
        <v-card style="text-align: left; font-size: 5vw">
          <div>
            <p
              style="
                float: left;
                font-weight: bold;
                margin-top: 3%;
                margin-left: 3%;
              "
            >
              <!-- 그룹명, 유저아이디로 대체 -->
              {{ group.group_name }} <br />
              운영진 | {{ group.group_nick }}
            </p>

            <!-- 그룹 대표 이미지로 대체 -->
            <p align="right">
              <img v-if="this.group.group_image.length" :src='`${ this.group.group_image }`' style='width:30%; height:30%;' />
            </p>
          </div>

          <br><br><br>

            <!-- 그룹 설명으로 대체 -->
            <div style="margin-left: 3%; text-align: left;">
              {{ group.group_content }}
            </div>
          

          <br />

          <div>
            <!-- 년생: 입력값으로 대체, 라디오 버튼 값으로 대체 -->
            <v-chip
              style="
                margin-left: 3%;
                font-weight: bold;
                font-size: 3vw;
                height: 5vw;
              "
              >{{ group.group_age_max }}년생부터 {{ group.group_age_min }}년생까지</v-chip
            >
            <v-chip
              style="
                margin-left: 3%;
                font-weight: bold;
                font-size: 3vw;
                height: 5vw;
              "
              >{{ group.group_gender }}</v-chip
            >
          </div>

          <!-- 현재/전체 참여자로 대체 -->
          <div
            style="margin-left: 5%; margin-top: 3%"
            class="d-flex justify-content-between"
          >
            <p style="color: #8cc7b3; font-size: 5vw">{{ this.currentMember }}/{{ group.group_limit }}명</p>
        
              <div style="margin-left:5%;">
                <v-chip
                  @click="goGroupDetail"
                  v-if="this.currentMember < group.group_limit"
                  color="#CCE9E1"
                  style="
                    
                    font-weight: bold;
                    font-size: 3vw;
                    height: 5vw;
                  "
                  >참여가능</v-chip
                >

                <v-chip
                  v-else
                  color="#C4C4C4"
                  style="
                    
                    font-weight: bold;
                    font-size: 3vw;
                    height: 5vw;
                  "
                  >참여불가</v-chip
                >
              </div>
          
          </div>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { http } from "@/js/http.js";

export default {
  name: "GroupItem",
  data() {
    return {
      group: {},
      currentMember: 0,
    };
  },

  mounted() {
  
  },

  methods: {
    goGroupDetail() {
      this.$router.push({
        name: "groupDetail",
        params: { groupno: this.group.group_id }
      })
    },
  },

  async created() {
    this.group = this.detailGroup
    // console.log (this.group.group_image)

    if (this.group.group_gender == '0') {
      this.group.group_gender = '모두'
    }
    else if (this.group.group_gender == '1') {
      this.group.group_gender = '남자만'
    }
    else {
      this.group.group_gender = '여자만'
    }

    const member = await http.get(`/group/members/${this.group.group_id}`)
    this.currentMember = 1+member.data.length
    
  },

  props: {
    detailGroup: Object,
  }
};
</script>

