<template>
  <v-container>
    <v-row class="text-center">
      <v-col cols="12">
        <v-card style="text-align:left; font-size:3vw;" @click.stop="dialog = true, checkmember()">
                <v-dialog
        v-model="dialog"
        max-width="80%"
      >
        <v-card>      
          <br>
          <div style="font-weight:bold; font-size:4vw;" class="text-center">
            {{ this.meeting.meetingTitle }}
          </div>

          <br>

          <div style="margin-left:3%; font-weight:bold; font-size:5vw;">
            <p>참가자</p>

            <div v-for="(member,i) in this.members" :key=i>
              <v-img :src=member.profileImage contain width="10%" height="10%" style="float:left;"/>
              <p style="font-size:4vw;">
                {{ member.userNick }} 
                <span v-if="i==0" style="color: #289672;">운영진</span>
              </p>
            </div>

          </div>
          <br>

          <div>
            <p style="margin-left:3%; font-weight:bold; font-size:5vw;">
              모임 정보 
              <v-icon style="color: #289672;">mdi-square-edit-outline</v-icon> 
            </p>
          </div>


          <div style="margin-left:5%; margin-right:5%;">
            <!-- <v-text-field
                disabled
                filled
                rounded
                dense
                placeholder=''
                prepend-icon="calendar_today"
              ></v-text-field> -->
              <div>
                <v-icon>calendar_today</v-icon> {{ this.meeting.meetingDay }}
              </div>
              <!-- <v-text-field
                disabled
                filled
                rounded
                dense
                placeholder="오후 07:00"
                prepend-icon="schedule"
              ></v-text-field> -->
              <div>
                <v-icon>schedule</v-icon> {{ this.meeting.meetingDay }}
              </div>
<!-- 
                <v-text-field
              
                filled
                rounded
                dense
                placeholder="정모 장소"
                prepend-icon="mdi-map-marker-outline"
              ></v-text-field> -->
              <div>
                <v-icon>mdi-map-marker-outline</v-icon> {{ this.meeting.placeName }}
              </div>

              
              <!-- <v-text-field
                
                filled
                rounded
                dense
                placeholder="지도 URL"
                prepend-icon="mdi-map"
              ></v-text-field> -->
              <div>
                <v-icon>mdi-map</v-icon> {{ this.meeting.meetingUrl }}
              </div>

                <!-- <v-text-field
                
                filled
                rounded
                dense
                placeholder="정모비"
                prepend-icon="mdi-currency-krw"
              ></v-text-field> -->
              <div>
                <v-icon>mdi-currency-krw</v-icon> {{ this.meeting.meetingMoney }}원
              </div>

                <!-- <v-text-field
                
                filled
                rounded
                dense
                placeholder="정원"
                prepend-icon="person_outline"
              ></v-text-field> -->
              <div>
                <v-icon>person_outline</v-icon> {{ this.membersid.length}} / {{ this.meeting.meetingPeople }}명
              </div>
          </div>
          <br>
          <div class="text-center">
            <v-btn v-if="this.ismember == false" color="rgb(40,150,114)" dark width="35%" @click.stop="dialog = false, join()" > 
              가입
            </v-btn>
            <v-btn v-else color="rgb(40,150,114)" dark width="35%" @click.stop="dialog = false, quit()"> 
              탈퇴
            </v-btn>
          </div>
          <br>

        </v-card>
      </v-dialog>


          <div style="margin-left:3%;">
            <div>
              <br>

              <!-- 모임 이름으로 대체 -->
              <v-row>
                <v-col cols="8">
                  <p style="font-weight:bold; float:left; font-size:8vw;"> {{ meeting.meetingTitle }} </p>
                  <p align="right" style="margin-right:3%;"></p>
                </v-col>
                  <!-- 참여자 프로필 사진으로 대체 -->
                <v-col cols="4">
                  <v-row style="margin-right:20%; margin-top:10%;">
                    <div v-for="(member,i) in this.members" :key=i>
                      <v-img :src=member.profileImage contain width="10%" height="10%" style="float:left;"/>
                    </div>
                  </v-row>
                </v-col>
              </v-row>

              <br>
              
              <div>
                <v-icon class="material-icons" style="font-size:3.5vw;">
                  calendar_today
                </v-icon>
                <!-- 사용자 지정 모임일로 대체 -->
                <span>
                  {{ meeting.meetingDay }}
                </span>

                <br>

                <v-icon class="material-icons" style="font-size:3.5vw;">
                  room
                </v-icon>
                <!-- 사용자 지정 장소로 대체 -->
                {{ meeting.placeName }} 

                <br>

                <v-icon style="font-size:3.5vw;">
                  mdi-map
                </v-icon>
                <!-- 사용자 지정 장소로 대체 -->
                {{ meeting.meetingUrl }}
              </div>
            </div>
          </div>

          <br>
        
          <div class="d-flex justify-content-between" style="font-size:3.5vw; margin-left:3%;">
            <!-- 사용자 지정 모임비로 대체 -->
            <p>₩ 모임비 {{ meeting.meetingMoney }}원</p>
            <!-- 현재인원/사용자 지정 참여인원 수로 대체 -->
            <p v-if="this.membersid.length < this.meeting.meetingPeople" style="margin-left:5%;">
              참여가능 {{ this.membersid.length}} / {{ this.meeting.meetingPeople }}
            </p>
            <p v-else style="margin-left:5%;">
              참여불가 {{ this.membersid.length}} / {{ this.meeting.meetingPeople }}
            </p>
          </div>  

        
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { http } from "@/js/http.js";
import { mapState } from "vuex";

const userStore = "userStore";

export default {
    name: "MeetingListItem",
    data() {
        return {
          dialog: false,
          meeting: {},
          members: [],
          membersid: [],
          manager: 0,
          ismember: false,
          date: '',
          time: '',
        };
    },
    mounted() {
    },
    
    
    computed: {
    ...mapState(userStore, ["userInfo"]),
    },

    methods: {
      async getmembers() {
        http.get(`/group/meetings/members/${this.meeting.meetingId}`)
        .then(res => {
          // console.log(res)
          console.log(res.data)
          this.members = res.data
          console.log('모임멤버', this.members)
          this.manager = res.data[0].userId
          console.log('모임장', this.manager)
          for(let i=0; i<res.data.length; i++) {
            console.log('모임데이터', res.data[i])
            console.log(res.data[i].userId)
            this.membersid.push(res.data[i].userId)

            const set = new Set(this.membersid)
            const newArr = [...set]
            this.membersid = newArr
            console.log('멤버들', this.membersid)
          }
          
        })   
      },

      async checkmember() {
        for (var i = 0; i < this.membersid.length; i++) {
          if (this.membersid[i] == this.me) {
            this.ismember = true
            break
          }
        };
        console.log(this.ismember)
      },

      async join() {
        this.membersid.push(this.me)
        await http.post(`/group/meetings/members/${this.meeting.meetingId}/${this.userInfo.user_id}`, )
        .then((res) => {
          console.log(res)
          console.log('정모 참가')
          this.ismember = true
          console.log(this.membersid)
        })
        .catch((err) => {
          console.log(err)
          console.log('정모 참가 실패')
        })
      },

      async quit() {
        for(let i = 0; i < this.membersid.length; i++) {
          if(this.membersid[i] == this.userInfo.user_id)  {
            this.membersid.splice(i, 1);
            i--;
            break
          }
        }
        await http.delete(`/group/meetings/members/${this.meeting.meetingId}/${this.userInfo.user_id}`)
        .then((res) => {
          console.log(res)
          console.log('정모 탈퇴')
          this.ismember = false
          console.log(this.membersid)
        })
        .catch((err) => {
          console.log(err)
          console.log('정모 탈퇴 실패')
        })
      },


      },
    

    async created() {
      this.meeting = this.detailMeeting
      this.me = this.userInfo.user_id
      console.log('나', this.me)
      console.log(this.userInfo.nickname)

      this.getmembers()
      console.log('멤버', this.members)
      
    },

    props: {
      detailMeeting: Object,
    },


};
</script>

<style scoped>

</style>