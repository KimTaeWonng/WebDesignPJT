<template>
  <v-container>
    <!-- 상단 네브바 -->
    <v-row class="text-center d-flex mb-5" align="center" style="margin-top: 3px">
      <!-- 닫기 버튼 -->
      <v-col cols="2" @click="goMain('')">
        <v-icon>mdi-close</v-icon>
      </v-col>
      <!-- 로고 -->
      <v-col cols="8">
        <span v-if="this.type == 'modify'">게시글 수정</span>
        <span v-else>게시글 작성</span>
      </v-col>
      <!-- 돋보기/설정 버튼 -->
      <v-col cols="2">
        <v-row>
          <!-- 여기에 제출 넣으면 될듯 -->
          <v-col align="center" @click="registerPost()">
            <v-icon>mdi-check</v-icon>
          </v-col>
        </v-row>
      </v-col>
    </v-row>

    <!-- 게시 완료 dialog -->
    <v-dialog v-model="successDialog" max-width="290">
      <v-card>
        <div>
          <br />
          <br />
        </div>

        <div class="text-center" style="color: rgb(40, 150, 114)">
          <span class="material-icons" style="font-size: 80px"> task_alt </span>
        </div>

        <div class="text-center">
          <div style="font-weight: bold"></div>
          게시글이 등록되었습니다!
        </div>

        <div class="text-center" style="margin-top: 10%">
          <v-btn color="rgb(40,150,114)" @click="goProfile()" dark> 확인 </v-btn>
        </div>

        <div>
          <br />
          <br />
        </div>
      </v-card>
    </v-dialog>

    <!-- 프로필 사진 변경 start 카드 크기 반응형으로 -->
    <!-- <v-row justify="space-around" style="margin: 0%">
      <v-card height="290px" width="290px">
        <v-row class="text-center" style="margin-top: 35%" align="center">
          <v-col>
            <label for="file">
              <v-avatar color="#289672" size="50">
                <v-icon v-if="user.img == ''" color="#ffffff">
                  mdi-plus
                </v-icon>
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
    </v-row> -->

    <!-- :style="{backgroundImage:`url('${image}')`}" -->
    <div class="ml-9" style="margin: 5%">
      <v-row align="center">
        이미지 추가
        <v-btn color="primary" dark icon>
          <label for="file">
            <v-avatar color="#289672" size="20">
              <label for="chooseFile">
                <v-icon color="#ffffff" small> mdi-plus </v-icon>
              </label>

              <div>
                <form method="post" enctype="multipart/form-data">
                  <input
                    multiple="multiple"
                    style="display: none"
                    ref="image"
                    @change="
                      upload();
                      getMeta();
                    "
                    type="file"
                    id="chooseFile"
                    name="chooseFile"
                    accept="image/*"
                  />
                </form>
              </div>
            </v-avatar>
          </label>
        </v-btn>
      </v-row>
    </div>

    <!-- <v-row class="text-center" style="margin-bottom:12%; margin-right:12%;" align="center">
            <v-col>
              <label for="file">
                <v-avatar  color="#289672" size="50" style="position:fixed;">
                  <label for="chooseFile">
                    <v-icon  color="#ffffff">
                      mdi-plus
                    </v-icon>
                  </label>
                  
                <div>
                  <form method="post" enctype="multipart/form-data">
                    <input
                      multiple
                      style="display: none"
                      ref="image"
                      @change="uploadImg()"
                      type="file"
                      id="chooseFile"
                      name="chooseFile"
                      accept="image/*"
                    />
                  </form>
                </div>
                </v-avatar>
              </label>
            </v-col>
          </v-row> -->

    <v-carousel height="290px" width="290px" hide-delimiter-background show-arrows-on-hover>
      <v-carousel-item v-for="(img, i) in post.image" :key="i" :src="img"> </v-carousel-item>
    </v-carousel>
    <!-- </v-parallax> -->

    <!-- 태그추가 + 버튼 -->
    <div class="ml-9" style="margin: 5%">
      <v-dialog v-model="dialog" max-width="600px">
        <template v-slot:activator="{ on, attrs }">
          <v-row align="center"
            >태그 추가
            <v-btn color="primary" dark v-bind="attrs" v-on="on" icon>
              <v-avatar color="#289672" size="20">
                <v-icon color="#ffffff" small> mdi-plus </v-icon>
              </v-avatar>
            </v-btn>
          </v-row>
        </template>
        <v-card>
          <v-card-title class="justify-center" style="font-weight: bolder; font-size: 5vw"
            >태그 추가
          </v-card-title>

          <!-- 분류 제목 + 버튼 -->
          <!-- 대분류 -->
          <v-item-group mandatory align="center" v-model="selected_1">
            <v-subheader>대분류</v-subheader>
            <v-item v-for="n in 5" :key="n" v-slot="{ active, toggle }">
              <v-btn
                height="64px"
                elevation="0"
                style="margin: 5px"
                :color="active ? '#289672' : ''"
                active-class="white--text"
                :input-value="active"
                @click="toggle(), selectBig(n)"
              >
                {{ categorys.tag[n - 1].big_name }}
              </v-btn>
            </v-item>
          </v-item-group>

          <!-- 소분류 -->
          <v-item-group mandatory align="center" v-model="selected_2">
            <v-subheader>소분류</v-subheader>
            <v-item v-for="(item, i) in this.small" :key="i" v-slot="{ active, toggle }">
              <v-btn
                width="64px"
                height="64px"
                elevation="0"
                style="margin: 5px"
                :color="active ? '#289672' : ''"
                active-class="white--text"
                :input-value="active"
                @click="toggle"
              >
                {{ item.small_name }}
              </v-btn>
            </v-item>
          </v-item-group>

          <!-- 누구랑 -->
          <v-item-group mandatory align="center" v-model="selected_3">
            <v-subheader>누구랑</v-subheader>
            <v-item v-for="(who, i) in whos" :key="i" v-slot="{ active, toggle }">
              <v-btn
                height="64px"
                elevation="0"
                style="margin: 5px"
                :color="active ? '#289672' : ''"
                active-class="white--text"
                :input-value="active"
                @click="toggle"
              >
                {{ who }}
              </v-btn>
            </v-item>
          </v-item-group>

          <!-- 어디서 -->
          <v-item-group mandatory align="center" v-model="selected_4">
            <v-subheader>어디서</v-subheader>
            <v-item v-for="(where, i) in wheres" :key="i" v-slot="{ active, toggle }">
              <v-btn
                width="64px"
                height="64px"
                elevation="0"
                style="margin: 5px"
                :color="active ? '#289672' : ''"
                active-class="white--text"
                :input-value="active"
                @click="toggle"
              >
                {{ where }}
              </v-btn>
            </v-item>
          </v-item-group>

          <!-- 분류 제목 + 버튼 여기까지  -->
          <v-divider class="mt-4"></v-divider>
          <v-card-actions class="mt-2 d-flex justify-end" style="background-color: #ffffff">
            <!-- 초기화 버튼 회색 배경 넣어주기  -->
            <!-- 버튼 색깔 회색 좀 옅은거로 바꿔야 될듯 -->
            <!-- <v-btn style="background-color: #f3f3f3" text @click="test()"> 초기화 </v-btn> -->
            <v-btn style="background-color: #289672" text dark @click="addTag()"> 확인 </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </div>

    <!-- 태그 추가 시 칩 표시되는 공간 -->
    <div class="mt-5 ml-6 mb-5" v-show="isSelected">
      <v-chip
        style="background-color: #289672; font-size: 13px; color: white; text-align: center"
        class="text-align-center mr-1"
        small
        >{{ tag_big }}</v-chip
      >
      <v-chip
        style="background-color: #289672; font-size: 13px; color: white; text-align: center"
        class="text-align-center mr-1"
        small
        >{{ tag_small }}</v-chip
      >
      <v-chip
        style="background-color: #289672; font-size: 13px; color: white; text-align: center"
        class="text-align-center mr-1"
        small
        >{{ tag_who }}</v-chip
      >
      <v-chip
        style="background-color: #289672; font-size: 13px; color: white; text-align: center"
        class="text-align-center mr-1"
        small
        >{{ tag_where }}</v-chip
      >
    </div>

    <!-- 내용 작성 -->
    <div class="ml-9 mr-9">
      <v-row align="center">
        <span class="mb-3">내용 작성</span>
      </v-row>
      <v-row>
        <v-textarea v-model="post.content" auto-grow outlined></v-textarea>
      </v-row>
    </div>
  </v-container>
</template>

<script>
import tag from "@/assets/json/tag.json";
import { mapState } from "vuex";
import { http } from "@/js/http.js";
import EXIF from "exif-js";

const userStore = "userStore";

// window.onload = getExif;

// function getExif() {
//   var img1 = this.$refs["image"].files[0];
//   EXIF.getData(img1, function () {
//     var MetaData = EXIF.getAllTags(this);
//     console.log(MetaData)
//   })
// }

export default {
  name: "PostInputItem",
  components: {},
  props: {
    type: String,
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  data() {
    return {
      isRight: true,
      image: "",
      user: {
        nickName: "",
        introduce: "",
        PB: "",
        BR: "",
        img: "",
      },
      modal: false,
      dialogm1: "",
      dialog: false,
      successDialog: false,

      // componentKey: 0, // 소분류 key changing 이용하기 위한 key

      categorys: tag, // 대분류,소분류 태그 json
      small: [
        { small_name: "한식" },
        { small_name: "중식" },
        { small_name: "일식" },
        { small_name: "양식" },
        { small_name: "분식" },
        { small_name: "아시안" },
        { small_name: "기타" },
      ],
      whos: ["가족", "친구", "연인", "혼자"],
      wheres: [
        "서울",
        "경기",
        "인천",
        "강원",
        "제주",
        "대전",
        "충북",
        "충남/세종",
        "부산",
        "울산",
        "경남",
        "대구",
        "경북",
        "광주",
        "전남",
        "전주/전북",
      ],

      // 선택한 태그 값들 (1, 2, ...)
      selected_1: "",
      selected_2: "",
      selected_3: "",
      selected_4: "",

      isSelected: false,

      // 선택한 태그 항목들 (맛집, 가족 ...)
      tag_big: "",
      tag_small: "",
      tag_who: "",
      tag_where: "",

      post: {
        classBig: "",
        classSmall: "",
        classWhere: "",
        classWho: "",
        commentCnt: 0,
        content: "",
        image: [],
        likeCnt: 0,
        nickname: "",
        postGpsName: "",
        postLat: 0,
        postLng: 0,
        uploadTime: "",
        userId: "",
        userImage: "",
      },

      badges: {
        badgeCafe: 0,
        badgeComment: 0,
        badgeCulture: 0,
        badgeFeed: 0,
        badgeFollow: 0,
        badgeFollower: 0,
        badgeFood: 0,
        badgeGroup: 0,
        badgeLife: 0,
        badgeMeet: 0,
        badgeTrip: 0,
        mainCafe: 0,
        mainComment: 0,
        mainCulture: 0,
        mainFeed: 0,
        mainFollow: 0,
        mainFollower: 0,
        mainFood: 0,
        mainGroup: 0,
        mainLife: 0,
        mainMeet: 0,
        mainTrip: 0,
        userId: "",
      },
    };
  },
  methods: {
    async upload() {
      const formData = new FormData();
      const file = this.$refs["image"].files[0];
      // console.log(file)

      formData.append("files", file);
      // console.log(formData)

      await http
        .post("/file", formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then((res) => {
          // console.log(res)
          // console.log(res.data[0])

          const imagePath = res.data[0];
          this.image = `https://i7a505.p.ssafy.io/api/file?imagePath=${imagePath}`;
          console.log(this.image);
          this.post.image.push(this.image);
          // console.log(this.user.img)
          // console.log(this.group.group_image)
          // http2.get(`/file?imagePath=${imagePath}`)
        })
        .catch((err) => {
          console.log(err);
        });
    },

    async getMeta() {
      const metaImg = this.$refs["image"].files[0];
      EXIF.getData(metaImg, function () {
        var exifLong = EXIF.getTag(this, "GPSLongitude");
        var exifLat = EXIF.getTag(this, "GPSLatitude");
        var exifLongRef = EXIF.getTag(this, "GPSLongitudeRef");
        var exifLatRef = EXIF.getTag(this, "GPSLatitudeRef");

        var latitude = 0;
        var longitude = 0;

        if (exifLatRef == "S") {
          latitude = exifLat[0] * -1 + (exifLat[1] * -60 + exifLat[2] * -1) / 3600;
        } else {
          latitude = exifLat[0] + (exifLat[1] * 60 + exifLat[2]) / 3600;
        }

        if (exifLongRef == "W") {
          longitude = exifLong[0] * -1 + (exifLong[1] * -60 + exifLong[2] * -1) / 3600;
        } else {
          longitude = exifLong[0] + (exifLong[1] * 60 + exifLong[2]) / 3600;
        }

        console.log(latitude); // 위도
        console.log(longitude); // 경도
      });
    },

    test() {
      // console.log(this.user);
      const data = this.categorys;
      console.log(data);
      console.log(data.tag);
      console.log(data.tag[0]);
      console.log(data.tag[0].big_id);
    },
    selectBig(n) {
      // this.componentKey = new Date();
      // this.componentKey = n;

      // this.smalls = this.categorys.tag[n - 1];

      console.log(n);
      this.small = this.categorys.tag[n - 1].category;
    },
    addTag() {
      this.dialog = false;
      this.isSelected = true;
      this.tag_big = this.categorys.tag[this.selected_1].big_name;
      this.tag_small = this.categorys.tag[this.selected_1].category[this.selected_2].small_name;
      this.tag_who = this.whos[this.selected_3];
      this.tag_where = this.wheres[this.selected_4];
    },
    goProfile(pageName) {
      if (this.$route.path != pageName) {
        this.$router.push({
          name: "profile",
          params: { userid: this.userInfo.user_id },
        });
      }
    },
    goMain(pageName) {
      if (this.$route.path != pageName) {
        this.$router.push({
          name: "mainList",
        });
      }
    },
    async registerPost() {
      // 게시글 등록 및 수정하기
      console.log("등록 포스트!");

      // 등록할 때
      if (this.type == "register") {
        const response = await http.post(`/main/posts`, {
          classBig: this.tag_big,
          classSmall: this.tag_small,
          classWhere: this.tag_where,
          classWho: this.tag_who,
          commentCnt: 0,
          content: this.post.content,
          image: this.post.image[0],
          likeCnt: 0,
          nickname: this.userInfo.nickname,
          postGpsName: "해안이네", // 메타데이터 구현 후 변경 필요
          postLat: 30, // 메타데이터 구현 후 변경 필요
          postLng: 120, // 메타데이터 구현 후 변경 필요
          uploadTime: "",
          userId: this.userInfo.user_id,
          userImage: this.userInfo.profileImage,
        });

        const getPosts = await http.get(`/main/posts`);
        const newPostId = getPosts.data[0].postId;
        console.log(newPostId);

        //다중 이미지 업로드
        const uploadImages = await http.post(`/file/post/${newPostId}`, this.post.image);

        if (uploadImages.data == 1) {
          console.log("다중 이미지 업로드 성공");
        }

        if (response.data == 1) {
          this.successDialog = true;
        }
      } else {
        // 수정할 때
        const response = await http.put(`/main/posts/${this.$route.params.postno}`, {
          classBig: this.tag_big,
          classSmall: this.tag_small,
          classWhere: this.tag_where,
          classWho: this.tag_who,
          commentCnt: 0,
          content: this.post.content,
          image: this.post.image[0],
          likeCnt: 0,
          nickname: this.userInfo.nickname,
          postGpsName: "해안이네", // 메타데이터 구현 후 변경 필요
          postId: this.$route.params.postno,
          postLat: 30, // 메타데이터 구현 후 변경 필요
          postLng: 120, // 메타데이터 구현 후 변경 필요
          uploadTime: "",
          userId: this.userInfo.user_id,
          userImage: this.userInfo.profileImage,
        });
        console.log(response.data);
      }

      // userInfo의 bd가 0이면 1로 변경
      if (this.userInfo.bd == 0) {
        this.userInfo.bd = 1;
        // 뱃지 보유여부 1로 회원정보 수정
        const modifyBD = await http.put(`/users/${this.userInfo.user_id}`, this.userInfo);
        console.log("뱃지보유여부 변경!!");
        // console.log(this.userInfo);
        console.log(modifyBD);

        // /users/badge 뱃지생성 api (post)
        this.badges.userId = this.userInfo.user_id;
        const registBadge = await http.post(`/users/badge`, this.badges);
        console.log(registBadge);
      } else {
        const getBadgeList = await http.get(`/users/badge/${this.userInfo.user_id}`);
        this.badges = getBadgeList.data;
        console.log(this.badges);
      }

      // /search/posts/count/{userid} api 받아옴
      const getClassBigCnt = await http.get(`/search/posts/count/${this.userInfo.user_id}`);
      console.log(getClassBigCnt);

      // 3, 30, 100, 200, 300 개면 해당 레벨업에 맞게 /users/badge 뱃지수정 api(put)
      if (
        getClassBigCnt.data.맛집 == 2 ||
        getClassBigCnt.data.맛집 == 29 ||
        getClassBigCnt.data.맛집 == 99 ||
        getClassBigCnt.data.맛집 == 199 ||
        getClassBigCnt.data.맛집 == 299
      ) {
        this.badges.badgeFood++;
        const modifyBadgeFood = await http.put(`/users/badge`, this.badges);
        console.log(this.badges);
        if (modifyBadgeFood.data == 1) {
          console.log("맛집 레벨 수정 성공");
        } else {
          console.log("맛집 레벨 수정 실패");
        }
      } else if (
        getClassBigCnt.data.카페 == 2 ||
        getClassBigCnt.data.카페 == 29 ||
        getClassBigCnt.data.카페 == 99 ||
        getClassBigCnt.data.카페 == 199 ||
        getClassBigCnt.data.카페 == 299
      ) {
        this.badges.badgeCafe++;
        const modifyBadgeCafe = await http.put(`/users/badge`, this.badges);
        if (modifyBadgeCafe.data == 1) {
          console.log("카페 레벨 수정 성공");
        } else {
          console.log("카페 레벨 수정 실패");
        }
      } else if (
        getClassBigCnt.data.문화 == 2 ||
        getClassBigCnt.data.문화 == 29 ||
        getClassBigCnt.data.문화 == 99 ||
        getClassBigCnt.data.문화 == 199 ||
        getClassBigCnt.data.문화 == 299
      ) {
        this.badges.badgeCulture++;
        const modifyBadgeCulture = await http.put(`/users/badge`, this.badges);
        if (modifyBadgeCulture.data == 1) {
          console.log("문화 레벨 수정 성공");
        } else {
          console.log("문화 레벨 수정 실패");
        }
      } else if (
        getClassBigCnt.data.여행 == 2 ||
        getClassBigCnt.data.여행 == 29 ||
        getClassBigCnt.data.여행 == 99 ||
        getClassBigCnt.data.여행 == 199 ||
        getClassBigCnt.data.여행 == 299
      ) {
        this.badges.badgeTrip++;
        const modifyBadgeTrip = await http.put(`/users/badge`, this.badges);
        if (modifyBadgeTrip.data == 1) {
          console.log("여행 레벨 수정 성공");
        } else {
          console.log("여행 레벨 수정 실패");
        }
      } else if (
        getClassBigCnt.data.생활 == 2 ||
        getClassBigCnt.data.생활 == 29 ||
        getClassBigCnt.data.생활 == 99 ||
        getClassBigCnt.data.생활 == 199 ||
        getClassBigCnt.data.생활 == 299
      ) {
        this.badges.badgeLife++;
        const modifyBadgeLife = await http.put(`/users/badge`, this.badges);
        if (modifyBadgeLife.data == 1) {
          console.log("생활 레벨 수정 성공");
        } else {
          console.log("생활 레벨 수정 실패");
        }
      }
    },
  },
  async created() {
    // this.type == 'modify' 인 경우 루트 경로의 게시글 내용 가져오기 함수 필요
    if (this.type == "modify") {
      const getPost = await http.get(`/main/posts/${this.$route.params.postno}`);
      this.post = getPost.data;
      // console.log(this.post);
      this.tag_big = getPost.data.classBig;
      this.tag_small = getPost.data.classSmall;
      this.tag_who = getPost.data.classWho;
      this.tag_where = getPost.data.classWhere;
      this.isSelected = true;

      this.post.image = [];
      const getImages = await http.get(`/file/post/${this.post.postId}`);
      for (var i = 0; i < getImages.data.length; i++) {
        this.post.image.push(getImages.data[i].filePath);
      }
    }
  },
};
</script>

<style scoped>
#v-chip {
  color: #289672;
}
</style>
