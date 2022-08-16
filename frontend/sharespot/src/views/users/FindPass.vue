<template>
    <v-container>
      <back-menu
      title="비밀번호 재발급"
      class="mb-1"
    ></back-menu>
    
      <v-row class="text-center">
      <v-col cols="12">

        <div style="margin-top:10%;">
          <span class="material-icons" style="color:rgb(40,150,114); font-size:700%;">
            lock_reset
          </span>
        </div>

        <div style="font-size:24px;">
          로그인에 문제가 있나요?
        </div>
        <br>
        가입하신 아이디(이메일)를 입력하시면
        <br>
        비밀번호 재설정 링크를 보내드립니다.

        <div style="margin-top:20%;">
          <p style="text-align: left; margin-left:3%">아이디 (이메일)</p>
            <div style="margin-left: 5%; margin-right: 5%; line-height: 0">
              <v-textarea v-model="email"
                label=""
                auto-grow
                outlined
                rows="1"
                row-height="15"
              ></v-textarea>
          </div>
        </div>

        <v-row style="margin-top:10%;">
          <v-col cols="6">
            <div>
              <v-btn color="#99C5B9" dark width="80%">
                <router-link to="/users/login" style="color:white; text-decoration: none">
                  이전
                </router-link>
              </v-btn>
            </div>
          </v-col>
          <v-col cols="6">
            <div>
              <v-btn color="rgb(40,150,114)" dark width="80%" @click="resetPass" >
                재설정하기
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
            계정 비밀번호 변경 방법을<br>
            이메일로 보냈습니다.
          </div>

          <div class="text-center" style="margin-top:10%;">
            <v-btn color="rgb(40,150,114)" dark  @click.stop="dialog=false">
              확인
            </v-btn>
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
<!-- 
      <div>
        <br>
        <br>
        <v-btn color="rgb(40,150,114)" dark width="35%"  @click.stop="dialog2=true"> 
          재설정 실패시
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

          <div class="text-center" style="color:#FF647C;">
            <span class="material-icons" style="font-size:80px;">
              highlight_off
            </span>
          </div>
        

          <div class="text-center">
            사용자를 찾을 수 없습니다.<br>
            아이디를 다시 확인해주세요.
          </div>

          <div class="text-center" style="margin-top:10%;">
            <v-btn color=#FF647C dark @click="dialog=false">
              확인
            </v-btn>
          </div>

          <div>
            <br>
            <br>
          </div>

        </v-card>
      </v-dialog>
      </div> -->

      </v-col>
    </v-row>

    </v-container> 
</template>
  
<script>
import { http } from "@/js/http.js";
import BackMenu from "@/components/layout/BackMenu.vue";




export default {
    components: { BackMenu },
    name: 'FindPass',

    data() {
        return {
          email:"",
          dialog: false,
        };
    },

    mounted() {
        
    },

    methods: {
      
        async resetPass(){
          console.log(this.email);
          const response = await http.post(`/users/login/reset/${this.email}`)
          console.log(response);
          if(response.data==1){
            this.dialog=true;
          }else{
            alert("사용자를 찾을 수 없습니다. 이메일을 다시 확인해주세요.");
          }



        }
    },
};
</script>

<style scoped>

</style>