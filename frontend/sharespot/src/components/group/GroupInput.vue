<template>
  <v-container>
    <v-row class="text-center">
      <v-col cols="12">
        <p style="text-align: left; margin-left: 3%; font-size:4.5vw;">그룹 이름</p>
        <div style="margin-left: 5%; margin-right: 5%; line-height: 0">
          <v-textarea
            label=""
            auto-grow
            outlined
            rows="1"
            row-height="15"
          ></v-textarea>
        </div>

        <p style="text-align: left; margin-left: 3%; font-size:4.5vw;">그룹 내용</p>
        <div style="margin-left: 5%; margin-right: 5%; line-height: 0">
          <v-textarea
            label=""
            auto-grow
            outlined
            
          ></v-textarea>
        </div>

        <div style="text-align: left; margin-left: 3%; font-size:4.5vw;">
          그룹 대표 이미지 선택
          <label for="chooseFile"><span class="material-icons" style="vertical-align:middle; color:rgb(40,150,114); font-size:5vw;">add_circle_outline</span></label>
          <div>
            <form method="post" enctype="multipart/form-data">
              <input style="display:none;" ref="image" @change="uploadImg()" type="file" id="chooseFile" name="chooseFile" accept="image/*">
            </form>
          </div>
        </div>

        <div style="margin-left: 5%; margin-right: 5%;">
          <v-img v-if="image != ''" :src="image" style="width:100%; height:30%;"></v-img>
        </div>

        <br>

        <div>
          <p style="text-align: left; margin-left: 3%; font-size:4.5vw;">연령대</p>
          <v-row style="margin-left: 5%; margin-right: 5%; font-size:5vw;">
            <!-- 정규 표현식 필요 -->
            <v-col cols="3">
              <v-text-field color="#289672" style="font-size:5vw;"></v-text-field>
            </v-col>
            <v-col cols="3" style="font-size:3.3vw; margin-top:10%;">
              년생~
            </v-col>
            <v-col cols="3">
              <v-text-field color="#289672" style="font-size:5vw;"></v-text-field>
            </v-col>
            <v-col cols="3" style="font-size:3.3vw; margin-top:10%;">
              년생
            </v-col>
          </v-row>

          <v-radio-group v-model="row" row>
            <v-radio label="누구나" value="radio-1" color="rgb(40,150,114)" style="margin-left: 5%; font-size:4.5vw;"></v-radio>
          </v-radio-group>
        </div>

        <div>
          <p style="text-align: left; margin-left: 3%; font-size:4.5vw;">성별</p>
          <div style="margin-left:5%;">
            <v-radio-group v-model="row" row>
              <v-radio label="남자만" value="radio-1" color="rgb(40,150,114)" style="font-size:4.5vw;"></v-radio>
              <v-radio label="여자만" value="radio-2" color="rgb(40,150,114)" style="font-size:4.5vw;"></v-radio>
              <v-radio label="남녀모두" value="radio-3" color="rgb(40,150,114)" style="font-size:4.5vw;"></v-radio>
            </v-radio-group>
          </div>
        </div>

        <div>
          <router-link to="/group/detail/:groupno" style="color:white; text-decoration: none">
            <v-btn color="rgb(40,150,114)" dark width="50%" style="font-size:4vw;"> 
              생성하기
            </v-btn>
          </router-link>
        </div>


      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { extend } from "vee-validate";
import { regex } from "vee-validate/dist/rules";

extend("regex", {
  ...regex,
  message: "{_field_} 형식이 올바르지 않습니다.",
});

export default {
    name: "GroupInput",
    data() {
      return {
        image : '',
      }
    },
    methods: {
      uploadImg() {
        var image = this.$refs['image'].files[0]
        const url = URL.createObjectURL(image)
        this.image = url
      },
    },
  }
</script>

<style scoped>

</style>