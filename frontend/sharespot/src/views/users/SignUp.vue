<template>
  <v-container>
    <v-row class="text-center">

      <v-col
        cols="12"
      >
        <div>
          <p style="font-weight:bold; text-align:center; margin-top:10%;">회원가입</p>
        </div>
    
        <p style="text-align:left; margin-left:3%; font-weight:bold;">아이디 (이메일)</p>
        <div style="margin-left:5%; margin-right:5%; line-height:0;">
          <v-textarea
          label=""
          auto-grow
          outlined
          rows="1"
          row-height="15"
        ></v-textarea>
        </div>
    
        <p style="text-align:left; margin-left:3%; font-weight:bold;">비밀번호</p>
        <div style="margin-left:5%; margin-right:5%; line-height:0;">
          <v-textarea
          label=""
          auto-grow
          outlined
          rows="1"
          row-height="15"
        ></v-textarea>
        </div>
  
        <p style="text-align:left; margin-left:3%; font-weight:bold;">비밀번호 확인</p>
        <div style="margin-left:5%; margin-right:5%; line-height:0;">
          <v-textarea
          label=""
          auto-grow
          outlined
          rows="1"
          row-height="15"
        ></v-textarea>
        </div>

        <p style="text-align:left; margin-left:3%; font-weight:bold;">이름</p>
        <div style="margin-left:5%; margin-right:5%; line-height:0;">
          <v-textarea
          label=""
          auto-grow
          outlined
          rows="1"
          row-height="15"
        ></v-textarea>
        </div>
      
        <p style="text-align:left; margin-left:3%; font-weight:bold;">닉네임</p>
        <div style="margin-left:5%; margin-right:5%; line-height:0;">
          <v-textarea
          label=""
          auto-grow
          outlined
          rows="1"
          row-height="15"
        ></v-textarea>
        </div>

        <p style="text-align:left; margin-left:3%; font-weight:bold;">전화번호</p>
        <div style="margin-left:5%; margin-right:5%; line-height:0;">
          <v-textarea
          label=""
          auto-grow
          outlined
          rows="1"
          row-height="15"
        ></v-textarea>
        </div>
        
        <div class="d-flex justify-content-between">
          <div style="text-align:left; margin-left:3%; font-weight:bold;">
            생년월일
            <v-tooltip
          v-model="show"
          top
        >
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              icon
              v-bind="attrs"
              v-on="on"
            >
              <v-icon style="color:black;"  @click="show = !show">
                mdi-information-outline
              </v-icon>
            </v-btn>
          </template>
          <div style="color:black; text-align:center;">생년월일을 비공개로 설정하면, <br>모든 모임을 볼 수 없어요!</div>
        </v-tooltip>
        </div>
        <div class="float-right" style="margin-left:3%;">
          <p v-if="unlock">공개</p>
          <p v-else>비공개</p>
          <v-switch v-model="switch1" flat style="float;" @click="toggle"></v-switch>
        </div>
      </div>

        <div style="margin-left:5%; margin-right:5%; line-height:0;">
          <v-textarea
          label=""
          auto-grow
          outlined
          rows="1"
          row-height="15"
          placeholder="(ex.19900909)"
        ></v-textarea>
        </div>

        <div class="d-flex justify-content-between">
          <div style="text-align:left; margin-left:3%; font-weight:bold;">
            성별
            <v-tooltip
          v-model="show2"
          top
        >
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              icon
              v-bind="attrs"
              v-on="on"
            >
              <v-icon style="color:black;"  @click="show2 = !show2">
                mdi-information-outline
              </v-icon>
            </v-btn>
          </template>
          <div style="color:black; text-align:center;">성별을 비공개로 설정하면, <br>모든 모임을 볼 수 없어요!</div>
        </v-tooltip>
          </div>
          <div class="float-right" style="margin-left:3%;">
            <p v-if="unlock2">공개</p>
            <p v-else>비공개</p>
            <v-switch v-model="switch2" flat style="float;" @click="toggle2"></v-switch>
          </div>
        </div>
        
      <div style="margin-left:3%;">
        <v-radio-group v-model="row" row>
          <v-radio label="남" value="radio-1" color="rgb(40,150,114)"></v-radio>
          <div style="margin-left:5%;">
            <v-radio label="여" value="radio-2" color="rgb(40,150,114)"></v-radio>
          </div>
        </v-radio-group>
      </div>

        <v-row>
          <v-col cols="6">
            <div>
              <router-link to="/users/login" style="color:white; text-decoration: none">
                <v-btn color="rgb(40,150,114)" dark width="80%">
                  이전
                </v-btn>
              </router-link>
            </div>
          </v-col>
          <v-col cols="6">
            <div>
              <v-btn color="rgb(40,150,114)" dark width="80%" @click.stop="dialog = true">
                가입하기
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
            <div style="font-weight:bold;">
              관리자 <span>님의</span>
            </div>
            가입이 완료되었습니다! 
          </div>

          <div class="text-center" style="margin-top:10%;">
            <router-link to="/users/login" style="color:white; text-decoration: none">
              <v-btn color="rgb(40,150,114)" dark>
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
  name: "SignUpView",
    data () {
      return {
        unlock: false,
        unlock2: false,
        dialog: false,
        show: false,
        show2: false,
      }
    },
    methods: {
      toggle() {
        this.unlock = !this.unlock;
      },
      toggle2() {
        this.unlock2 = !this.unlock2;
      }
    }
};

</script>
