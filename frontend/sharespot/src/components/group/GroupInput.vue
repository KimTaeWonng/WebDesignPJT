<template>
  <v-container>
    <v-row class="text-center">
      <v-col cols="12">
          <validation-observer ref="observer" v-slot="{ invalid }">
            <form @submit.prevent="submit" class="mr-6 ml-6">

              <p style="text-align: left; margin-left: 3%; font-size:4.5vw;">그룹 이름</p>
                <validation-provider 
            v-slot="{ errors }" 
            name="그룹 이름"
            rules="name_required"
            >
              <v-text-field
                class="mt-3"
                v-model="group.name"
                label=""
                :error-messages="errors"
                required
                color="#289672"
              ></v-text-field>
            </validation-provider>

              <p style="text-align: left; margin-left: 3%; font-size:4.5vw;">그룹 내용</p>
                <validation-provider 
            v-slot="{ errors }" 
            name="그룹 내용"
            rules="content_required"
            >
              <v-textarea
                v-model="group.content"
                label=""
                :error-messages="errors"
                required
                color="#289672"
                auto-grow
                outlined
              ></v-textarea>
            </validation-provider>


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
                <v-img v-model="group.image" v-if="image != ''" :src="image" style="width:100%; height:30%;"></v-img>
              </div>

              <br>

              <div>
                <p style="text-align: left; margin-left: 3%; font-size:4.5vw;">연령대</p>
                <v-row style="margin-left: 5%; margin-right: 5%; font-size:5vw;">
                  <!-- 정규 표현식 필요 -->
                  <v-col cols="3">
                    
                      <validation-provider
                        v-slot="{ errors }"
                        name="연령대1"
                        :rules="{
                            age_required: true,
                            age_regex: '(19|20)(\\d{2})',
                          }"
                          >
                        <v-text-field
                          v-model="group.age_min"
                          :error-messages="errors"
                          label=""
                          required
                          color="#289672"
                        ></v-text-field>
                      </validation-provider>
                    
                  </v-col>

                  <v-col cols="3" style="font-size:3.3vw; margin-top:10%;">
                    년생~
                  </v-col>

                  <v-col cols="3">
                    <validation-provider
                        v-slot="{ errors }"
                        name="연령대2"
                        :rules="{
                            age_required: true,
                            age_regex: '(19|20)(\\d{2})',
                          }"
                          >
                      <v-text-field
                          v-model="group.age_max"
                          :error-messages="errors"
                          label=""
                          required
                          color="#289672"
                      ></v-text-field>
                    </validation-provider>   
                  </v-col>

                  <v-col cols="3" style="font-size:3.3vw; margin-top:10%;">
                    년생
                  </v-col>
                </v-row>

                <!-- <v-radio-group v-model="row" row>
                  <v-radio label="누구나" value="radio-1" color="rgb(40,150,114)" style="margin-left: 5%; font-size:4.5vw;"></v-radio>
                </v-radio-group> -->
              </div>

              <div>
                <p style="text-align: left; margin-left: 3%; font-size:4.5vw;">성별</p>
                <div style="margin-left:5%;">
                  <validation-provider 
                    v-slot="{ errors }" 
                    name="성별제한"
                    rules="required"
                    >
                    <v-radio-group v-model="group.gender" :error-messages="errors" required row>
                      <v-radio label="남" value="radio-1" color="rgb(40,150,114)"></v-radio>
                      <v-radio label="여" value="radio-2" color="rgb(40,150,114)"></v-radio>
                      <v-radio label="모두" value="radio-3" color="rgb(40,150,114)"></v-radio>
                    </v-radio-group>
                  </validation-provider>
                </div>
              </div>

              <div>
                <v-btn
                  type="submit"
                  :disabled="invalid"
                  style="background-color: #289672; color: white"
                  width="50%"
                  @click="registGroup"
                >
                  생성하기
                </v-btn>
              </div>


        
          </form>
        </validation-observer>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { extend, ValidationObserver, ValidationProvider } from "vee-validate";
import { required, regex } from "vee-validate/dist/rules";
import { http } from "@/js/http.js";


extend("age_required", {
  ...required,
  message: " ",
});

extend("age_regex", {
  ...regex,
  message: " ",
});

extend("name_required", {
  ...required,
  message: " ",
});

extend("content_required", {
  ...required,
  message: " ",
});


export default {
    name: "GroupInput",
    components: { ValidationProvider, ValidationObserver },
    data() {
      return {
        image: '',

        group: {
          name: '',
          content: '',
          limit: '',
          gender: '',
          age_min: '',
          age_max: '',
          image: '',
        },
      }
    },
    methods: {
      uploadImg() {
        var image = this.$refs['image'].files[0]
        const url = URL.createObjectURL(image)
        this.image = url
      },

      submit() {
      this.$refs.observer.validate()
      },

      async registGroup() {
      
      const response = await http.post("/group/register", this.group);
      console.log(response.data);
      console.log(this.group);

      if (response.data.message == "success") {
        console.log("success")
        this.$router.push({ name: "groupList" });
      } else {
        console.log("fail")
      }
      },
    },
  }
</script>

<style scoped>

</style>