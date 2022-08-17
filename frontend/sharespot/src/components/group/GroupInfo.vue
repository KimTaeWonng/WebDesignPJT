<template>
  <v-container>
    <v-row class="text-center">
      <v-col cols="12">
        <v-row>
          <!-- 그룹 대표 이미지로 대체 -->
          <v-img
              :src="detailGroup.group_image"
              class="mt-3"
              contain        
            />
        </v-row>
        <br>
        <br>

        <div style="text-align:left; vertical-align:middle;">
          <div style="font-weight:bold; margin-top:3%; margin-left:3%; font-size:5vw;" class="d-flex justify-content-between">
            <!-- 그룹 이름으로 대체 -->
            <p style="float:left;">{{ detailGroup.group_name }}</p>
            <v-btn v-if="this.ismember == false" @click="join" color="rgb(40,150,114)" dark width="20%" height="7vw" style="margin-left:5%; font-size:3vw;"> 
              가입
            </v-btn>
            <v-btn v-else-if="this.userInfo.user_id == this.manager" @click="modify" color="rgb(40,150,114)" dark width="20%" height="7vw" style="margin-left:5%; font-size:3vw;"> 
              수정
            </v-btn>
            <v-btn v-else @click="quit" color="rgb(40,150,114)" dark width="20%" height="7vw" style="margin-left:5%; font-size:3vw;"> 
              탈퇴
            </v-btn>
          </div>
        </div>
  
        
        <div style="margin-left:3%; text-align:left; font-size:3.3vw; font-weight:bold;">
          <!-- 그룹 설명으로 대체 -->
					{{ detailGroup.group_content }}
				</div>
        
        <br>

        <!-- <div style="margin-left:3%; text-align:left; font-size:3.3vw; font-weight:bold;">
					{{ group.group_content }}
        </div> -->

        <div>
          <p style="text-align: left; margin-left:3%; margin-top:3%; font-weight:bold; font-size:5vw;">
            모임 일정
            <span v-if="this.ismember == true" class="material-icons" style="vertical-align:middle; color:rgb(40,150,114); font-size:5vw;" @click.stop="dialog = true">
              add_circle_outline
            </span>
          
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
// import MeetingListItem from '@/components/group/MeetingListItem.vue';
import { mapState } from "vuex";

const userStore = "userStore";

extend("required", {
  ...required,
  message: "{_field_}(을)를 입력해주세요.",
});


export default {
  components: { ValidationProvider, ValidationObserver },
  name: "GroupInfo",
  props: {
    detailGroup: Object
  },


    data() {
        return {
          dialog: false,
          group: {},
          membersid: [],
          manager: 0,
          ismember: false,

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

        
        };
    },

    computed: {
    ...mapState(userStore, ["userInfo"]),
    },


    async created() {
      this.group = this.detailGroup
      console.log(this.userInfo.user_id)
      const members = await http.get(`/group/members/${this.$route.params.groupno}`)
    
      for(let i=0; i<members.data.length; i++) {
        this.membersid.push(members.data[i].userId)
      }
      console.log(this.membersid)

      const res = await http.get(`group/${this.$route.params.groupno}`)
      this.manager = res.data.group_manager
      console.log(this.manager)

      for (var i = 0; i < this.membersid.length; i++) {
        if (this.membersid[i] == this.userInfo.user_id) {
          this.ismember = true
          break
      }
      };
      console.log(this.ismember)
    },

    mounted() {
    
    },

    methods: {
      submit() {
      this.$refs.observer.validate();
    },

    async join() {
      this.membersid.push(this.userInfo.userId)
      await http.post(`/group/${this.$route.params.groupno}/${this.userInfo.user_id}`)
      .then((res) => {
        console.log(res)
        console.log('그룹 참가')
        this.ismember = true
        console.log(this.membersid)
      })
      .catch((err) => {
        console.log(err)
        console.log('그룹 참가 실패')
      })

      this.$router.go();
      },
  
    modify() {
      this.$router.push({ name: "groupModify" })
    },

    async quit() {
      for(let i = 0; i < this.membersid.length; i++) {
        if(this.membersid[i] == this.userInfo.userId)  {
          this.membersid.splice(i, 1);
          i--;
          break
        }
      }

      const response = await http.delete(`/group/${this.$route.params.groupno}/${this.userInfo.user_id}`)
        if (response.data == 1) {
          console.log('그룹 탈퇴')
          this.ismember == false
          console.log(this.membersid)
          // this.$router.go();
        } else {
          console.log('그룹 탈퇴 실패')
        }
      this.$router.go();
    },

    async registMeeting() {
      // console.log(this.meeting);
      this.meeting.groupId = this.$route.params.groupno
      const response = await http.post(`/group/${this.$route.params.groupno}/meetings`, this.meeting);
      if (response.data == 1) {
        alert("모임 생성이 완료되었습니다.");
        this.$router.go();
      } else {
        alert("모임 생성에 실패하였습니다.");
      }

      const getmeeting = await http.get(`/group/${this.$route.params.groupno}/meetings`)

      const newmeeting = getmeeting.data.at(-1);
      console.log(newmeeting)

      const mid = newmeeting.meetingId
      console.log(mid)

      await http.post(`/group/meetings/members/${mid}/${this.userInfo.user_id}`, {
        mid: mid,
        userId: this.userInfo.user_id
      })

    },

    },

    


};
</script>

<style scoped>

</style>