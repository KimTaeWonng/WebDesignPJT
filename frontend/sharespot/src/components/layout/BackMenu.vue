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
        <v-row v-if="type == 'groupDetail'">
          <v-col cols="6">
            <v-icon>mdi-share-variant-outline</v-icon>
          </v-col>
          <v-col cols="6" style="padding: 12px 0px">
            <v-icon class="mr-4" @click.stop="dialog = true">mdi-dots-horizontal</v-icon>
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

                <v-col cols="6">
                  <div>
                    <v-btn color="#289672" dark width="50%" style="font-size:3.3vw;">
                      확인
                    </v-btn>
                  </div>
                </v-col>
              </v-row>
            </v-col>
          </div>


        </v-card>
      </v-dialog>
            </v-col>

            <v-divider></v-divider>
            <v-col>
              <span>가입한 멤버 보기</span>
            </v-col>
            
            <v-divider></v-divider>

            <v-col>
              <span>정산하기</span>
            </v-col>

            <v-divider></v-divider>

            <v-col>
              <span>그룹 나가기</span>
            </v-col>
          </div>
        </v-card>
      </v-dialog>


        </v-row>
        <div v-if="type == 'modify'" style="color: #289672; font-weight: bold">
          완료
        </div>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: "BackMenu",
  props: {
    title: String,
    isRight: Boolean,
    type: String,
  },

  data() {
    return {
      dialog: false,
      dialog2: false,
    };
  },

  mounted() {},

  methods: {
    editFinish() {
      //axios 쓰기
    },

    goBack() {
      this.$router.go(-1);
    },

    event1() {
      this.dialog = !this.dialog,
      this.dialog2 = !this.dialog2
    },
  },
};
</script>

<style scoped>
  v-textarea {
        font-size: 3.3vw;
      }
</style>
