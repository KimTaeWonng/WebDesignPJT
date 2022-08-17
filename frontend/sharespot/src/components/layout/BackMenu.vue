<template>
  <v-container fluid>
    <v-row class="text-center justify-sm">
      <v-col cols="3" @click="goBack()">
        <v-icon>mdi-chevron-left</v-icon>
      </v-col>
      <v-col class="text-center" cols="6">
        {{ title }}
      </v-col>
      <v-col cols="3" v-if="isRight">
        <!-- type: groupDetail -->
        <v-row v-if="type == 'groupDetail'">
          <v-col cols="6">
            <v-icon>mdi-share-variant-outline</v-icon>
          </v-col>
          <v-col cols="6" style="padding: 12px 0px">
            <v-icon class="mr-4" @click.stop="dialog = true"
              >mdi-dots-horizontal</v-icon
            >
          </v-col>

          <v-dialog
        v-model="dialog"
        max-width="80%"
      >
        <v-card>
          <div class="text-center" style="font-weight:bold; font-size:4vw; align-items:center;">
            <v-col>
              
              <span style="color:#FF0000;"  @click.stop="event1()">그룹 신고</span>
              
              <v-dialog
        v-model="dialog2"
        max-width="80%"
      >
        <v-card>
          <div style="font-weight:bold; align-items:center;">
            <v-col>
              <p style="font-size:5vw; margin-left:3%;">신고 내용을 기재해주세요.</p>
              <v-textarea
                filled
                placeholder="허위 신고 시 이용이 제한될 수 있습니다."
                auto-grow
                outlined
                rows="6"
              ></v-textarea>
          
              <v-row style="margin-left:3%; margin-right:3%;" class="text-center">
                <v-col cols="6">
                  <v-btn color="#289672" dark width="50%" @click.stop="dialog2 = false" style="font-size:3.3vw;"> 
                    취소 
                  </v-btn>
                </v-col>
                <v-divider></v-divider>
                <v-col>
                  <span>가입한 멤버 보기</span>
                </v-col>
              </v-row>
            </v-col>
          </div>


        </v-card>
      </v-dialog>
            </v-col>

            <v-divider></v-divider>

            <v-col>
              
              <span @click.stop="dialog3 = true">가입한 멤버 보기</span>
              
                <v-dialog
        v-model="dialog3"
        max-width="80%"
      >
        <v-card>
          <div style="font-weight:bold; align-items:center;">
            <v-col>
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
            </v-col>
          </div>
        </v-card>
      </v-dialog>
            </v-col>
            
            <v-divider></v-divider>

            <v-col v-show="this.isGmember == true">
            
              <span v-if="this.manager == this.userInfo.user_id" @click='delGroup'>그룹 삭제</span>
              <span v-else @click='quitGroup'>그룹 나가기</span>
              
            </v-col>
          </div>
        </v-card>
      </v-dialog>


        </v-row>
        <!-- type: modify -->
        <div v-if="type == 'modify'" style="color: #289672; font-weight: bold">
          완료
        </div>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { http } from "@/js/http.js";
import { mapState } from "vuex";

const userStore = "userStore";

export default {
  name: "BackMenu",
  props: {
    title: String,
    isRight: Boolean,
    type: String,
    detailGroup: Object,
  },

  data() {
    return {
      dialog: false,
      dialog2: false,
      dialog3: false,
      group: {},
      isGmember: false,
      membersid: [],
      manager: 0,
      members: [],
    };
  },

  mounted() {},

  methods: {
    editFinish() {
      //axios 쓰기
    },

    async getmembers() {
        http.get(`/group/members/${this.$route.params.groupno}`)
        .then(res => {
          // console.log(res)
          // console.log(res.data)
          this.members = res.data
          // console.log('모임멤버', this.members)
          this.manager = res.data[0].userId
          // console.log('모임장', this.manager)
          for(let i=0; i<res.data.length; i++) {
            // console.log('모임데이터', res.data[i])
            // console.log(res.data[i].userId)
            this.membersid.push(res.data[i].userId)

            const set = new Set(this.membersid)
            const newArr = [...set]
            this.membersid = newArr
            console.log('백멤버들', this.membersid)
          }
          
        })   
      },

    goBack() {
      this.$router.go(-1);
    },

    event1() {
      (this.dialog = !this.dialog), (this.dialog2 = !this.dialog2);
    },

    async delGroup() {
      await http.delete(`/group/${this.$route.params.groupno}`)  
    },

    async quitGroup() {
      for(let i = 0; i < this.membersid.length; i++) {
        if(this.membersid[i] == this.userInfo.userId)  {
          this.membersid.splice(i, 1);
          i--;
          break
        }
      }

      await http.delete(`/group/${this.$route.params.groupno}/${this.userInfo.user_id}`)
      .then((res) => {
        console.log(res)
        console.log('그룹 탈퇴')
        this.isGmember == false
      })
      .catch((err) => {
        console.log(err)
        console.log('그룹 탈퇴 실패')
      })
      
      // this.$router.go();
    },

  },

  async created() {
    this.group = this.detailGroup
    // console.log('백메뉴', typeof this.detailGroup)
    // console.log('백메뉴', this.group)

    const members = await http.get(`/group/members/${this.$route.params.groupno}`)
    
      for(let i=0; i<members.data.length; i++) {
        this.membersid.push(members.data[i].userId)
      }
      // console.log('백가입자', this.membersid)
      
      const res = await http.get(`group/${this.$route.params.groupno}`)
      this.manager = res.data.group_manager
      // console.log('백그룹장', this.manager)
      
      for (var i = 0; i < this.membersid.length; i++) {
        if (this.membersid[i] == this.userInfo.user_id) {
          this.isGmember = true
          break
      }
      };

      console.log(this.$router.history.current.name)
      if (this.$router.name == 'groupDetail') {
        this.getmembers()
      }
      // console.log('백가입여부', this.isGmember)
  },

  computed: {
    ...mapState(userStore, ["userInfo"]),
    },
};
</script>

<style scoped>
v-textarea {
  font-size: 3.3vw;
}
</style>
