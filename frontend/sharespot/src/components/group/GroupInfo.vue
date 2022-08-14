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
            <p style="float:left;">{{ group.group_name }}</p>
            <v-btn color="rgb(40,150,114)" dark width="20%" height="7vw" style="margin-left:5%; font-size:3vw;"> 
              가입  
            </v-btn>
          </div>
        </div>
  
        
        <div style="margin-left:3%; text-align:left; font-size:3.3vw; font-weight:bold;">
          <!-- 그룹 설명으로 대체 -->
					{{ group.group_content }}
				</div>
        
        <br>

        <!-- <div style="margin-left:3%; text-align:left; font-size:3.3vw; font-weight:bold;">
					{{ group.group_content }}
        </div> -->

        <div>
          <p style="text-align: left; margin-left:3%; margin-top:3%; font-weight:bold; font-size:5vw;">
            모임 일정
            <span class="material-icons" style="vertical-align:middle; color:rgb(40,150,114); font-size:5vw;" @click.stop="dialog = true">add_circle_outline</span>

            <v-dialog
        v-model="dialog"
        width="90%"
      >
        <v-card>
          <validation-observer ref="observer" v-slot="{ invalid }">

            <form @submit.prevent="submit" class="mr-6 ml-6">
              <div>

                <br>
                <p class="text-center" style="font-weight:bold; font-size:5vw;">정모 생성하기</p>
              
                <v-col cols="12">
                  <validation-provider
              v-slot="{ errors }"
              name="정모 이름"
              rules="required"
            >
                    <v-text-field
                      v-model="meeting.meetingTitle"

                      :error-messages="errors"
                      placeholder="정모 이름"
                      filled
                      rounded
                      dense
                      required
                    ></v-text-field>
                  </validation-provider>

                <validation-provider
              v-slot="{ errors }"
              name="정모 날짜"
              rules="required"
            >
                  <v-text-field
                    v-model="meeting.meetingDay"
                    :error-messages="errors"
                    filled
                    rounded
                    dense
                    placeholder="yyyy-MM-dd-HH:mm"
                    prepend-icon="calendar_today"
                  ></v-text-field>
                </validation-provider>
<!-- 
                <validation-provider
              v-slot="{ errors }"
              name="정모 시간"
              rules="required"
            >
                  v-model="meeting.meeting_time"
                  <v-text-field
                    
                    :error-messages="errors"
                    filled
                    rounded
                    dense
                    placeholder="정모 시간"
                    prepend-icon="schedule"
                  ></v-text-field>
                </validation-provider>  -->


                <validation-provider
              v-slot="{ errors }"
              name="정모 장소"
              rules="required"
            >
                    
                    <v-text-field
                    v-model="meeting.placeName"
                    :error-messages="errors"
                    filled
                    rounded
                    dense
                    placeholder="정모 장소"
                    prepend-icon="mdi-map-marker-outline"
                  ></v-text-field>
                </validation-provider>
                
                <validation-provider
              v-slot="{ errors }"
              name="지도 URL"
              rules="required"
            >
                  <v-text-field
                    v-model="meeting.meetingUrl"
                    :error-messages="errors"
                    filled
                    rounded
                    dense
                    placeholder="지도 URL"
                    prepend-icon="mdi-map"
                  ></v-text-field>
                </validation-provider>

                
                <validation-provider
              v-slot="{ errors }"
              name="정모비"
              rules="required"
            >
                  <v-text-field
                    v-model="meeting.meetingMoney"
                    :error-messages="errors"
                    filled
                    rounded
                    dense
                    placeholder="정모비"
                    prepend-icon="mdi-currency-krw"
                ></v-text-field>
                </validation-provider>

                <validation-provider
              v-slot="{ errors }"
              name="정모 인원"
              rules="required"
            >
                  <v-text-field
                  v-model="meeting.meetingPeople"
                  :error-messages="errors"
                  filled
                  rounded
                  dense
                  placeholder="정모 인원"
                  prepend-icon="person_outline"
                ></v-text-field>
                </validation-provider>

                  <div class="text-center">
                    <v-btn
                type="submit"
                :disabled="invalid"
                style="background-color: #289672; color: white"
                width="50%"
                @click="registMeeting"
              >
                생성하기
              </v-btn>
                  </div>
                </v-col>
              </div>
        
            </form>
          </validation-observer>
        </v-card>
      </v-dialog>


          </p>
        </div>

      </v-col>
    </v-row>
  </v-container>
</template>


<script>
import { extend, ValidationObserver, ValidationProvider } from "vee-validate";
import { required } from "vee-validate/dist/rules"
import { http } from "@/js/http.js";
// import { mapState } from "vuex";

// const userStore = "userStore";

extend("required", {
  ...required,
  message: "{_field_}(을)를 입력해주세요.",
});


export default {
  components: { ValidationProvider, ValidationObserver },
    name: "GroupInfo",

    data() {
        return {
          dialog: false,
          group: {},

          meeting: {
            meetingTitle: '',
            // meeting_time: '',
            placeName: '',
            meetingUrl: '',
            meetingMoney: 0,
            meetingDay: '',
            meetingPeople: 0,
            meetingLat: 0,
            meetingLng: 0,
            groupId: 1,
          },

          // async created() {
          //   this.group.group_manager = this.userInfo.user_id;
          //   this.group.group_nick = this.userInfo.nickname;
          // },
        };
    },

    // computed: {
    // ...mapState(userStore, ["userInfo"]),
    // },

    mounted() {
    },

    methods: {
      submit() {
      this.$refs.observer.validate();
    },

    async registMeeting() {
      console.log(this.meeting);
      const response = await http.post(`/group/${this.group.group_id}/meetings`, this.meeting);
      if (response.data == 1) {
        alert("모임 생성이 완료되었습니다.");
        this.$router.push({ name: "groupList" });
      } else {
        alert("모임 생성에 실패하였습니다.");
      }
    },

    async getGroup() {
        const response = await http.get(`/group/${this.no}`);
        this.group = response.data
      },
    },
    // async getMeeting() {
    //     const response = await http.get(`/group/${this.meeting.groupId}/meetings`);
    //     this.meetings = response.data
    //   },
    // },

    async created() {
      this.getGroup()
    },

    props: {
      no : String,
    },
};
</script>

<style scoped>

</style>