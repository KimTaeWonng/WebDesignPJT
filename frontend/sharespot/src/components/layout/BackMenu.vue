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
        max-width="200"
      >
        <v-card>
          <div class="text-center" style="font-weight:bold; font-size:4vw; align-items:center;">
            <v-col @click.stop="dialog = false()">
              <span style="color:#FF0000;">그룹 신고</span>
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
  },
};
</script>

<style scoped></style>
