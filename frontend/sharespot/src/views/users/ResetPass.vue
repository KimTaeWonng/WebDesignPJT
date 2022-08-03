<template>
    <v-container>
    <v-row class="text-center">
      <v-col cols="12">
        <div style="margin-top:10%;">
          비밀번호 재설정
        </div>
      

        <div style="margin-top:10%;">
          <span class="material-icons"  style="color:rgb(40,150,114); font-size:700%;">
            lock_reset
          </span>
        </div>

        <div style="margin-top:20%;">
          <p style="text-align: left; margin-left:3%">새 비밀번호</p>
            <div style="margin-left: 5%; margin-right: 5%; line-height: 0">
              <v-textarea
                label=""
                auto-grow
                outlined
                rows="1"
                row-height="15"
              ></v-textarea>
          </div>
        </div>

        <div>
          <p style="text-align: left; margin-left:3%">새 비밀번호 확인</p>
            <div style="margin-left: 5%; margin-right: 5%; line-height: 0">
              <v-textarea
                label=""
                auto-grow
                outlined
                rows="1"
                row-height="15"
              ></v-textarea>
          </div>
        </div>

        <v-row style="margin-top:5%;">
          <v-col cols="12">
            <div>
              <v-btn color="rgb(40,150,114)" dark width="50%" style="color:white; text-decoration: none" @click.stop="dialog = true">
                비밀번호 변경하기
              </v-btn>

              <v-dialog
        v-model="dialog"
        max-width="290"
      >
        <v-card>
          <div>
            <br>
            <br>
          </div>

          <div class="text-center" style="color:rgb(40,150,114);">
            <span class="material-icons" style="font-size:80px;">
              task_alt
            </span>
          </div>
        

          <div class="text-center">
            비밀번호 변경에<br>
            성공했습니다.
          </div>

          <div class="text-center" style="margin-top:10%;">
            <router-link to="/users/login" style="color:white; text-decoration: none">
              <v-btn color="rgb(40,150,114)" dark  @click.stop="dialog=false">
                확인
              </v-btn>
            </router-link>
          </div>

          <div>
            <br>
            <br>
          </div>

        </v-card>
      </v-dialog>

            </div>
          </v-col>
        </v-row>

      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
    name: 'S07P12A505ResetPass',

    data() {
        return {
          dialog: false,
        };
    },

    mounted() {
        
    },

    methods: {
        
    },
};
</script>

<style scoped>

</style>