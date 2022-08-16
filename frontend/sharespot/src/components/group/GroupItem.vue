<template>
  <v-container>

  <v-card
    class="mx-auto"
    
    style="text-align: left; font-size: 5vw; padding:0%; margin-bottom: 3%;"
  >
    <v-list-item three-line>
      <v-list-item-content >
        <div style="
                float: left;
                font-weight: bold;
                margin-top: 3%;
                ">
          {{ group.group_name }}
        </div>
        <v-list-item-title style="
        font-weight: bold;

        font-size: 4vw;">
          운영진 | {{ group.group_nick }}
        </v-list-item-title>
        <v-list-item-title style="font-size: 4vw;">{{ group.group_content }}</v-list-item-title>
      </v-list-item-content>

      <v-list-item-avatar
        tile
        size="90">
        <img v-if="this.group.group_image.length" :src='`${ this.group.group_image }`' style='width:95%; height:95%;' />
      </v-list-item-avatar>
    </v-list-item>

      <v-row style="margin-top: 3%; margin-left: 1%;">
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
      </v-row>

      <v-row >

      <v-col cols="5" style="color: #8cc7b3; font-size: 5vw; margin-left: 3%;">
              <v-icon color="#CCE9E1">
          mdi-account-circle-outline
        </v-icon>
        {{ this.currentMember }}/{{ group.group_limit }}명</v-col>
      <v-col cols="6" style="margin-right: 5%; "  align="right">

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

      </v-col>
    </v-row>

  </v-card>
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
    this.currentMember = member.data.length
    
  },

  props: {
    detailGroup: Object,
  }
};
</script>

