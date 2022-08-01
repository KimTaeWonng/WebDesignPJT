<template>
 <v-container>
    <v-row class="text-center">

      <v-col
        cols="12"
        sm="6"
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
        
        <div>
          <p style="text-align:left; margin-left:3%; font-weight:bold;">생년월일(ex.19900909)</p>
          
          <v-switch 
        v-model="switch1"
        :label="`${switch1.toString()}`"
      ></v-switch>
        </div>

        <div style="margin-left:5%; margin-right:5%; line-height:0;">
          <v-textarea
          label=""
          auto-grow
          outlined
          rows="1"
          row-height="15"
        ></v-textarea>
        </div>

        <div>
          <p style="text-align:left; margin-left:3%; font-weight:bold;">성별 <v-icon>info</v-icon></p>

          <v-switch
        v-model="switch2"
        :label="`${switch2.toString()}`"
      ></v-switch>
        </div>
        
      <v-radio-group
        v-model="row"
        row
      >
        <v-radio
          label="남"
          value="radio-1"
        ></v-radio>
        <v-radio
          label="여"
          value="radio-2"
        ></v-radio>
      </v-radio-group>

        <v-row>
          <v-col cols="6">
            <div>
              <v-btn
                  color= rgb(40,150,114)
                  dark
        
              >
                이전
              </v-btn>
            </div>
          </v-col>
          <v-col cols="6">
            <div>
              <v-btn
                  color= rgb(40,150,114)
                  dark
                
              >
                가입하기
              </v-btn>
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
        switch1: true,
        switch2: true,
        column: null,
        row: null,
      }
    },
};

</script>
