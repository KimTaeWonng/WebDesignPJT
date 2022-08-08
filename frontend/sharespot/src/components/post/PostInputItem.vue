<template>
<!-- 상단 네브바 -->
<div>
  <v-row class="text-center d-flex" align="center" style="margin-top: 3px; margin-bottom: 3px;">
    <!-- 닫기 버튼 -->
      <v-col cols="2" @click="changeRouter('')">
        <v-icon>mdi-close</v-icon>
      </v-col>
      <!-- 로고 -->
      <v-col cols="8">
        <span style="color: #289672; font-weight: 800;">Share Spot</span>
      </v-col>
      <!-- 돋보기/설정 버튼 -->
      <v-col cols="2">
        <v-row>
          <!-- 여기에 제출 넣으면 될듯 -->
          <v-col align="center" @click="changeRouter('')">
        <v-icon>mdi-check</v-icon>
      </v-col>
        </v-row>
      </v-col>
    </v-row>
    <!-- 프로필 사진 변경 start 카드 크기 반응형으로 -->
  <v-row justify="space-around" style="margin:0%;">
  <v-card  height="290px" width="290px" >
    <v-row class="text-center" style="margin-top:35%"  align="center">
      <v-col>
        <label for="file">
        <v-avatar color="#289672" size="50">
          <v-icon v-if="user.img == ''" color="#ffffff"> mdi-plus </v-icon>
          <v-img v-if="user.img != ''" :src="user.img"></v-img>
        </v-avatar>
        <v-file-input
          dense
          hide-input
          v-model="user.img"
          color="#289672"
          prepend-icon=""
          id="file"
        ></v-file-input>
      </label>
      </v-col>
    </v-row>
  </v-card>
  </v-row>

<!-- 태그추가 + 버튼 -->
<v-col>
<v-cols cols="3" style="margin-left:5%; margin-bottom:10%; margin-top:5%;" align="center">
  태그 추가
</v-cols>
<v-cols cols="2">
    <v-dialog
      v-model="dialog"
      scrollable
      max-width="600px"
    >
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          color="primary"
          dark
          v-bind="attrs"
          v-on="on"
          icon
          
        >
          <v-avatar color="#289672" size="20">
          <v-icon color="#ffffff" small> mdi-plus </v-icon>
        </v-avatar>
        </v-btn>
      </template>
      <v-card>
        <v-card-text style="margin-top:15%; font-weight:bolder;">태그 추가 (항목 당 1개씩 골라주세요)</v-card-text>   
        <!-- 분류 제목 + 버튼 -->
      <v-list>
      <template v-for="(item, index) in tagitems">
        <v-subheader 
          v-if="item.header"
          :key="item.header"
          v-text="item.header"
          class="font-weight-bold"
          align="center"
        ></v-subheader>

        <v-divider
          v-else-if="item.divider"
          :key="index"
        ></v-divider>

<!-- 안에 들어가는 내용 -->
    <v-list-item
      v-else
      :key="item.title"
      class="my-1"
    > 
      <v-item-group mandatory>
        <v-container>
          <v-row>
            <v-col
              v-for="n in 3"
              :key="n"
              cols="12"
              md="4"
            >
              <v-item v-slot="{ active, toggle }">
                <v-card
                  :color="active ? 'primary' : ''"
                  class="d-flex align-center"
                  dark
                  height="30"
                  @click="toggle"
                >
                  <v-scroll-y-transition>
                    <div
                      v-if="active"
                      class="text-h2 flex-grow-1 text-center"
                    >
                      Active
                    </div>
                  </v-scroll-y-transition>
                </v-card>
              </v-item>
            </v-col>
          </v-row>
        </v-container>
      </v-item-group>
        </v-list-item>
      </template>
    </v-list>


        <!-- 분류 제목 + 버튼 여기까지  -->
        
        <v-card-actions class="d-flex justify-end" style="background-color: #ffffff">
          <!-- 초기화 버튼 회색 배경 넣어주기  -->
        <!-- 버튼 색깔 회색 좀 옅은거로 바꿔야 될듯 -->
        <v-btn
            style="background-color:#F3F3F3;"
            text
            @click="dialog = false"
          >
            초기화
          </v-btn>
          <v-btn
            style="background-color:#289672;"
            text
            dark  
            @click.stop="dialog=false"
          >
            확인
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-cols>
</v-col>

<!-- 이거 모달 다 다시 짜야됨 -->


<!-- 내용 작성 -->
<v-col>
<v-cols cols="3" style="margin-left:5%; margin-bottom:15%; margin-top:10%;" align="center">
  내용 작성
</v-cols>
  <v-textarea 
  auto-grow 
  outlined 
  style="margin-left:5%; margin-right: 5%;"
  ></v-textarea>

</v-col>


</div>
</template>


<script>


export default {
  name: 'PostInputItem',
  components: {},
  data() {
    return {
      isRight: true,
      user: {
        nickName: "",
        introduce: "",
        PB: "",
        BR: "",
        img: "",

      },
      modal: false,
      dialogm1: '',
      dialog: false,
      tagitems: [
        { header: '대분류' },
               {
          id1: "식당",
          id2: "카페",
          id3: "문화",
          id4: '여행',
          id5: '생활',
        },
        { divider: true},
        {
          bgc: "green lighten-2",
          icon: "mdi-lock-outline",
          color: "white",
          title: '비밀번호 재설정',
          subtitle: '비밀번호 변경',
        },
        { divider: true},
          { header: 'Account Settings22' },
               {
          bgc: "blue lighten-3",
          icon: "mdi-account-outline",
          color: "white",
          title: '프로필 변경',
          subtitle: '개인 정보 변경',
        },
        { divider: true},
        {
          bgc: "green lighten-2",
          icon: "mdi-lock-outline",
          color: "white",
          title: '비밀번호 재설정',
          subtitle: '비밀번호 변경',
        },
        { divider: true},

      ],
    };
  },

  methods: {
        test() {
      console.log(this.user);
    },
  },

}
</script>

<style scoped>
  #v-chip {
    color: #289672;
  }
</style>