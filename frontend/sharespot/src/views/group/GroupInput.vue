<template>
  <v-container>
    <v-row class="text-center">
      <v-col cols="12">
        <p style="text-align: left; margin-left: 3%">그룹 이름</p>
        <div style="margin-left: 5%; margin-right: 5%; line-height: 0">
          <v-textarea
            label=""
            auto-grow
            outlined
            rows="1"
            row-height="15"
          ></v-textarea>
        </div>

        <p style="text-align: left; margin-left: 3%">그룹 내용</p>
        <div style="margin-left: 5%; margin-right: 5%; line-height: 0">
          <v-textarea
            label=""
            auto-grow
            outlined
            
          ></v-textarea>
        </div>

        <p style="text-align: left; margin-left: 3%">
          그룹 대표 이미지 선택
          <span class="material-icons" style="vertical-align:middle; color:rgb(40,150,114);">add_circle_outline</span>
        </p>
        <div style="margin-left: 5%; margin-right: 5%; line-height: 0">
          <v-textarea
            label=""
            auto-grow
            outlined
            rows="8"
            disabled
          ></v-textarea>
        </div>

        <div>
          <p style="text-align: left; margin-left: 3%">연령대</p>
          <v-row style="margin-left: 5%; margin-right: 5%;">
            <v-text-field outlined style="width:15%;"></v-text-field>
            <p style="margin-top:5%;">년생~</p>

            <v-text-field outlined style="width:15%;"></v-text-field>
            <p style="margin-top:5%;">년생</p>
          </v-row>

          <v-radio-group v-model="row" row>
            <v-radio label="누구나" value="radio-1" color="rgb(40,150,114)" style="margin-left: 5%;"></v-radio>
          </v-radio-group>
        </div>

        <div>
          <p style="text-align: left; margin-left: 3%">성별</p>
          <div style="margin-left:5%;">
            <v-radio-group v-model="row" row>
              <v-radio label="남자만" value="radio-1" color="rgb(40,150,114)"></v-radio>
              <v-radio label="여자만" value="radio-2" color="rgb(40,150,114)"></v-radio>
              <v-radio label="남녀모두" value="radio-3" color="rgb(40,150,114)"></v-radio>
            </v-radio-group>
          </div>
        </div>

        <div>
          <router-link to="/group/detail/:groupno" style="color:white; text-decoration: none">
            <v-btn color="rgb(40,150,114)" dark width="35%"> 
              생성하기
            </v-btn>
          </router-link>
        </div>


      </v-col>
    </v-row>
  </v-container>
</template>

<script>

export default {
    name: "GroupInput",
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